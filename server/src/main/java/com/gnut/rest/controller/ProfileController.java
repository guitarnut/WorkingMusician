package com.gnut.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gnut.rest.constants.ErrorMessages;
import com.gnut.rest.model.GenreModel;
import com.gnut.rest.model.InstrumentModel;
import com.gnut.rest.model.ProfileModel;
import com.gnut.rest.model.VocalModel;
import com.gnut.rest.mysql.GenreDao;
import com.gnut.rest.mysql.InstrumentDao;
import com.gnut.rest.mysql.ProfileDao;
import com.gnut.rest.mysql.VocalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by guitarnut on 10/10/16.
 */
@RestController
@RequestMapping("/profile")
public class ProfileController {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String GENRES = "genres";
    private static final String VOCALS = "vocals";
    private static final String INSTRUMENTS = "instruments";

    @Autowired
    MultipleRecordHandler multipleRecordHandler;

    @Autowired
    private ProfileDao profileDao;

    @Autowired
    private GenreDao genreDao;

    @Autowired
    private VocalDao vocalDao;

    @Autowired
    private InstrumentDao instrumentDao;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value = "/{userId}")
    public Map<String, Object> createUser(@RequestBody Map<String, Object> profileMap) {
        Map<String, Object> response = new LinkedHashMap<>();

        if (!validateProfile(profileMap, response)) {
            return response;
        }

        try {
            ProfileModel profile = mapper.convertValue(profileMap.get("profile"), ProfileModel.class);

            profileDao.save(profile);
            multipleRecordHandler.insertMultipleProfileDataRecords(GenreModel.class, profileMap, GENRES, genreDao);
            multipleRecordHandler.insertMultipleProfileDataRecords(VocalModel.class, profileMap, VOCALS, vocalDao);
            multipleRecordHandler.insertMultipleProfileDataRecords(InstrumentModel.class, profileMap, INSTRUMENTS, instrumentDao);

            response.put("message", "Profile created successfully");

        } catch (Exception e) {
            response.put("message", ErrorMessages.INVALID_PARAMETERS.toString());
        }

        return response;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public Map<String, String> getUserById(@PathVariable("userId") String userId) {
        Map<String, String> response = new LinkedHashMap<>();

        try {
            ProfileModel profile = profileDao.findByUserId(Long.parseLong(userId));
            if (profile != null) {
                response.put("profile", mapper.writeValueAsString(profile));

                List<GenreModel> genres = genreDao.findByUserId(profile.getUserId());
                response.put("genres", mapper.writeValueAsString(genres));

                List<InstrumentModel> instruments = instrumentDao.findByUserId(profile.getUserId());
                response.put("instruments", mapper.writeValueAsString(instruments));

                List<VocalModel> vocals = vocalDao.findByUserId(profile.getUserId());
                response.put("vocals", mapper.writeValueAsString(vocals));

                response.put("message", "Profile found");
            } else {
                response.put("message", "Profile not found");
            }
        } catch (Exception e) {
            response.put("message", ErrorMessages.INVALID_PARAMETERS.toString());
        }

        return response;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public Map<Long, Map<String, String>> getAllProfiles() {
        Map<Long, Map<String, String>> profiles = new HashMap<>();

        try {
            for (ProfileModel profile : profileDao.findAll()) {
                Map<String, String> response = new LinkedHashMap<>();

                response.put("profile", mapper.writeValueAsString(profile));

                List<GenreModel> genres = genreDao.findByUserId(profile.getUserId());
                response.put("genres", mapper.writeValueAsString(genres));

                List<InstrumentModel> instruments = instrumentDao.findByUserId(profile.getUserId());
                response.put("instruments", mapper.writeValueAsString(instruments));

                List<VocalModel> vocals = vocalDao.findByUserId(profile.getUserId());
                response.put("vocals", mapper.writeValueAsString(vocals));

                profiles.put(profile.getUserId(), response);
            }
        } catch (Exception e) {
            // Do nothing
        }

        return profiles;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/{userId}/viewed")
    public void updateProfileViewCount(@PathVariable("userId") String userId) {

        try {
            ProfileModel profile = profileDao.findByUserId(Long.parseLong(userId));
            if (profile != null) {
                profile.setProfileViews(profile.getProfileViews() + 1);
                profileDao.save(profile);
            }
        } catch (Exception e) {
            // do nothing
        }

    }

    private Boolean validateProfile(Map<String, Object> profileMap, Map<String, Object> response) {
        return true;
    }

}

