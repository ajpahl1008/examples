package com.pahlsoft.examples.ejb;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Message-Driven Bean implementation class for: ReceiveTestMessageMDB
 *
 */
@MessageDriven(name="ReceiveTestMessageMDB", activationConfig = {
	    @ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
	    @ActivationConfigProperty(propertyName="destination", propertyValue="AgentMessages")
	})

@TransactionManagement(TransactionManagementType.BEAN)
public class ReceiveTestMessageMDB implements MessageListener {

    /**
     * Default constructor. 
     */
    public ReceiveTestMessageMDB() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    
    public void onMessage(Message message) {
        // TODO Auto-generated method stub
    	System.out.println("DEBUG: Holy Shit this worked!!!");
        
    }

}
