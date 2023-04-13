package com.yizzuide.ice.api.mapper;

import com.github.yizzuide.milkomeda.sirius.RefMatcher;
import com.yizzuide.ice.api.domain.SysDepartment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * SysDepartmentMapper
 * @author yizzuide
 */
// 外引用匹配，用于检测引用删除
@RefMatcher(foreignMapper = SysUserMapper.class, foreignField = "department_id")
public interface SysDepartmentMapper extends BaseMapper<SysDepartment> {
}




