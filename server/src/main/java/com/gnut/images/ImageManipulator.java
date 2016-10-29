package com.gnut.images;

import org.imgscalr.Scalr;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by guitarnut on 10/29/16.
 */
public class ImageManipulator {

    private static int DEFAULT_SIZE = 120;

    public static BufferedImage createProfileImage(MultipartFile file) {
        try {
            BufferedImage profileImage = ImageIO.read(file.getInputStream());

            BufferedImage result = Scalr.resize(
                    profileImage,
                    Scalr.Method.QUALITY,
                    Scalr.Mode.FIT_TO_WIDTH,
                    DEFAULT_SIZE,
                    Scalr.OP_ANTIALIAS
            );

            result = Scalr.crop(result, 0, 0, DEFAULT_SIZE, DEFAULT_SIZE);

            return result;

        } catch (IOException ex) {
            // do nothing
            String exception = ex.toString();
        }

        return null;
    }

}