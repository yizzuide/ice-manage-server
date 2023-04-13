package com.yizzuide.ice.api.mapper;

import com.github.yizzuide.milkomeda.sirius.RefMatcher;
import com.yizzuide.ice.api.domain.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * SysUserRoleMapper
 * @author yizzuide
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    /**
     * 根据用户id查找角色
     * @param uid   用户id
     * @return  角色id列表
     */
    List<Long> findRoleListByUserId(Long uid);
}




