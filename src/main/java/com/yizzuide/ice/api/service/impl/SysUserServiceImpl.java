package com.yizzuide.ice.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yizzuide.milkomeda.sirius.IPageableService;
import com.github.yizzuide.milkomeda.sirius.PageableService;
import com.yizzuide.ice.api.domain.SysUser;
import com.yizzuide.ice.api.service.SysUserService;
import com.yizzuide.ice.api.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

/**
 * SysUserServiceImpl
 * @author yizzuide
 */
@Service
public class SysUserServiceImpl extends PageableService<SysUserMapper, SysUser>
    implements SysUserService {

    @Override
    public SysUser findUserByUsername(String username) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        QueryWrapper<SysUser> query = new QueryWrapper<>(sysUser);
        return baseMapper.selectOne(query);
    }
}




