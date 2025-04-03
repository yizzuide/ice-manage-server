package com.yizzuide.ice.api.extent;

import com.github.yizzuide.milkomeda.universe.context.RedisHolder;
import com.github.yizzuide.milkomeda.universe.context.WebContext;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.data.redis.core.BoundHashOperations;
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

    private static final String SESSION_KEY = "login_kaptcha_code";

    public static void out(DefaultKaptcha defaultKaptcha, String uuid) throws IOException {
        String text = defaultKaptcha.createText();
        RedisHolder.getStringRedisTemplate().boundHashOps(SESSION_KEY).put(uuid, text);

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

    public static void verify(String code, String uuid) {
        BoundHashOperations<String, Object, Object> hashOps = RedisHolder.getStringRedisTemplate().boundHashOps(SESSION_KEY);
        var origCode = hashOps.get(uuid);
        if (!Objects.equals(origCode, code)) {
            throw new KaptchaVerifyException("verify fail");
        }
        hashOps.delete(uuid);
    }
}
