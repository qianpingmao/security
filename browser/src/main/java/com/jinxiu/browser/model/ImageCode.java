package com.jinxiu.browser.model;

import org.apache.commons.lang.time.DateUtils;

import java.awt.image.BufferedImage;
import java.util.Date;

public class ImageCode extends ValidateCode {

    private BufferedImage image;


    public ImageCode(String code, BufferedImage image, int seconds) {
        super(code,seconds);
        this.image = image;


    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
