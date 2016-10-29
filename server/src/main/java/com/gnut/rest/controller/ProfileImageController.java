package com.gnut.rest.controller;

import com.gnut.images.ImageManipulator;
import com.gnut.rest.model.ProfileModel;
import com.gnut.rest.mysql.ProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by guitarnut on 10/24/16.
 */

@RestController
@RequestMapping("/profileimage")
public class ProfileImageController {

    private final String IMAGE_PATH = "/Users/guitarnut/Documents/Projects/WorkingMusician/web/WorkingMusician/build/uploads";
    private final File dir = new File(IMAGE_PATH + File.separator + "tmpFiles");

    @Autowired
    private ProfileDao profileDao;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value = "/{userId}")
    public Map<String, Object> saveImage(@PathVariable("userId") String userId, @RequestParam("file") MultipartFile file, @RequestParam("name") String filename) {
        Map<String, Object> response = new LinkedHashMap<>();
        Long userIdVal = Long.parseLong(userId);
        ProfileModel profile = profileDao.findByUserId(userIdVal);

        if(profile == null) {
            response.put("message", "Invalid profile");
            return response;
        }

        if (!file.isEmpty()) {
            BufferedImage result = ImageManipulator.createProfileImage(file);

            if (result != null) {
                if(!saveImage(userIdVal, result, response)) {
                    return response;
                }

                profile.setProfilePicture(userId + ".jpg");
                profileDao.save(profile);
            } else {
                response.put("message", "There was a problem processing your image");
            }
        } else {
            response.put("message", "You failed to upload " + userId + " because the file was empty.");
        }

        return response;
    }

    private Boolean saveImage(Long userId, BufferedImage image, Map<String, Object> response) {
        try {
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File imageFile = new File(dir.getAbsolutePath() + File.separator + userId + ".jpg");
            ImageIO.write(image, "jpg", imageFile);

            return true;
        } catch (IOException ex) {
            response.put("message", "There was a problem saving your image");
            return false;
        }
    }

}