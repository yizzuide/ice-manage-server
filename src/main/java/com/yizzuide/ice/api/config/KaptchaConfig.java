package com.yizzuide.ice.api.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * KaptchaConfig
 *
 * @author yizzuide
 * Create at 2023/12/24 18:06
 */
@Configuration
public class KaptchaConfig {
    @Bean
    public DefaultKaptcha defaultKaptcha() {
        var defaultKaptcha = new DefaultKaptcha();
        // A configuration object was created for Captcha settings.
        var config = new Config(new Properties());
        // Captcha img width
        config.getProperties().setProperty("kaptcha.image.width", "80");
        // Captcha img height
        config.getProperties().setProperty("kaptcha.image.height", "40");
        // Captcha font color
        config.getProperties().setProperty("kaptcha.textproducer.font.color", "cyan");
        // Captcha font size
        config.getProperties().setProperty("kaptcha.textproducer.font.size", "32");
        // Captcha char length
        config.getProperties().setProperty("kaptcha.textproducer.char.length", "4");
        // Captcha font style
        config.getProperties().setProperty("kaptcha.textproducer.font.names", "Arial, Courier");
        // Captcha noise color (The line intended to reduce the readability of text in the middle of the image.)
        config.getProperties().setProperty("kaptcha.noise.color", "black");
        // Captcha char string
        config.getProperties().setProperty("kaptcha.textproducer.char.string", "0123456789");
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
