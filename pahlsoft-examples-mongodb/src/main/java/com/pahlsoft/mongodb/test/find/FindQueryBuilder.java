package com.pahlsoft.mongodb.test.find;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

public class FindQueryBuilder {
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

        QueryBuilder builder = QueryBuilder.start("x").is(0)
                .and("y").greaterThan(10).lessThan(70);

        System.out.println("\nFind Count:");
        long count = collection.count(builder.get());
        System.out.println("count = " + count);


        System.out.println("\nFind all:");
        DBCursor cursor = collection.find(builder.get());
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
