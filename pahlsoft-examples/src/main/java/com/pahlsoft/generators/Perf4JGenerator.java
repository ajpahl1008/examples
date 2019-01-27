package com.pahlsoft.generators;

/**
 * Created by aj on 12/06/14.
 */

import org.perf4j.StopWatch;
import org.perf4j.log4j.Log4JStopWatch;

import java.io.FileWriter;
import java.io.PrintWriter;

public class Perf4JGenerator {


        private static int count = 2000;

        public static void usingString() throws Exception {
            StopWatch stopWatch = new Log4JStopWatch();
            String output= new String();
            Thread.sleep((int) Math.ceil(Math.random()*5000));
            for(int i=0;i<count;i++) {
                output = output.concat(Integer.toString(i));
            }
            stopWatch.stop("String");
        }

        public static void usingStringBuffer() throws Exception {

            StopWatch stopWatch = new Log4JStopWatch();
            StringBuffer output= new StringBuffer();
            Thread.sleep((int) Math.ceil(Math.random()*5000));
            for(int i=0;i<count;i++) {
                output = output.append(Integer.toString(i));
            }
            stopWatch.stop("StringBuffer");
        }

        public static void main(String[] args) throws Exception {
            FileWriter fileHandle = new FileWriter("stats.out",true);
            PrintWriter pw = new PrintWriter(fileHandle);

            for (int x=1; x<1000; x++) {
                usingString();
                usingStringBuffer();
            }
        }


}
