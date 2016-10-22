package com.gnut.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gnut.rest.model.UserModel;
import com.gnut.rest.mysql.UserDao;
import com.gnut.utils.SimpleMD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by guitarnut on 9/22/16.
 */

@RestController
@RequestMapping("/user")
public class UserController {

    private static final String ERROR_PARAMS = "Invalid or missing parameters";
    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private UserDao userDao;

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, String> loginUser(@RequestBody Map<String, String> userMap) {
        Map<String, String> response = new LinkedHashMap<>();

        if(!validateUser(userMap, response)) {
            return response;
        }

        UserModel user = userDao.findByUsername(userMap.get("username"));

        if(user != null) {
            if(user.getPassword().equals(SimpleMD5Encoder.encode(userMap.get("password")))) {
                response.put("id", Long.toString(user.getId()));
            } else {
                response.put("message", "Login failed");
            }
        } else {
            response.put("message", "Username not found");
        }

        return response;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST)
    public Map<String, String> createUser(@RequestBody Map<String, String> userMap) {
        Map<String, String> response = new LinkedHashMap<>();

        if(!validateUser(userMap, response)) {
            return response;
        }

        String hashPassword = SimpleMD5Encoder.encode(userMap.get("password"));
        userMap.put("password", hashPassword);

        try {
            UserModel user = mapper.convertValue(userMap, UserModel.class);
            userDao.save(user);
            response.put("user", mapper.writeValueAsString(user));
            response.put("message", "UserModel created successfully");
        } catch (Exception e) {
            response.put("message", ERROR_PARAMS);
        }

        return response;
    }
/*
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
        response.put("message", "UserModel deleted successfully");
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

    */

    private Boolean validateUser(Map<String, String> userMap, Map<String, String> response) {
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
        UserModel user = new UserModel(username, SimpleMD5Encoder.encode(username + password));
        user.setId(userId);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "UserModel Updated successfully");
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