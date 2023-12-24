package com.yizzuide.ice.api.controller;

import com.github.yizzuide.milkomeda.comet.core.CometParam;
import com.github.yizzuide.milkomeda.crust.CrustContext;
import com.github.yizzuide.milkomeda.crust.CrustPermission;
import com.github.yizzuide.milkomeda.crust.CrustUserInfo;
import com.github.yizzuide.milkomeda.hydrogen.uniform.ResultVO;
import com.github.yizzuide.milkomeda.hydrogen.uniform.UniformPage;
import com.github.yizzuide.milkomeda.hydrogen.uniform.UniformQueryPageData;
import com.github.yizzuide.milkomeda.hydrogen.uniform.UniformResult;
import com.yizzuide.ice.api.domain.SysPermission;
import com.yizzuide.ice.api.domain.SysUser;
import com.yizzuide.ice.api.service.SysPermissionService;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * SysMenuController
 *
 * @author yizzuide
 * <br>
 * Create at 2022/10/19 01:51
 */
@RequestMapping("/manage/menu")
@RestController
public class SysPermissionController {

    @Resource
    private SysPermissionService sysPermissionService;

    @PreAuthorize("@crust.permitAny('sys:menu:select')")
    @GetMapping("tree")
    public ResultVO<List<CrustPermission>> permTree() {
        CrustUserInfo<SysUser, CrustPermission> userInfo = CrustContext.get().getUserInfo(SysUser.class);
        List<CrustPermission> permissionList = CrustPermission.buildPermTree(userInfo.getPermissionList(), userInfo.getPermClass(), 0L);
        return UniformResult.ok(permissionList);
    }

    @RequestMapping("list")
    public ResultVO<?> queryPage(@CometParam UniformQueryPageData<SysPermission> queryPageData) {
        UniformPage<SysPermission> uniformPage = sysPermissionService.selectByPage(queryPageData);
        return UniformResult.ok(uniformPage);
    }

    @GetMapping("all")
    public ResultVO<List<CrustPermission>> queryAll() {
        CrustUserInfo<SysUser, CrustPermission> userInfo = CrustContext.get().getUserInfo(SysUser.class);
        List<CrustPermission> permissionList = CrustPermission.buildPermTree(sysPermissionService.list().stream().map(perm -> (CrustPermission) perm).collect(Collectors.toList()), userInfo.getPermClass(), 0L);
        return UniformResult.ok(permissionList);
    }

    @PostMapping("add")
    public ResultVO<?> save(@CometParam SysPermission permission) {
        sysPermissionService.save(permission);
        return UniformResult.ok(null);
    }

    @PutMapping("update")
    public ResultVO<?> update(@CometParam SysPermission permission) {
        sysPermissionService.updateById(permission);
        return UniformResult.ok(null);
    }

    @DeleteMapping("del")
    public ResultVO<?> remove(Long id) {
        SysPermission permission = new SysPermission();
        permission.setId(id);
        boolean isRemoved = sysPermissionService.removeBeforeCheckRef(permission);
        if (!isRemoved) {
            return UniformResult.error("23", "当前记录被引用，不能删除！");
        }
        return UniformResult.ok(null);
    }

}
