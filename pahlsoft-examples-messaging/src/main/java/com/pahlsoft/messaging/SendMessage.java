package com.pahlsoft.messaging;

import javax.jms.JMSException;

public interface SendMessage {
	void sendMessage(String message) throws JMSException;
	void sendMessage(String message, String correlationId) throws JMSException;


}
