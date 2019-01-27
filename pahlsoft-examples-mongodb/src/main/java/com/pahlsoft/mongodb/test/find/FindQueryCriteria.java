package com.pahlsoft.mongodb.test.find;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

public class FindQueryCriteria {
    public static void main(String [] args) throws UnknownHostException {
        System.out.println("Start Testing");

        MongoClient  client = new MongoClient(new ServerAddress("192.168.0.14",27017));
        DB db = client.getDB("course");
        DBCollection collection = db.getCollection("findTestFromJava");
        collection.drop();

        for (int i = 0; i<10; i++) {
            collection.insert(
                    new BasicDBObject("x", new Random().nextInt(2))
                    .append("y",new Random().nextInt(100)));
        }

        // String based Query
        DBObject query = new BasicDBObject("x",0)
                .append("y", new BasicDBObject("$gt",10).append("$lt",90));

        System.out.println("\nFind Count:");
        long count = collection.count(query);
        System.out.println("count = " + count);


        System.out.println("\nFind all:");
        DBCursor cursor = collection.find(query);
        try {
            while (cursor.hasNext()) {
                DBObject cur = cursor.next();
                System.out.println("cursor = " + cur);
            }
        } finally {
            cursor.close();
        }





    }

}
