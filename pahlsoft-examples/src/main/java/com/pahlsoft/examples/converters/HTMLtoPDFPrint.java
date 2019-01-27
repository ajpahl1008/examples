package com.pahlsoft.examples.converters;


import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.html.simpleparser.StyleSheet;
import com.itextpdf.text.pdf.PdfWriter;

public class HTMLtoPDFPrint {

      //private static Font bFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
      
      public static void main(String[] args)    {
            try {
                  FileOutputStream fos = new FileOutputStream("csstestsheet.pdf");
                  Document document = new Document();
                  @SuppressWarnings("unused")
				PdfWriter pdfWriter = PdfWriter.getInstance(document,fos);
                  document.open();
                  addMetaData(document);
                  //For loop for multiple html docs
                  String html = fixSourceData();
                  addContent(document, html);
                  //end for
                  document.close();
            } catch (Exception ex) {
                  System.out.println(ex.getMessage());
            }
      }
      
      private static void addMetaData(Document document) {
            document.addAuthor("Anthony Pahl");
            document.addCreator("My Converter Job");
            document.addSubject("Subject");
            document.addCreationDate();
            document.addTitle("iText API Example ");
      }
      private static String fixSourceData() throws IOException {
            FileReader fr = new FileReader("CSS.htm");
            BufferedReader br = new BufferedReader(fr);
            String output = "";
            String str;

            while ((str = br.readLine()) != null) {
                  if (str.toLowerCase().contains("<title>")) {
                        continue;
                  } else if (str.toLowerCase().contains("<body")) {
                        output += str;
                        // Add html White Text Items (mailcode, Bank #, Acct #)
                        output += "<p color='#ffffff'>MailCode - Bank # - Acct #</p>";
                  }
                  output += str;
            }
            br.close();
            return output;
      }
      
      private static void addContent(Document document, String html) throws DocumentException, IOException {
    	    StringReader sr = new StringReader(html);
            List<Element> list = HTMLWorker.parseToList(sr,generateStyleSheet());
                    for (Element lst : list) {
            	document.add(lst);
            }
      }
      
      private static StyleSheet generateStyleSheet() {
            StyleSheet ss = new StyleSheet();
            ss.loadTagStyle("body", "font-family", "Times New Roman");
            ss.loadTagStyle("li", "padding-bottom", "10px");
            
            return ss;
      }
}

