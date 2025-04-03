package com.yizzuide.ice.api.service.impl;

import com.github.yizzuide.milkomeda.crust.*;
import com.yizzuide.ice.api.domain.SysPermission;
import com.yizzuide.ice.api.domain.SysUser;
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
    protected CrustPermDetails buildPremDetails() {
        return CrustPermDetails.builder()
                .rolesCollector((userInfo, roleList) -> {
                    // 将租户ID设置首要角色
                    //roleList.add(((SysUser) userInfo.getEntity()).getCompanyId());
                    // 添加系统角色
                    List<Long> sysRoleIds = sysUserRoleService.findRoleListByUserId(userInfo.getUidLong());
                    roleList.addAll(sysRoleIds);
                })
                // 用于多租户的角色过滤
                //.rolesFilter(roleList -> roleList.subList(1, roleList.size()))
                .adminRecognizer(roleList -> roleList.contains(1L))
                .permsCollector((roleList, isAdmin) -> {
                    // 如果是admin，查询所有权限列表
                    if (isAdmin) {
                        return sysPermissionService.list();
                    }
                    return sysPermissionService.findPermissionByRoleIds(roleList);
                }).build();
    }

    @Override
    protected CrustEntity findEntityById(Serializable uid) {
        return sysUserService.getById(uid);
    }
}
