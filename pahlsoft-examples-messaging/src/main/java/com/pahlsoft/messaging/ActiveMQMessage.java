package com.pahlsoft.messaging;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jms.*;

public class ActiveMQMessage implements SendMessage {
    private static Log log = LogFactory.getLog(ActiveMQMessage.class);

    private static int ackMode;
    private static boolean transacted;
    private MessageProducer producer;
    private static Connection connection;
    private static ActiveMQConnectionFactory connectionFactory;
    private static TextMessage txtMessage;
    private static Session session;
    private static Destination queue;

    static {
        ackMode = Session.AUTO_ACKNOWLEDGE;
        transacted = false;
    }

    public ActiveMQMessage(String destination, String queueName) {
        try {
            setupConnection(destination, queueName);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private void setupConnection(String destination, String queueName) throws JMSException {
        connectionFactory = new ActiveMQConnectionFactory(destination);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(transacted, ackMode);
        queue = session.createQueue(queueName);
        producer = session.createProducer(queue);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        producer.setPriority(1);
        txtMessage = session.createTextMessage();
    }

    public void sendMessage(String message, String correlationId) throws JMSException {
        try {
            txtMessage.setJMSCorrelationID(correlationId);
            this.sendMessage(message);
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (JMSException e) {
                log.error("Message Failed");
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) throws JMSException {
        try {
            txtMessage.setText(message);
            this.producer.send(txtMessage);
            log.info("Message Sent");

        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (JMSException e) {
                log.error("Message Failed");
                e.printStackTrace();
            }
        }
    }


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
        	e.printStackTrace();
        }
    }
  
}
