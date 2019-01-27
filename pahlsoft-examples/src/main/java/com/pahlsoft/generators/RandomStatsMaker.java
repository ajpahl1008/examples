package com.pahlsoft.generators;


import java.io.FileWriter;
import java.io.PrintWriter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RandomStatsMaker {

    static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    public static void main(String[] args) throws Exception {
        FileWriter fileHandle = new FileWriter("stats.out",true);
        PrintWriter pw = new PrintWriter(fileHandle);

        for (int x=0; x<3600; x++ ) {
            Date date = new Date();
            System.out.println("Time : " + sdf.format(date.getTime()) + " Math Value: " + (int) Math.ceil(Math.random()*30000));
            pw.print(sdf.format(date.getTime()) + " " + (int) Math.ceil(Math.random() * 30000)+"\n");
            pw.flush();
            java.lang.Thread.sleep(1000);

        }

        pw.close();
    }


}
