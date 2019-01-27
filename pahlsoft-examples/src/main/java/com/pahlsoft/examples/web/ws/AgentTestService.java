package com.pahlsoft.examples.web.ws;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName = "AgentTestService", wsdlLocation = "model/agent.wsdl")

public class AgentTestService {

	@WebMethod
	public ArrayList<byte[]> getTests(String hostname) { return obtainTests(hostname);}

	
	private ArrayList<byte[]> obtainTests(String hostname){
		return null; 
	}
	
}


