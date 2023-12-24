package com.yizzuide.ice.api.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.yizzuide.milkomeda.crust.CrustStatefulEntity;
import com.github.yizzuide.milkomeda.sirius.PrefectType;
import com.github.yizzuide.milkomeda.sirius.QueryAutoLinker;
import com.github.yizzuide.milkomeda.sirius.QueryLinkers;
import com.github.yizzuide.milkomeda.sirius.QueryMatcher;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户
 * @author yizzuide
 */
@TableName(value ="sys_user")
@Data
public class SysUser implements CrustStatefulEntity {
    /**
     * 用户编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名称
     */
    @QueryMatcher(prefect = PrefectType.LIKE)
    private String username;

    /**
     * 登录密码
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * 用户是否过期（0-未过期，1-过期）
     */
    @JsonIgnore
    private Integer isAccountExpire;

    /**
     * 帐号是否锁定（0-未锁定，1-锁定）
     */
    @JsonIgnore
    private Integer isAccountLock;

    /**
     * 密码是否过期（0-未过期，1-过期）
     */
    @JsonIgnore
    private Integer isCredentialsExpire;

    /**
     * 帐号是否可用（0-不可用，1-可用）
     */
    private Integer isEnabled;

    /**
     * 真实姓名
     */
    @QueryMatcher(prefect = PrefectType.LIKE)
    private String realName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 部门编号
     */
    @QueryAutoLinker(links = "departName->departmentName", type = SysDepartment.class)
    private Long departmentId;

    /**
     * 部门名称
     */
    private String departmentName;


    @TableField(exist = false)
    private String departName;

    /**
     * 性别（0-男，1-女）
     */
    private Integer gender;

    /**
     * 手机号
     */
    @QueryMatcher
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户图像
     */
    private String avatar;

    /**
     * 是否为超级管理员（0-不是，1-是）
     */
    private Integer isAdmin;

    /**
     * 创建时间
     */
    @QueryMatcher(prefect = PrefectType.PageDate)
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonIgnore
    private Date updateTime;

    /**
     * 是否已删除（0-不是，1-是）
     */
    @JsonIgnore
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Override
    public Serializable getUid() {
        return id;
    }

    @Override
    public boolean accountExpired() {
        return getIsAccountExpire() == 1;
    }

    @Override
    public boolean accountLocked() {
        return getIsAccountLock() == 1;
    }

    @Override
    public boolean credentialsExpired() {
        return getIsCredentialsExpire() == 1;
    }

    @Override
    public boolean enabled() {
        return getIsEnabled() == 1;
    }
}