package com.pahlsoft.examples.parsers;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/*
<PHONEBOOK>
<PERSON>
 <NAME>Joe Wang</NAME>
 <EMAIL>joe@yourserver.com</EMAIL>
 <TELEPHONE>202-999-9999</TELEPHONE>
 <WEB>www.java2s.com</WEB>
</PERSON>
<PERSON>
 <NAME>Karol</name>
 <EMAIL>karol@yourserver.com</EMAIL>
 <TELEPHONE>306-999-9999</TELEPHONE>
 <WEB>www.java2s.com</WEB>
</PERSON>
<PERSON>
 <NAME>Green</NAME>
 <EMAIL>green@yourserver.com</EMAIL>
 <TELEPHONE>202-414-9999</TELEPHONE>
 <WEB>www.java2s.com</WEB>
</PERSON>
</PHONEBOOK>

 */

public class ReadXMLSAX {

	public static void main(String args[]) {
		
		SaxHandler handler = new SaxHandler();
				
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			parser.parse("NewFile.xml", handler);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

class SaxHandler extends DefaultHandler {
	boolean foundItem = Boolean.FALSE;
	public void startElement(String uri, String localName, String qName, Attributes attrs ) throws SAXException {
		if (qName.equals("role")) {
			String name = attrs.getValue("name");
		    System.out.println(name); 
			//foundItem = Boolean.TRUE;
		}
		
		if (qName.equals("entitlement")) {
			String auth = attrs.getValue("auth");
			String eName = attrs.getValue("name");
		    System.out.println(auth+"="+eName);
		}
	}
// Uncomment this method if you want to manipulate at the character level.
//	 public void characters(char ch[], int start, int length) throws SAXException {
//		 if (foundItem) {
//			 System.out.println("ROLE: " + new String(ch, start, length));
//			 foundItem = Boolean.FALSE;
//		 }
// }

	
}







           
