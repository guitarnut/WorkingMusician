package com.gnut.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

/**
 * Created by guitarnut on 10/9/16.
 */
@Component
public class MongoDAOImpl implements MongoDAO {

    private static final MongoClient client = new MongoClient("localhost", 27017);
    private static final MongoDatabase db = client.getDatabase("workingmusician");

    //coll.find().maxTime(1, SECONDS).count();


    public JSONArray getDocuments(String collection) {
        MongoCursor cursor = db.getCollection(collection).find().iterator();
        JSONArray results = new JSONArray();

        try {
            while(cursor.hasNext()) {
                Document record = (Document) cursor.next();
                results.put(record.toJson());
            }
        } finally {
            cursor.close();
        }

        return results;
    }

    public JSONObject getDocumentById(String collection, String id) {
        MongoCursor cursor = db.getCollection(collection).find(new BasicDBObject("_id", id)).iterator();
        JSONObject results = new JSONObject();

        try {
            while(cursor.hasNext()) {
                Document record = (Document) cursor.next();
                results = new JSONObject(record.toJson());
            }
        } finally {
            cursor.close();
        }

        return results;
    }

    public void deleteDocumentById(String collection, String id) {
        //db.getCollection(collection).deleteOne(new BsonDocument({"sdf":1}));
    }

    public String addDocument(String collection, Document document) {
        MongoCollection coll = db.getCollection(collection);
        coll.insertOne(document);
        String username = document.get("username").toString();
        MongoCursor cursor = coll.find(new BasicDBObject("username", username)).iterator();
        String userJSON = "";

        try {
            while(cursor.hasNext()) {
                Document record = (Document) cursor.next();
                userJSON = record.get("_id").toString();
            }
        } finally {
            cursor.close();
        }

        return userJSON;
    }

}