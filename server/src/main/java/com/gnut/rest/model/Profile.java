package com.gnut.rest.model;

import com.gnut.utils.DocumentIDGenerator;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Map;

/**
 * Created by guitarnut on 10/3/16.
 */

@org.springframework.data.mongodb.core.mapping.Document(collection = "profiles")
public class Profile {
    public static Document build(Map<String, Object> userMap) {
        Document profile = new Document();
        profile.putAll(userMap);
        DocumentIDGenerator.appendId(profile);
        return profile;
    }

}