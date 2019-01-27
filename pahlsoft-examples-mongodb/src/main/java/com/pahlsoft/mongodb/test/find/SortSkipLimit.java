package com.pahlsoft.mongodb.test.find;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

public class SortSkipLimit {
    public static void main(String [] args) throws UnknownHostException {
        System.out.println("Start Testing");

        MongoClient  client = new MongoClient(new ServerAddress("192.168.0.14",27017));
        DB db = client.getDB("course");
        DBCollection lines = db.getCollection("DotNotationTest");
        lines.drop();
        Random rand = new Random();

        for (int i = 0; i<10; i++) {
            lines.insert(
                    new BasicDBObject("_id",i)
                    .append("start",
                            new BasicDBObject("x",rand.nextInt(90) + 10)
                                      .append("y",rand.nextInt(90) + 10)
                    )
                    .append("end",
                            new BasicDBObject("x",rand.nextInt(90) + 10)
                                      .append("y",rand.nextInt(90) + 10)
                    )
            );
        }


        //DBCursor cursor = lines.find().sort(new BasicDBObject("_id", -1));
        DBCursor cursor = lines.find().sort(new BasicDBObject("start.y", -1)).skip(2).limit(3);

        try {
            while (cursor.hasNext()) {
                DBObject cur = cursor.next();
                System.out.println(cur);
            }
        } finally {
            cursor.close();
        }


    }

}
