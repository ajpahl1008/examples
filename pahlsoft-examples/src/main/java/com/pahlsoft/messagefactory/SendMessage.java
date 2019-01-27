package com.pahlsoft.messagefactory;

public abstract interface SendMessage {
	public void sendMessage (String destination, String queue, String message);

}
