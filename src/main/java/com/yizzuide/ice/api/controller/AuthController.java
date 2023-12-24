package com.yizzuide.ice.api.controller;

import com.github.yizzuide.milkomeda.crust.CrustAnon;
import com.github.yizzuide.milkomeda.crust.CrustContext;
import com.github.yizzuide.milkomeda.crust.CrustPermission;
import com.github.yizzuide.milkomeda.crust.CrustUserInfo;
import com.github.yizzuide.milkomeda.hydrogen.uniform.ResultVO;
import com.github.yizzuide.milkomeda.hydrogen.uniform.UniformResult;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.yizzuide.ice.api.domain.SysUser;
import com.yizzuide.ice.api.extent.KaptchaHelper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * LoginController
 *
 * @author yizzuide
 * <br />
 * Create at 2022/10/08 18:35
 */
@Controller
public class AuthController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @CrustAnon
    @GetMapping("/code/render")
    public void render(HttpServletResponse response) throws IOException { // 使用Response流写出必须添加HttpServletResponse参数，否则Spring Security抛异常
        KaptchaHelper.out(defaultKaptcha);
    }

    @ResponseBody
    @PostMapping("/login")
    public ResultVO<Map<String, Object>> login(@RequestParam Map<String, String> data) {
        String username = data.get("username");
        String password = data.get("password");
        String code = data.get("code");

        KaptchaHelper.verify(code);

        CrustUserInfo<SysUser, CrustPermission> userInfo = CrustContext.get().login(username, password, SysUser.class);
        Map<String, Object> body = new HashMap<>();
        body.put("uid", userInfo.getUidLong());
        body.put("username", userInfo.getUsername());
        body.put("token", userInfo.getToken());
        body.put("expire", userInfo.getTokenExpire());
        return UniformResult.ok(body);
    }

    @ResponseBody
    @GetMapping("/refresh")
    public ResultVO<Map<String, Object>> refreshToken() {
        CrustUserInfo<?, CrustPermission> userInfo = CrustContext.get().refreshToken();
        Map<String, Object> body = new HashMap<>();
        body.put("token", userInfo.getToken());
        body.put("expire", userInfo.getTokenExpire());
        return UniformResult.ok(body);
    }
}
