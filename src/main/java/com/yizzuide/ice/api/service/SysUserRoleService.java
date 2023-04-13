package com.yizzuide.ice.api.service;

import com.github.yizzuide.milkomeda.sirius.IPageableService;
import com.yizzuide.ice.api.domain.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * SysUserRoleService
 * @author yizzuide
 */
public interface SysUserRoleService extends IPageableService<SysUserRole> {
    /**
     * 根据用户id查找角色
     * @param uid   用户id
     * @return  角色id列表
     */
    List<Long> findRoleListByUserId(Long uid);
}
