package com.yizzuide.ice.api.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.yizzuide.milkomeda.sirius.PrefectType;
import com.github.yizzuide.milkomeda.sirius.QueryMatcher;
import com.github.yizzuide.milkomeda.sirius.RefMatcher;
import lombok.Data;
import org.springframework.core.Ordered;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 部门
 * @author yizzuide
 */
@TableName(value ="sys_department")
@Data
public class SysDepartment implements Serializable, Ordered {
    /**
     * 部门编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 部门名称
     */
    // 分布查询匹配字段
    @QueryMatcher(prefect = PrefectType.LIKE)
    private String departmentName;

    /**
     * 部门电话
     */
    private String phone;

    /**
     * 部门地址
     */
    private String address;

    /**
     * 所属部门编号
     */
    // 自引用匹配，用于检测引用删除
    @RefMatcher
    private Long pid;

    /**
     * 所属部门名称
     */
    private String parentName;

    /**
     * 创建时间
     */
    @QueryMatcher(prefect = PrefectType.PageDate)
    // 前端传入格式：yyyy-MM-dd HH:mm:ss
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonIgnore
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 排序
     */
    @QueryMatcher(prefect = PrefectType.OrderByPost)
    private Integer orderNum;

    /**
     * 是否删除（0-未删除 1-删除）
     */
    @JsonIgnore
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Override
    public int getOrder() {
        return orderNum;
    }
}