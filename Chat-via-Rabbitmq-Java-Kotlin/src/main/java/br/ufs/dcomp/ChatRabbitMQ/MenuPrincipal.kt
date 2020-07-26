package br.ufs.dcomp.ChatRabbitMQ

class MenuPrincipal {

     companion object {

        @JvmStatic
        fun help(){
            println("!<comando>")
            println("@<nomeUser>")
            println("#<nomeGrupo>")
            println("addUsers <nomeUser> <...> <nomeGrupo>")
            println("createGroup <nomeGrupo>")
            println("exit")
            println("listGroup")
            println("listUsers <nomeGrupo>")
            println("removeUser <nomeUser> <...> <nomeGrupo>")
            println("upload <caminho-do-arquivo>")
        }
        
        @JvmStatic
        fun comando(mensagem: String?, chat: Chat): Boolean{
            var mensagemLista = mensagem?.split(" ")

            when(mensagemLista?.get(0)){

                "exit" -> return false

                "createGroup" -> {
                    if (mensagemLista.size > 1) {
                        chat.createGroup(mensagemLista.last())
                    } else {
                        println("Grupo Inválido")
                    }
                }

                "addUsers" -> {
                    var usuarios = mensagemLista.subList(1, mensagemLista.size-1)
                    chat.addUsers(usuarios, mensagemLista.last() )
                }

                "removeUser" -> {
                    var usuarios = mensagemLista.subList(1, mensagemLista.size-1)
                    chat.removeUser(usuarios, mensagemLista.last())
                }
                "removeGroup" ->{
                    chat.removeGroup(mensagemLista.last())
                }
                "listGroup" ->{
                    chat.listGroup()
                }
                "listUsers" -> {
                    var group = mensagemLista.last()
                    chat.listMembers(group)
                }
                "upload" ->{ if (chat.PARA_NAME != "" || chat.EXCHANGE_NAME != "") {
                    var arquivo = mensagemLista.last()
                    chat.envio_arq(arquivo)

                }else{
                    println("Destinatário inválido!")
                }

                }
                "help" ->{
                    help()
                }
            else -> println("Comando Inválido")
            }
            return true
        }

        @JvmStatic
        fun menu(mensagem: String?, chat: Chat): Boolean {
            return when(mensagem?.get(0)!!) {
                '@' -> {
                    var symbol = "@"
                    var nome = mensagem.substring(1)
                    chat.setCabecalho(symbol, nome)
                    chat.setPARA_NAME(nome)
                    true

                }

                '!'  -> {
                    comando(mensagem.substring(1), chat)

                }
                '#' -> {
                    var symbol = "#"
                    var nome = mensagem.substring(1)
                    chat.setCabecalho(symbol, nome)
                    chat.setCanalGroup( nome )
                    true
                }
                else -> {
                    chat.envio_msg(mensagem)
                    true
                }
            }
        }

         @JvmStatic
         fun main(args: Array<String>){

             print("Users:")
             var USER_NAME = readLine()
             var chat = Chat(USER_NAME)
             chat.setCabecalho()
             chat.ChannelConsumerDefault()

             print(chat.Cabecalho)
             var MENSAGEM: String? = readLine()

             while(menu(MENSAGEM, chat)){
                 print(chat.Cabecalho)
                 MENSAGEM = readLine()
             }


             try {
                 chat.CANAL_DE_CONSUMO.close()
                 chat.CANAL_DE_ENVIO.close()
                 chat.CANAL_DE_ARQ_CONSUMO.close()
                 
             }finally {
                 chat.connection.close()
             }
         }

     }
}

