package com.pahlsoft.examples.parsers;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class ReadProperties {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		FileInputStream fis = new FileInputStream("C:\\MyData\\Development\\workspace\\TestProperties\\src\\dev.props");
		FileOutputStream fos = new FileOutputStream("C:\\MyData\\Development\\workspace\\TestProperties\\src\\properties.xml");
		Properties props = new Properties();
		Properties propsFromXML = new Properties();
		
		// Load the .properties file
		props.load(fis);
		System.out.println("PROPS: User: " + props.getProperty("user"));
		System.out.println("PROPS: Password: " + props.getProperty("password"));
		
		// Write it to an XML
		props.storeToXML(fos, "My First Attempt To Store Properties in XML");
		
		FileInputStream fisXML = new FileInputStream("C:\\MyData\\Development\\workspace\\TestProperties\\src\\properties.xml");
		propsFromXML.loadFromXML(fisXML);
		
		System.out.println("XML: User: " + propsFromXML.getProperty("user"));
		System.out.println("XML: Password: " + propsFromXML.getProperty("password"));
		
	}

}
