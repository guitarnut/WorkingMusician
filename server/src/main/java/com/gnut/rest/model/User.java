package com.gnut.rest.model;

import java.util.Map;

/**
 * Created by guitarnut on 9/22/16.
 */

@org.springframework.data.mongodb.core.mapping.Document(collection = "users")
public class User {
    public static org.bson.Document build(Map<String, Object> userMap) {
        return new org.bson.Document("username", userMap.get("username").toString())
                .append("password", userMap.get("password").toString());
    }
}
