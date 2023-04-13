package com.yizzuide.ice.api.controller;

import com.baomidou.kaptcha.Kaptcha;
import com.github.yizzuide.milkomeda.comet.core.CometParam;
import com.github.yizzuide.milkomeda.crust.CrustAnon;
import com.github.yizzuide.milkomeda.crust.CrustContext;
import com.github.yizzuide.milkomeda.crust.CrustPermission;
import com.github.yizzuide.milkomeda.crust.CrustUserInfo;
import com.github.yizzuide.milkomeda.hydrogen.uniform.ResultVO;
import com.github.yizzuide.milkomeda.hydrogen.uniform.UniformResult;
import com.yizzuide.ice.api.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    private Kaptcha kaptcha;

    @CrustAnon
    @GetMapping("/code/render")
    public void render() {
        kaptcha.render();
    }

    @ResponseBody
    @PostMapping("/login")
    public ResultVO<Map<String, Object>> login(@RequestParam Map<String, String> data) {
        String username = data.get("username");
        String password = data.get("password");
        String code = data.get("code");

        // default timeout 900 seconds
        kaptcha.validate(code);

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
