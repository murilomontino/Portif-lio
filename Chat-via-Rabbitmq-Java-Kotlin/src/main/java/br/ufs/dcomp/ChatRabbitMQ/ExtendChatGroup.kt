package br.ufs.dcomp.ChatRabbitMQ

import java.io.IOException


fun Chat.createGroup(name: String){
    try {
      
        this.CANAL_DE_ENVIO.connection.createChannel().exchangeDeclare(name, "direct")
        this.CANAL_DE_ENVIO.queueBind(this.USER_NAME, name, "mensagem")
        this.CANAL_DE_ENVIO.queueBind(this.USER_NAME, name, "arquivo")


        println("Grupo $name criado com sucesso!")
    }catch (e: Exception){
        println("Falha ao criar o grupo!")
    }

}

fun Chat.addUsers(usuario: List<String>, EXCHANGE_NAME: String){

    for(nome in usuario){
        
        try{
        var name_arq = nome + "ARQ"
        
        this.CANAL_DE_ENVIO.queueDeclare(nome, false,
                false, false, null)


        this.CANAL_DE_ENVIO.queueBind(nome, EXCHANGE_NAME , "mensagem")
        this.CANAL_DE_ENVIO.queueBind(nome, EXCHANGE_NAME , "arquivo")

        
        println("$nome adicionado com sucesso em $EXCHANGE_NAME!")
        }catch(e: Exception){
        println("Não foi possível add $nome")
        }
        

    }

}

fun Chat.removeUser(usuario: List<String>, EXCHANGE_NAME: String){
    try {
        usuario.forEach {
            nome ->
            this.CANAL_DE_ENVIO.queueUnbind(nome, EXCHANGE_NAME, "mensagem")
            this.CANAL_DE_ENVIO.queueUnbind(nome, EXCHANGE_NAME, "arquivo")
            println("$nome removido com sucesso!")

        }
    }catch (e: Exception) {
        println("Erro ao remover!")
    }


}

fun Chat.removeGroup(EXCHANGE_NAME: String){
    try {
        this.CANAL_DE_ENVIO.exchangeDelete(EXCHANGE_NAME)
        println("Grupo $EXCHANGE_NAME removido com sucesso!")
    }catch (e: Exception){
        println("Não foi possível remover o grupo")
    }
}

fun Chat.setCanalGroup(group : String): Boolean{
    return try {
        this.PARA_NAME = ""
        this.EXCHANGE_NAME =  group

        true
    } catch (e: IOException) {
        false
    }

}




