package com.pahlsoft.mongodb.test.find;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

public class FindTest {
    public static void main(String [] args) throws UnknownHostException {
        System.out.println("Start Testing");

        MongoClient  client = new MongoClient(new ServerAddress("192.168.0.14",27017));
        DB db = client.getDB("course");
        DBCollection collection = db.getCollection("findTestFromJava");
        collection.drop();

        for (int i = 0; i<10; i++) {
            collection.insert(new BasicDBObject("x", new Random().nextInt(100)));
        }
        System.out.println("Find one:");
        DBObject one = collection.findOne();
        System.out.println("one = " + one);
        System.out.println("\nFind all:");
        DBCursor cursor = collection.find();
        try {
            while (cursor.hasNext()) {
                DBObject cur = cursor.next();
                System.out.println("cursor = " + cur);
            }
        } finally {
            cursor.close();
        }
        System.out.println("\nFind Count:");
        long count = collection.count();
        System.out.println("count = " + count);

    }

}
