package com.pahlsoft.messagefactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;
//import java.util.Random;

public class AMQMessage implements MessageListener,SendMessage {
    private static int ackMode;
    
    private boolean transacted = false;
    private MessageProducer producer;

    static {
        ackMode = Session.AUTO_ACKNOWLEDGE;
    }

    public void sendMessage(String factoryDestination, String clientQueueName, String myMessage) {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(factoryDestination);
        
        Connection connection;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(transacted, ackMode);
            Destination adminQueue = session.createQueue(clientQueueName);

            //Setup a message producer to send message to the queue the server is consuming from
            this.producer = session.createProducer(adminQueue);
            this.producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            //Now create the actual message you want to send
            TextMessage txtMessage = session.createTextMessage();
            txtMessage.setText(myMessage);
            txtMessage.setJMSPriority(1);

//            String correlationId = this.createRandomString();
//            txtMessage.setJMSCorrelationID(correlationId);

            this.producer.send(txtMessage);
            System.exit(0);
        } catch (JMSException e) {
            //Handle the exception appropriately: DEBUG:AJP We'll need a global Debug statement here.
        	e.printStackTrace();
        }
    }

//    private String createRandomString() {
//        Random random = new Random(System.currentTimeMillis());
//        long randomLong = random.nextLong();
//        return Long.toHexString(randomLong);
//    }

    public void onMessage(Message message) {
        String messageText = null;
        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                messageText = textMessage.getText();
                System.out.println("messageText = " + messageText);
                System.exit(0);
            }
        } catch (JMSException e) {
            //Handle the exception appropriately DEBUG:AJP We'll need a debug statement from global
        	e.printStackTrace(); 
        }
    }
  
}
