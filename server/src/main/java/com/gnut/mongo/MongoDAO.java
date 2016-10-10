package com.gnut.mongo;

import org.bson.Document;
import org.json.JSONArray;

/**
 * Created by guitarnut on 10/9/16.
 */
public interface MongoDAO {

    JSONArray getDocuments(String collection);

    Document getDocumentById(String collection, String id);

    Document getDocumentByRootKey(String collection, String key, String keyValue);

    void deleteDocumentById(String collection, String id);

    void addDocument(String collection, Document document);
}
