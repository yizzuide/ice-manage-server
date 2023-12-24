package com.yizzuide.ice.api.service.impl;

import com.github.yizzuide.milkomeda.crust.CrustEntity;
import com.github.yizzuide.milkomeda.crust.CrustPerm;
import com.github.yizzuide.milkomeda.crust.CrustPermission;
import com.github.yizzuide.milkomeda.crust.CrustUserDetailsService;
import com.yizzuide.ice.api.domain.SysPermission;
import com.yizzuide.ice.api.service.SysPermissionService;
import com.yizzuide.ice.api.service.SysUserRoleService;
import com.yizzuide.ice.api.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * UserDetailsService
 *
 * @author yizzuide
 * <br />
 * Create at 2022/10/07 23:44
 */
@Service
public class UserDetailsService extends CrustUserDetailsService {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysPermissionService sysPermissionService;

    @Resource
    private SysUserRoleService sysUserRoleService;

    @Override
    protected CrustEntity findEntityByUsername(String username) {
        return sysUserService.findUserByUsername(username);
    }

    @Override
    protected CrustPerm findPermissionsById(Serializable uid) {
        List<Long> roleIds = sysUserRoleService.findRoleListByUserId((Long) uid);
        boolean isAdmin = roleIds.contains(1L);
        List<SysPermission> permissions;
        // 如果是admin，查询所有权限列表
        if (isAdmin) {
            permissions = sysPermissionService.list();
        } else {
            permissions = sysPermissionService.findPermissionByUserId(Long.valueOf(uid.toString()));
        }
        return CrustPerm.builder().roleIds(roleIds).admin(isAdmin).permissionList(permissions).build();
    }

    @Override
    protected CrustEntity findEntityById(Serializable uid) {
        return sysUserService.getById(uid);
    }
}
