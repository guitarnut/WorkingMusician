package com.gnut.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gnut.rest.model.ProfileModel;
import com.gnut.rest.mysql.ProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by guitarnut on 10/10/16.
 */
@RestController
@RequestMapping("/profile")
public class ProfileController {

    private static final String ERROR_PARAMS = "Invalid or missing parameters";
    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ProfileDao profileDao;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value = "/{userId}")
    public Map<String, String> createUser(@RequestBody Map<String, String> profileMap) {
        Map<String, String> response = new LinkedHashMap<>();

        if(!validateProfile(profileMap, response)) {
            return response;
        }

        try {
            ProfileModel profile = mapper.convertValue(profileMap, ProfileModel.class);
            profileDao.save(profile);
            response.put("profile", mapper.writeValueAsString(profile));
            response.put("message", "Profile created successfully");
        } catch (Exception e) {
            response.put("message", ERROR_PARAMS);
        }

        return response;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public Map<String, String> getUserById(@PathVariable("userId") String userId) {
        Map<String, String> response = new LinkedHashMap<>();

        try {
            ProfileModel profile = profileDao.findByUserId(Long.parseLong(userId));
            if(profile != null) {
                response.put("profile", mapper.writeValueAsString(profile));
                response.put("message", "Profile found");
            } else {
                response.put("message", "Profile not found");
            }
        } catch (Exception e) {
            response.put("message", ERROR_PARAMS);
        }

        return response;
    }

    private Boolean validateProfile(Map<String, String> profileMap, Map<String, String> response) {
        return true;
    }

/*


    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
    public Map<String, String> deleteUserById(@PathVariable("userId") String userId) {
        mongoDAO.deleteDocumentById(COLLECTION, userId);
        Map<String, String> response = new HashMap<String, String>();
        response.put("message", "User deleted successfully");
        return response;
    }


                 */

}

