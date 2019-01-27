package com.pahlsoft.mongodb.test.find;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

public class DotNotationTest {
    public static void main(String [] args) throws UnknownHostException {
        System.out.println("Start Testing");

        MongoClient  client = new MongoClient(new ServerAddress("192.168.0.14",27017));
        DB db = client.getDB("course");
        DBCollection collection = db.getCollection("DotNotationTest");
        collection.drop();
        Random rand = new Random();

        for (int i = 0; i<10; i++) {
            collection.insert(
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

        QueryBuilder builder = QueryBuilder.start("start.x").greaterThan(50);

        DBCursor cursor = collection.find(builder.get());

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
