package com.gnut.rest.model;

import com.gnut.utils.SimpleMD5Encoder;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Map;

/**
 * Created by guitarnut on 9/22/16.
 */

@org.springframework.data.mongodb.core.mapping.Document(collection = "users")
public class User {
    public static Document build(Map<String, Object> userMap) {
        //Document foo = new Document();
        //foo.putAll(userMap);
        return new Document("username", userMap.get("username").toString())
                .append("password", SimpleMD5Encoder.encode(userMap.get("username").toString() + userMap.get("password").toString()))
                .append("_id", new ObjectId().toString());
    }
}