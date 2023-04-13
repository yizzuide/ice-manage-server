package com.yizzuide.ice.api.service.impl;

import com.github.yizzuide.milkomeda.sirius.PageableService;
import com.yizzuide.ice.api.domain.SysUserRole;
import com.yizzuide.ice.api.mapper.SysUserRoleMapper;
import com.yizzuide.ice.api.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SysUserRoleServiceImpl
 * @author yizzuide
 */
@Service
public class SysUserRoleServiceImpl extends PageableService<SysUserRoleMapper, SysUserRole>
    implements SysUserRoleService{

    @Override
    public List<Long> findRoleListByUserId(Long uid) {
        return this.baseMapper.findRoleListByUserId(uid);
    }
}




