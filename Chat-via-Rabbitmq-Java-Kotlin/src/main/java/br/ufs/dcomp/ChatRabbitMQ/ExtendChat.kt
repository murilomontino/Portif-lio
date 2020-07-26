package br.ufs.dcomp.ChatRabbitMQ

import br.ufs.dcomp.ProtocolBuffers.Serializador
import com.google.protobuf.ByteString
import java.io.FileInputStream
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.concurrent.thread

fun Chat.ChannelConsumerDefault(){


        //Consumo MENSAGEM
        CANAL_DE_CONSUMO = connection.createChannel()
        CANAL_DE_CONSUMO.queueDeclare(USER_NAME, false, false,
                false, null)
        this.ConsumerMensagem()
        CANAL_DE_CONSUMO.basicConsume(USER_NAME, true, consumer)


    thread(start = true, isDaemon = true) {
        //Consumo ARQUIVOS
        CANAL_DE_ARQ_CONSUMO = connection.createChannel()
        CANAL_DE_ARQ_CONSUMO.queueDeclare(USER_NAME, false, false,
                false, null)
        ConsumerArquivo()
        CANAL_DE_ARQ_CONSUMO.basicConsume(USER_NAME, true, arquivo)
    }


}

fun Chat.setCabecalho(symbol: String = "", name: String = "", setas: String = ">>"){
    this.Cabecalho = symbol + name + setas
}

fun Chat.date_formatter(): String{
    var hoje = LocalDateTime.now()
    var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")
    var hoje_format = hoje.format(formatter)
    return hoje_format
}

fun Chat.formatando_mensagem(texto: String): ByteArray {
    val buffer = ByteString.copyFrom(texto.toByteArray())
    return Serializador.serialMsg(this.USER_NAME, this.date_formatter(), buffer, this.EXCHANGE_NAME)
}

@Throws(IOException::class)
fun Chat.envio_msg(mensagem: String) {
    val texto: ByteArray = this.formatando_mensagem(mensagem)
    var remetente = if(this.EXCHANGE_NAME == "") this.PARA_NAME else "mensagem"
    try {
        this.CANAL_DE_ENVIO.basicPublish(this.EXCHANGE_NAME, remetente, null, texto)
    } catch (e: IllegalStateException) {
        println("Erro usuário de envio inválido!")
    }
}

@Throws(IOException::class)
fun Chat.envio_arq(diretorio: String): Thread {
    return thread(start = true, isDaemon = true) {
        try {

            var nome_arq = diretorio.split("\\").last()

            var receptor = if (this.PARA_NAME == "") this.EXCHANGE_NAME else this.PARA_NAME

            println()
            println("Enviando $nome_arq para $receptor")
            print(this.Cabecalho)

            var file = FileInputStream(diretorio)
            var source = Paths.get(diretorio);
            var tipoMime = Files.probeContentType(source);

            val buffer = ByteString.copyFrom(file.readBytes())
            var conteudo = Serializador.serialMsg(emisor = this.USER_NAME, date = date_formatter(),
                    msg = buffer, EXCHANGE_NAME = this.EXCHANGE_NAME, tipo = tipoMime, nome_conteudo = nome_arq)

            var remetente = if (this.EXCHANGE_NAME == "") this.PARA_NAME else "arquivo"
            this.CANAL_DE_ENVIO_ARQ.basicPublish(this.EXCHANGE_NAME, remetente, null, conteudo)



        }catch (e: Exception){
            println("Arquivo Inválido!")
            print(this.Cabecalho)
        }

    }

}
