package com.yizzuide.ice.api.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.github.yizzuide.milkomeda.sirius.PrefectType;
import com.github.yizzuide.milkomeda.sirius.QueryMatcher;
import lombok.Data;

/**
 * 角色
 * @author yizzuide
 */
@TableName(value ="sys_role")
@Data
public class SysRole implements Serializable {
    /**
     * 角色编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色名
     */
    @QueryMatcher(prefect = PrefectType.LIKE)
    private String roleName;

    /**
     * 创建人
     */
    private Long createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 角色描述
     */
    private String remark;

    /**
     * 是否已删除（0-否，1-是）
     */
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}