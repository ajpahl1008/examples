package com.pahlsoft.messaging;

import javax.jms.JMSException;
import java.util.UUID;

public class MessageController {

	private static String destination;
	private static String queueName;
	private static String message;
	private static String messageId = UUID.randomUUID().toString();

	public static void main(String[] args) {
		if (args.length < 3) {
			System.out.println("Usage Error:");
			System.out.println("MessageController [destination] [queueName] [message]");
			System.out.println("or");
			System.out.println("MessageController [destination] [queueName] [message] [uuid]");
		}

		if (args.length >= 3) {
			destination = args[0];
			queueName = args[1];
			message = args[2];
		}

		if (args.length >= 4) {
			messageId = args[3];
		}

		ActiveMQMessage amq = new ActiveMQMessage(destination, queueName);
		try {
			amq.sendMessage(message, messageId);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
