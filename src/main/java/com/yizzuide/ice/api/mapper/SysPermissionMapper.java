package com.yizzuide.ice.api.mapper;

import com.github.yizzuide.milkomeda.sirius.RefMatcher;
import com.yizzuide.ice.api.domain.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * SysPermissionMapper
 * @author yizzuide
 */
// 外引用匹配，用于检测引用删除
@RefMatcher(foreignMapper = SysRolePermissionMapper.class, foreignField = "permission_id")
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
    /**
     * 根据用户id查询
     * @param userId 用户id
     * @return SysPermission list
     */
    List<SysPermission> findPermissionByUserId(Long userId);
}




