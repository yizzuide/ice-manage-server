package com.yizzuide.ice.api.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户角色
 * @author yizzuide
 */
@TableName(value ="sys_user_role")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SysUserRole implements Serializable {
    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 角色编号
     */
    private Long roleId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}