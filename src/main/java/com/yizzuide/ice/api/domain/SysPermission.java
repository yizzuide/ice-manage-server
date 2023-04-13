package com.yizzuide.ice.api.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.yizzuide.milkomeda.crust.CrustPermission;
import com.github.yizzuide.milkomeda.sirius.PrefectType;
import com.github.yizzuide.milkomeda.sirius.QueryMatcher;
import com.github.yizzuide.milkomeda.sirius.RefMatcher;
import lombok.Data;

/**
 * 权限
 * @author yizzuide
 */
@TableName(value ="sys_permission")
@Data
public class SysPermission implements CrustPermission, Serializable {
    /**
     * 权限编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 权限名称
     */
    // 分布查询匹配字段
    @QueryMatcher(prefect = PrefectType.LIKE)
    private String label;

    /**
     * 父权限ID
     */
    // 自引用匹配，用于检测引用删除
    @RefMatcher
    private Long parentId;

    /**
     * 父权限名
     */
    private String parentName;

    /**
     * 授权标识符
     */
    private String code;

    /**
     * 路由地址
     */
    @JsonIgnore // 忽略与接口CrustPermission不同的字段，交由接口适配
    private String path;

    /**
     * 路由名称
     */
    @JsonIgnore
    private String name;

    /**
     * 授权路径
     */
    @JsonIgnore
    private String url;

    /**
     * 类型（0-目录，1-菜单，2-按钮）
     */
    private Integer type;

    /**
     * 图标
     */
    private String icon;

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
     * 描述
     */
    @JsonIgnore
    private String remark;

    /**
     * 排序
     */
    @JsonIgnore
    private Integer orderNum;

    /**
     * 是否已删除（0-否，1-是）
     */
    @JsonIgnore
    private Integer isDelete;

    /**
     * 子权限列表
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField(exist = false)
    private List<CrustPermission> children;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    @Override
    public void setRoutePath(String routePath) {
        // 设置回原字段
        this.path = routePath;
    }

    @Override
    public String getRoutePath() {
        return path;
    }

    @Override
    public void setRouteName(String routeName) {
        this.name = routeName;
    }

    @Override
    public String getRouteName() {
        return name;
    }

    @Override
    public void setComponentPath(String componentPath) {
        this.url = componentPath;
    }

    @Override
    public String getComponentPath() {
        return this.url;
    }

    @Override
    public int getOrder() {
        return orderNum == null ? 0 : orderNum;
    }

    @Override
    public void setOrder(int order) {
        this.orderNum = order;
    }
}