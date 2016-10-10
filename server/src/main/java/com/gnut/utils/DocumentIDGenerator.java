package com.gnut.utils;

import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Created by guitarnut on 10/10/16.
 */
public class DocumentIDGenerator {
    public static Document appendId(Document document) {
        return document.append("_id", new ObjectId().toString());
    }
}
