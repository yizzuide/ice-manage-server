package com.yizzuide.ice.api.service.impl;

import com.github.yizzuide.milkomeda.sirius.PageableService;
import com.yizzuide.ice.api.domain.SysPermission;
import com.yizzuide.ice.api.service.SysPermissionService;
import com.yizzuide.ice.api.mapper.SysPermissionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SysPermissionServiceImpl
 * @author yizzuide
 */
@Service
public class SysPermissionServiceImpl extends PageableService<SysPermissionMapper, SysPermission>
    implements SysPermissionService{

    @Override
    public List<SysPermission> findPermissionByUserId(Long userId) {
        return baseMapper.findPermissionByUserId(userId);
    }

    @Override
    public List<SysPermission> findPermissionByRoleIds(List<Long> roleIds) {
        return baseMapper.findPermissionByRoleIds(roleIds);
    }

}




