
package br.ufs.dcomp.ProtocolBuffers

import com.google.protobuf.ByteString
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream


class Serializador {
    companion object {

        @JvmStatic
        fun serialMsg(emisor: String, date: String, msg: ByteString,
                      EXCHANGE_NAME: String, tipo: String = "text/plain", nome_conteudo: String = ""): ByteArray {

            var cabecalho = SerialProto.Cabecalho.newBuilder()
            setCabecalhoBuilder(cabecalho, emisor, date, EXCHANGE_NAME, tipo)


            var MSGPROTO = SerialProto.Mensagem.newBuilder()
                MSGPROTO.setCabecalho(cabecalho)
                MSGPROTO.conteudo = msg
                MSGPROTO.nomeConteudo = nome_conteudo

            val mensagem = MSGPROTO.build()
            val buffer = mensagem.toByteArray()


            return buffer
        }

        @JvmStatic
        fun download(objectByte: ByteArray, date: String, hora: String,
                     grupo: String, emisor: String, nome_conteudo: String): String{

            val bf = BufferedInputStream(objectByte.inputStream())
            bf.read(objectByte)
            var file = File("downloads/")
            file.mkdir()
            var arquivo = File("downloads/$nome_conteudo")
            val fos = FileOutputStream(arquivo)
            fos.write(objectByte)
            fos.close()


            return ("\n($date ás $hora)  Arquivo $nome_conteudo recebido de @$emisor$grupo ")

        }

        @JvmStatic
        fun DesserialMsg(buffer: ByteArray): String {

            val MSGPROTO = SerialProto.Mensagem.parseFrom(buffer)
            val emisor = MSGPROTO.cabecalho.emisor
            val date = MSGPROTO.cabecalho.data
            val hora = MSGPROTO.cabecalho.hora
            val grupo = if(MSGPROTO.cabecalho.grupo == "") "" else ("#"+MSGPROTO.cabecalho.grupo)
            val tipo = MSGPROTO.cabecalho.tipo



            if (tipo != "text/plain"){
                val conteudo = MSGPROTO.conteudo.toByteArray()
                val nome_conteudo = MSGPROTO.nomeConteudo
                return (download(conteudo, date, hora, grupo, emisor, nome_conteudo) )
            }
            else{
                val msg = MSGPROTO.conteudo.toStringUtf8()
                return ("\n($date ás $hora)  $emisor$grupo diz: ${msg}")
            }




        }

        fun setCabecalhoBuilder(cabecalho: SerialProto.Cabecalho.Builder , name: String,
                                date: String, EXCHANGE_NAME: String,
                                tipo: String = "text/plain"){
            var data_hora = date.split(" ")
            cabecalho.data = data_hora.get(0)
            cabecalho.hora = data_hora.get(1)
            cabecalho.emisor = name
            cabecalho.tipo = tipo
            cabecalho.grupo = EXCHANGE_NAME
        }


    }
} 
