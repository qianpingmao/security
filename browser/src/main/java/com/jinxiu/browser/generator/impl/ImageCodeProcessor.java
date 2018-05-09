package com.jinxiu.browser.generator.impl;

import com.jinxiu.browser.model.ImageCode;
import com.jinxiu.browser.model.ValidateCode;
import com.sun.imageio.plugins.common.ImageUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.io.IOException;

@Component
public class ImageCodeProcessor extends AbstractValidateCodeProcessor {

    @Override
    void send(ServletWebRequest request, ValidateCode validateCode) throws IOException {
        ImageIO.write(((ImageCode) validateCode).getImage(), "JPEG", request.getResponse().getOutputStream());
    }
}
