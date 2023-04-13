package com.yizzuide.ice.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yizzuide.milkomeda.sirius.PageableService;
import com.yizzuide.ice.api.domain.SysRole;
import com.yizzuide.ice.api.service.SysRoleService;
import com.yizzuide.ice.api.mapper.SysRoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SysRoleServiceImpl
 * @author yizzuide
 */
@Service
public class SysRoleServiceImpl extends PageableService<SysRoleMapper, SysRole>
    implements SysRoleService{
}




