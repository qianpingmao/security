package com.jinxiu.browser.generator.impl;

import com.jinxiu.browser.generator.ValidateCodeGenerator;
import com.jinxiu.browser.model.ImageCode;
import com.jinxiu.properties.SecuritysProperties;
import org.apache.commons.lang.math.RandomUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageCodeGenerator implements ValidateCodeGenerator {

    private SecuritysProperties securitysProperties;

    public ImageCodeGenerator(SecuritysProperties securitysProperties) {
        this.securitysProperties = securitysProperties;
    }

    @Override
    public ImageCode generate() {
            BufferedImage image = new BufferedImage(securitysProperties.getCode().getImageCode().getWidth(),
                    securitysProperties.getCode().getImageCode().getHeight(),BufferedImage.TYPE_INT_RGB);

            Graphics g = image.getGraphics();

            g.setColor(getRandomColor(200,250));
            g.fillRect(0,0,image.getWidth(),image.getHeight());

            g.setFont(new Font("Times New Roman",Font.ITALIC,20));
            g.setColor(getRandomColor(160,200));
            for (int i = 0;i < 155;i++) {
                int x = RandomUtils.nextInt(image.getWidth());
                int y = RandomUtils.nextInt(image.getHeight());

                int x1 = x + RandomUtils.nextInt(12);
                int y1 = y + RandomUtils.nextInt(12);

                g.drawLine(x,y,x1,y1);
            }

            String checkCode= "";
            for (int i = 0;i < securitysProperties.getCode().getImageCode().getLength();i++) {
                String s = RandomUtils.nextInt(10) + "";
                checkCode += s;
                g.setColor(getRandomColor(20,110));

                g.drawString(s,13 * i + 6,16);
            }
            g.dispose();

            return new ImageCode(checkCode,image,securitysProperties.getCode().getImageCode().getExpire());
        }


    private Color getRandomColor(int fc,int bc) {
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;

        int r = fc + RandomUtils.nextInt(bc - fc);
        int g = fc + RandomUtils.nextInt(bc - fc);
        int b = fc + RandomUtils.nextInt(bc - fc);

        return new Color(r,g,b);
    }
}
