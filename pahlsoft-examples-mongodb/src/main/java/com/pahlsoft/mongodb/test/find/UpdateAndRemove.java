package com.pahlsoft.mongodb.test.find;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;


public class UpdateAndRemove {
    public static void main(String[] args) throws UnknownHostException {

        DBCollection collection = createCollection();

        List<String> names = Arrays.asList("alice","bobby","cathy","david","ethan");
        for (String name : names) {
            collection.insert(new BasicDBObject("_id", name));
        }

        collection.update(new BasicDBObject("_id","alice"),
                new BasicDBObject("age",24));

        collection.update(new BasicDBObject("_id","frank"),
                new BasicDBObject("$set",new BasicDBObject("gender","F")), true, false);

        collection.update(new BasicDBObject(),
                new BasicDBObject("$set",new BasicDBObject("title","Dr.")), false, true);

        // Remove Alice...
        collection.remove(new BasicDBObject("_id","alice"));

        printCollection(collection);

    }

    private static void printCollection(DBCollection collection) {
        DBCursor cursor = collection.find();
        try {
            while (cursor.hasNext()) {
                DBObject cur = cursor.next();
                System.out.println("cursor = " + cur);
            }
        } finally {
            cursor.close();
        }
    }


    private static DBCollection createCollection() throws UnknownHostException {
        MongoClient client = new MongoClient(new ServerAddress("192.168.0.14",27017));
        DB db = client.getDB("course");
        DBCollection collection = db.getCollection("UpdateAndRemove");
        collection.drop();
        return collection;
    }


}
