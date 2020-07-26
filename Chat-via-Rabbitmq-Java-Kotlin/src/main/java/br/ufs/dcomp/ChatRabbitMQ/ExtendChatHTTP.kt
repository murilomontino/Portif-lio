package br.ufs.dcomp.ChatRabbitMQ

import java.net.URL
import java.net.URLConnection
import java.util.*

fun tratandoDados(texto: String): List<String>{
    var palavra: String = ""
    var arq = texto.forEach { letra ->
        if(letra=='[' || letra==']' || letra=='{' || letra == '}' || letra == '"'){
        }else{
            palavra += letra
        }
    }
    return palavra.split(",")
}

fun Chat.site_declare(tipo: String = ""): URLConnection {
    var con: URLConnection
    try {

        var url = URL("http://balanceadohttp-bee465a8ca6e9acf.elb.us-east-1.amazonaws.com/$tipo" )
        con = url.openConnection()
        val userCredentials = "murilomontino:kingdom2012"
        val basicAuth = "Basic " + String(Base64.getEncoder().encode(userCredentials.toByteArray()))
        con.setRequestProperty("Authorization", "$basicAuth")
        con.setRequestProperty("Content-Type", "application/json")
        return con

    } catch (e: Exception){
        var url = URL("http://52.23.232.254:15672/$tipo")
        con = url.openConnection()
        return con
    }


}

fun Chat.listMembers(group: String){

    try {
        var tipo = "api/exchanges/%2F/$group/bindings/source"
        var site = site_declare(tipo)
        var input = site.getInputStream().reader()
        var leitura = tratandoDados(input.readLines().toString())

        var member_list: List<String> = mutableListOf()
        for (LINHA in leitura) {
            if (LINHA.startsWith("destination:")) {
                var palavra = LINHA.split(":").last()
                try {
                    member_list += if (palavra.length > 0 && palavra != member_list.last()) {
                        palavra
                    } else continue
                }catch (e: Exception){
                    member_list += palavra
                }

            }
        }

        println(member_list.joinToString(" "))
    }catch (e: Exception){
        println("Grupo Inválido")
    }
}

fun Chat.listGroup(nome: String = this.USER_NAME){

    var tipo = "api/queues/%2F/$nome/bindings"
    try {
        var site = site_declare(tipo)
        var input = site.getInputStream().reader()
        var leitura = tratandoDados(input.readLines().toString())

        var group_list: List<String> = mutableListOf()
        for (LINHA in leitura) {
            if (LINHA.startsWith("source:")) {
                var palavra = LINHA.split(":").last()
                try {
                    group_list += if (palavra.length > 0 && palavra != group_list.last()) {
                        palavra
                    } else continue
                }catch (e: Exception){
                    group_list += palavra
                }

            }
        }
        println(group_list.joinToString(" "))
    }catch (e: Exception){
        println("Erro")
    }
}


