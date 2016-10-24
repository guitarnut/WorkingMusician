package com.gnut.rest.controller;

import com.gnut.rest.constants.ErrorMessages;
import com.gnut.rest.model.GenreModel;
import com.gnut.rest.model.InstrumentModel;
import com.gnut.rest.model.ProfileModel;
import com.gnut.rest.model.VocalModel;
import com.gnut.rest.mysql.ProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by guitarnut on 10/24/16.
 */

@RestController
@RequestMapping("/profileimage")
public class ProfileImageController {

    @Autowired
    private ProfileDao profileDao;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value = "/{userId}")
    public Map<String, Object> saveImage(@PathVariable("userId") String userId, @RequestParam("file") MultipartFile file, @RequestParam("name") String filename) {
        Map<String, Object> response = new LinkedHashMap<>();

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                String rootPath = "/Users/guitarnut/Documents/Projects/WorkingMusician/web/WorkingMusician/build/uploads";

                File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath() + File.separator + userId + ".jpg");
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                response.put("message", "You successfully uploaded file=" + userId);

                ProfileModel profile = profileDao.findByUserId(Long.parseLong(userId));

                if(profile != null) {
                    profile.setProfilePicture(userId + ".jpg");
                }

                profileDao.save(profile);

            } catch (Exception e) {
                response.put("message", "You failed to upload " + userId + " => " + e.getMessage());
            }
        } else {
            response.put("message", "You failed to upload " + userId + " because the file was empty.");
        }

        return response;
    }
}