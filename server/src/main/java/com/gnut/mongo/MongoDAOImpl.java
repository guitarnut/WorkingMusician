package com.gnut.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.JSONArray;
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
        return saveResultsToJSONArray(db.getCollection(collection).find().iterator());
    }

    public Document getDocumentById(String collection, String id) {
        return saveResultToDocument(db.getCollection(collection).find(new BasicDBObject("_id", id)).iterator());
    }

    public Document getDocumentByRootKey(String collection, String key, String keyValue) {
        return saveResultToDocument(db.getCollection(collection).find(new BasicDBObject(key, keyValue)).iterator());
    }

    public void deleteDocumentById(String collection, String id) {
        Document record = saveResultToDocument(db.getCollection(collection).find(new BasicDBObject("_id", id)).iterator());

        if(record.containsKey("_id")) {
            db.getCollection(collection).deleteOne(record);
        }
    }

    public void addDocument(String collection, Document document) {
        db.getCollection(collection).insertOne(document);
    }

    private Document saveResultToDocument(MongoCursor cursor) {
        Document record = new Document();

        try {
            while(cursor.hasNext()) {
                record = (Document) cursor.next();
            }
        } finally {
            cursor.close();
        }

        return record;
    }

    private JSONArray saveResultsToJSONArray(MongoCursor cursor) {
        JSONArray records = new JSONArray();

        try {
            while(cursor.hasNext()) {
                Document record = (Document) cursor.next();
                records.put(record.toJson());
            }
        } finally {
            cursor.close();
        }

        return records;
    }

}