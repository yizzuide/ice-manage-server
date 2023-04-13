package com.yizzuide.ice.api.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 角色权限
 * @author yizzuide
 */
@TableName(value ="sys_role_permission")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SysRolePermission implements Serializable {
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 权限ID
     */
    private Long permissionId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}