package com.gnut.mongo;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by guitarnut on 10/9/16.
 */
public interface MongoDAO {

    JSONArray getDocuments(String collection);

    JSONObject getDocumentById(String collection, String id);

    void deleteDocumentById(String collection, String id);

    String addDocument(String collection, Document document);
}
