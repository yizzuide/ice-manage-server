package com.yizzuide.ice.api.mapper;

import com.github.yizzuide.milkomeda.sirius.RefMatcher;
import com.yizzuide.ice.api.domain.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * SysRoleMapper
 * @author yizzuide
 */
@RefMatcher(foreignMapper = SysUserRoleMapper.class, foreignField = "role_id")
public interface SysRoleMapper extends BaseMapper<SysRole> {
}




