package com.pahlsoft.mongodb.test.find;

import com.mongodb.*;

import javax.management.Query;
import java.net.UnknownHostException;
import java.util.Random;

public class FindFieldSelection {
    public static void main(String [] args) throws UnknownHostException {
        System.out.println("Start Testing");

        MongoClient  client = new MongoClient(new ServerAddress("192.168.0.14",27017));
        DB db = client.getDB("course");
        DBCollection collection = db.getCollection("findTestFromJava");
        collection.drop();
        Random rand = new Random();

        for (int i = 0; i<10; i++) {
            collection.insert(
                    new BasicDBObject("x", rand.nextInt(2))
                    .append("y",rand.nextInt(100))
                    .append("z",rand.nextInt(1000)));
        }

        // String based Query
        DBObject query = QueryBuilder.start("x").is(0)
                .and("y").greaterThan(10).lessThan(70).get();

        System.out.println("\nFind Count:");
        long count = collection.count(query);
        System.out.println("count = " + count);


        System.out.println("\nFind all:");
        DBCursor cursor = collection.find(query,new BasicDBObject("y",true));

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
