package com.yizzuide.ice.api.service;

import com.github.yizzuide.milkomeda.sirius.IPageableService;
import com.yizzuide.ice.api.domain.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * SysPermissionService
 * @author yizzuide
 */
public interface SysPermissionService extends IPageableService<SysPermission> {
    /**
     * 根据用户id查询
     * @param userId 用户id
     * @return SysPermission list
     */
    List<SysPermission> findPermissionByUserId(Long userId);
}
