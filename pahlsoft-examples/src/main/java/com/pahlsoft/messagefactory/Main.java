package com.pahlsoft.messagefactory;

public class Main implements SendMessage {
	public static void main(String[] args) {
	
		String myMessage = "THIS IS A NEW MESSAGE";
    	String myQueueName = "AgentMessages";
    	String myDestination = "AJs-MacBook-Pro.local:6161";
    	
        AMQMessage amq = new AMQMessage(); 
        amq.sendMessage(myDestination,myQueueName,myMessage);
        
        //new JBOSSMessage(myDestination,myQueueName,myMessage);
        //new GlassFishMessage(myDestination,myQueueName,myMessage);
        
	}

//	@Override
	public void sendMessage(String destination, String queue, String message) {
		// TODO Auto-generated method stub

	}

}
