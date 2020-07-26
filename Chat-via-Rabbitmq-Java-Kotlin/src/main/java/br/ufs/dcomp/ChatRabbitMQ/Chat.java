
package br.ufs.dcomp.ChatRabbitMQ;

import br.ufs.dcomp.ProtocolBuffers.Serializador;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Chat {

	protected ConnectionFactory factory;
	protected Connection connection;

	protected Channel CANAL_DE_ENVIO;
    protected Channel CANAL_DE_ENVIO_ARQ;

	protected Channel CANAL_DE_CONSUMO;
	protected Channel CANAL_DE_ARQ_CONSUMO;

	protected String USER_NAME;

	protected Consumer consumer;
	protected Consumer arquivo;

	protected String PARA_NAME;
	protected String EXCHANGE_NAME;

	protected String Cabecalho;

	public Chat(String username) throws IOException, TimeoutException {
		  this.USER_NAME = username;

		  this.PARA_NAME = "";
		  this.EXCHANGE_NAME = "";

		  this.factory = new ConnectionFactory();
		  this.set_factory();
		  this.connection = this.factory.newConnection();

		  this.CANAL_DE_ENVIO = this.connection.createChannel();
		  this.CANAL_DE_ENVIO_ARQ = this.connection.createChannel();

	}
	
	public void set_factory() {
		this.factory.setHost("balanceadorAMQP-f1068faa95e43b97.elb.us-east-1.amazonaws.com"); // Alterar
		this.factory.setUsername("murilomontino"); // Alterar
		this.factory.setPassword("kingdom2012"); // Alterar
		this.factory.setVirtualHost("/");
	}

	public void ConsumerMensagem() throws IOException {
	    this.consumer = new DefaultConsumer(this.CANAL_DE_CONSUMO) {
	        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)           throws IOException {

	          System.out.println(Serializador.DesserialMsg(body));
	          System.out.print(Cabecalho);
	        }
	      };

	                        //(queue-name, autoAck, consumer);    
	}

	public void ConsumerArquivo() throws IOException {
			this.arquivo = new DefaultConsumer(this.CANAL_DE_ARQ_CONSUMO) {
				public void handleDelivery(String consumerTag, Envelope envelope,
										   AMQP.BasicProperties properties, byte[] body) throws IOException {

					System.out.println(Serializador.DesserialMsg(body));
					System.out.print(Cabecalho);


				};
			};
}

	public void setPARA_NAME(String nome) throws IOException {

		try {

			this.PARA_NAME = nome;
			this.EXCHANGE_NAME = "";

			this.CANAL_DE_ENVIO = this.connection.createChannel();
			this.CANAL_DE_ENVIO.queueDeclare(this.PARA_NAME, false,
					false, false, null);

		} catch (IOException e) {
		}
	}
	
  public static void main(String[] argv) throws Exception {
		MenuPrincipal.main(argv);
  }
  
}