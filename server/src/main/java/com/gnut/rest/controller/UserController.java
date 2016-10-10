package com.gnut.rest.controller;

import com.gnut.mongo.MongoDAOImpl;
import com.gnut.rest.model.User;
import com.gnut.utils.SimpleMD5Encoder;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by guitarnut on 9/22/16.
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private MongoDAOImpl mongoDAO;

    private static final String COLLECTION = "users";
    private static final String ERROR_PARAMS = "Invalid or missing parameters";

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST)
    public Map<String, Object> createUser(@RequestBody Map<String, Object> userMap) {
        Map<String, Object> response = new LinkedHashMap<>();

        if(!validateUser(userMap, response)) {
            return response;
        }

        Document user = User.build(userMap);
        mongoDAO.addDocument(COLLECTION, user);
        response.put("user", user.toJson());
        response.put("message", "User created successfully");
        return response;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public Map<String, Object> getAllUsers() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("users", mongoDAO.getDocuments(COLLECTION));
        return response;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public Map<String, Object> getUserById(@PathVariable("userId") String userId) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("user", mongoDAO.getDocumentById(COLLECTION, userId).toJson());
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

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> loginUser(@RequestBody Map<String, Object> userMap) {
        Map<String, Object> response = new LinkedHashMap<>();

        if(!validateUser(userMap, response)) {
            return response;
        }

        Document user = mongoDAO.getDocumentByRootKey(COLLECTION, "username", userMap.get("username").toString());

        if(user.containsKey("username")) {
            if(user.get("password").equals(encodePassword(userMap))) {
                response.put("id", user.get("_id"));
            } else {
                response.put("message", "Login failed");
            }
        } else {
            response.put("message", "Username not found");
        }

        return response;
    }

    @CrossOrigin
    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public Map<String, Object> isUsernameValid(@RequestBody Map<String, Object> userMap) {
        Map<String, Object> response = new LinkedHashMap<>();
        Document user = mongoDAO.getDocumentByRootKey(COLLECTION, "username", userMap.get("username").toString());

        if(!user.containsKey("username")) {
            response.put("message", "true");
        } else {
            response.put("message", "false");
        }

        return response;
    }

    private Boolean validateUser(Map<String, Object> userMap, Map<String, Object> response) {
        Boolean valid = userMap.get("username") != null && userMap.get("password") != null;

        if(!valid) {
            response.put("error", "Invalid or missing parameter values");
        }

        return valid;
    }

    private String encodePassword(Map<String, Object> userMap) {
        return SimpleMD5Encoder.encode(userMap.get("password").toString() + userMap.get("password").toString());
    }

/*



    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, value = "/{userId}")
    public Map<String, Object> editUser(
            @PathVariable("userId") String userId,
            @PathVariable("username") String username,
            @PathVariable("password") String password,
            @RequestBody Map<String, Object> userMap
    ) {
        User user = new User(username, SimpleMD5Encoder.encode(username + password));
        user.setId(userId);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "User Updated successfully");
        response.put("user", userRepository.save(user));
        return response;
    }

*/
}


/*

curl http://dev.sandbox.com:8080/user

curl -X POST -H 'Content-Type:application/json' -d '{"username":"Rick"}' http://dev.sandbox.com:8080/user

curl -X PUT -H 'Content-Type:application/json' -d '{"username":"Ricky"}' http://dev.sandbox.com:8080/user/57e49aa360087f2fc4799c26

curl -X DELETE -H 'Content-Type:application/json' http://dev.sandbox.com:8080/user/57fb9cde1d50bd710bb871b5

curl -X POST -H 'Content-Type:application/json' -d '{"username":"rick"}' http://dev.sandbox.com:8080/user/validate


 */