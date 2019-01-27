package com.pahlsoft.filewatcher;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class UnixCommand {
	// Open the process pipe and process the UNIX command provided
	public ArrayList<String> exec( String cmd ) throws Exception {
		
		
		ArrayList<String> cmdOutput = new ArrayList<String>();
		String line = "";
		String[] execString = {"/bin/sh", "-c", cmd};
		//System.out.println("DEBUG: Executing Command: " + cmd);
		Process p = Runtime.getRuntime().exec(execString);
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        
        // Add cron.allow to the Arraylist
        while ((line = input.readLine()) != null) {
            cmdOutput.add(line);
            //if (Global.DebugEnabled){ 
            	//System.out.println("DEBUG: " + line);
            //}
        }
        
        input.close();
        
		return cmdOutput;
	}
}
