package com.yizzuide.ice.api.controller;

import com.github.yizzuide.milkomeda.comet.core.CometParam;
import com.github.yizzuide.milkomeda.crust.CrustContext;
import com.github.yizzuide.milkomeda.crust.CrustMenu;
import com.github.yizzuide.milkomeda.crust.CrustPermission;
import com.github.yizzuide.milkomeda.crust.CrustUserInfo;
import com.github.yizzuide.milkomeda.hydrogen.uniform.ResultVO;
import com.github.yizzuide.milkomeda.hydrogen.uniform.UniformPage;
import com.github.yizzuide.milkomeda.hydrogen.uniform.UniformQueryPageData;
import com.github.yizzuide.milkomeda.hydrogen.uniform.UniformResult;
import com.yizzuide.ice.api.domain.*;
import com.yizzuide.ice.api.service.SysUserRoleService;
import com.yizzuide.ice.api.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * SysUserController
 *
 * @author yizzuide
 * <br>
 * Create at 2022/10/15 14:34
 */
@RequestMapping("manage/user")
@RestController
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("info")
    public ResultVO<Map<String, Object>> userInfo() {
        CrustUserInfo<SysUser, CrustPermission> userInfo = CrustContext.get().getUserInfo(SysUser.class);
        SysUser entity = userInfo.getEntity();
        Map<String, Object> body = new HashMap<>();
        body.put("uid", userInfo.getUidLong());
        body.put("nickname", entity.getNickName());
        body.put("avatar", entity.getAvatar());
        body.put("phone", entity.getPhone());
        body.put("email", entity.getEmail());
        body.put("department", entity.getDepartmentName());
        body.put("isAdmin", entity.getIsAdmin());
        body.put("perms", userInfo.getPermissionList().stream().map(CrustPermission::getCode).collect(Collectors.toList()));
        return UniformResult.ok(body);
    }

    @GetMapping("menu")
    public ResultVO<List<CrustMenu>> userMenu() {
        CrustUserInfo<SysUser, CrustPermission> userInfo = CrustContext.get().getUserInfo(SysUser.class);
        List<CrustMenu> menuList = CrustPermission.buildMenuTree(userInfo.getPermissionList(), 0L);
        return UniformResult.ok(menuList);
    }

    @PreAuthorize("@crust.permitAny('sys:user:select')")
    @RequestMapping("list")
    public ResultVO<UniformPage<SysUser>> queryPage(@CometParam UniformQueryPageData<SysUser> queryPageData) {
        return UniformResult.ok(sysUserService.selectByPage(queryPageData));
    }

    @GetMapping("all")
    public ResultVO<List<SysUser>> queryAll() {
        return UniformResult.ok(sysUserService.list());
    }

    @GetMapping("find")
    public ResultVO<List<Long>> findRoleIds(Long uid) {
        return UniformResult.ok(sysUserRoleService.lambdaQuery().eq(SysUserRole::getUserId, uid)
                .list().stream().map(SysUserRole::getRoleId).collect(Collectors.toList()));
    }

    @PostMapping("add")
    public ResultVO<?> save(@CometParam SysUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        sysUserService.save(user);
        return UniformResult.ok(null);
    }

    @PutMapping("update")
    public ResultVO<?> update(@CometParam SysUser user) {
        if(StringUtils.isNotBlank(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        sysUserService.updateById(user);
        return UniformResult.ok(null);
    }

    @DeleteMapping("del")
    public ResultVO<?> remove(Long id) {
        sysUserService.removeById(id);
        return UniformResult.ok(null);
    }

    @PostMapping("assign")
    public ResultVO<?> assignRole(Long uid, @RequestParam("roleIds") List<Long> roleIds) {
        sysUserRoleService.assignAuthority(uid, roleIds, SysUserRole::getUserId,
                (roleId) -> new SysUserRole(uid, (Long) roleId));
        return UniformResult.ok(null);
    }

}
