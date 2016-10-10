package com.gnut.rest.controller;

import com.gnut.mongo.MongoDAOImpl;
import com.gnut.rest.model.Profile;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by guitarnut on 10/10/16.
 */
@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private MongoDAOImpl mongoDAO;

    private static final String COLLECTION = "users";
    private static final String ERROR_PARAMS = "Invalid or missing parameters";

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST)
    public Map<String, Object> createUser(@RequestBody Map<String, Object> profileMap) {
        Map<String, Object> response = new LinkedHashMap<>();

        if(!validateProfile(profileMap, response)) {
            return response;
        }

        Document profile = Profile.build(profileMap);
        mongoDAO.addDocument(COLLECTION, profile);
        response.put("profile", profile.toJson());
        response.put("message", "Profile created successfully");
        return response;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public Map<String, Object> getUserById(@PathVariable("userId") String userId) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("profile", mongoDAO.getDocumentByRootKey(COLLECTION, "userId", userId).toJson());
        return response;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
    public Map<String, String> deleteUserById(@PathVariable("userId") String userId) {
        mongoDAO.deleteDocumentById(COLLECTION, userId);
        Map<String, String> response = new HashMap<String, String>();
        response.put("message", "User deleted successfully");
        return response;
    }

    private Boolean validateProfile(Map<String, Object> profileMap, Map<String, Object> response) {
        return true;
        /*
        Boolean valid = profileMap.get("username") != null && profileMap.get("password") != null;

        if(!valid) {
            response.put("error", ERROR_PARAMS);
        }

        return valid;
                 */

    }
}

