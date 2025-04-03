package com.yizzuide.ice.api.mapper;

import com.yizzuide.ice.api.domain.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * SysPermissionMapper
 * @author yizzuide
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
    /**
     * 根据用户id查询
     * @param userId 用户id
     * @return SysPermission list
     */
    List<SysPermission> findPermissionByUserId(Long userId);

    /**
     * 根据用户角色id列表查询
     * @param roleIds 角色id列表
     * @return SysPermission list
     */
    List<SysPermission> findPermissionByRoleIds(List<Long> roleIds);
}




