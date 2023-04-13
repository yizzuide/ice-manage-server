package com.yizzuide.ice.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yizzuide.milkomeda.sirius.PageableService;
import com.yizzuide.ice.api.domain.SysDepartment;
import com.yizzuide.ice.api.domain.SysPermission;
import com.yizzuide.ice.api.domain.SysRolePermission;
import com.yizzuide.ice.api.mapper.SysRolePermissionMapper;
import com.yizzuide.ice.api.service.SysPermissionService;
import com.yizzuide.ice.api.mapper.SysPermissionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

}




