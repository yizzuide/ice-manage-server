package com.yizzuide.ice.api.service;

import com.github.yizzuide.milkomeda.sirius.IPageableService;
import com.yizzuide.ice.api.domain.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * SysUserService
 * @author yizzuide
 */
public interface SysUserService extends IPageableService<SysUser> {
    /**
     * 根据用户名查找用户
     * @param username  用户名
     * @return  SysUser
     */
    SysUser findUserByUsername(String username);
}
