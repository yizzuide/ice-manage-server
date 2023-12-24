package com.yizzuide.ice.api.extent;

import com.github.yizzuide.milkomeda.universe.context.WebContext;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

/**
 * KaptchaHelper
 *
 * @author yizzuide
 * Create at 2023/12/24 18:03
 */
public class KaptchaHelper {
    public static void out(DefaultKaptcha defaultKaptcha) throws IOException {
        String text = defaultKaptcha.createText();
        Objects.requireNonNull(WebContext.getRequestAttributes()).getRequest().getSession().setAttribute("Kaptcha-Code", text);

        var response = WebContext.getRawResponse();
        // 设置请求头为输出图片类型
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        response.setHeader(HttpHeaders.PRAGMA, "No-cache");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");
        response.setDateHeader(HttpHeaders.EXPIRES, 0);
        OutputStream outputStream = response.getOutputStream();
        BufferedImage image = defaultKaptcha.createImage(text);
        ImageIO.write(image, "png", outputStream);
    }

    public static void verify(String code) {
        var origCode = Objects.requireNonNull(WebContext.getRequestAttributes()).getRequest().getSession().getAttribute("Kaptcha-Code");
        if (!Objects.equals(origCode, code)) {
            throw new KaptchaVerifyException();
        }
    }
}
