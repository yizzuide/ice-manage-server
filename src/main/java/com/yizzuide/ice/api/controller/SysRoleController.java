package com.yizzuide.ice.api.controller;

import com.github.yizzuide.milkomeda.comet.core.CometParam;
import com.github.yizzuide.milkomeda.crust.CrustContext;
import com.github.yizzuide.milkomeda.crust.CrustPermission;
import com.github.yizzuide.milkomeda.crust.CrustUserInfo;
import com.github.yizzuide.milkomeda.hydrogen.uniform.ResultVO;
import com.github.yizzuide.milkomeda.hydrogen.uniform.UniformPage;
import com.github.yizzuide.milkomeda.hydrogen.uniform.UniformQueryPageData;
import com.github.yizzuide.milkomeda.hydrogen.uniform.UniformResult;
import com.yizzuide.ice.api.domain.*;
import com.yizzuide.ice.api.service.SysRolePermissionService;
import com.yizzuide.ice.api.service.SysRoleService;
import com.yizzuide.ice.api.service.SysUserRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SysRoleController
 *
 * @author yizzuide
 * <br>
 * Create at 2022/11/26 14:46
 */
@RequestMapping("manage/role")
@RestController
public class SysRoleController {
    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysRolePermissionService sysRolePermissionService;

    @RequestMapping("list")
    public ResultVO<?> queryPage(@CometParam UniformQueryPageData<SysRole> queryPageData) {
        UniformPage<SysRole> uniformPage = sysRoleService.selectByPage(queryPageData);
        return UniformResult.ok(uniformPage);
    }

    @GetMapping("all")
    public ResultVO<List<SysRole>> queryAll() {
        return UniformResult.ok(sysRoleService.list());
    }

    @GetMapping("find")
    public ResultVO<List<Long>> findPermIds(Long roleId) {
        return UniformResult.ok(sysRolePermissionService.lambdaQuery().eq(SysRolePermission::getRoleId, roleId)
                .list().stream().map(SysRolePermission::getPermissionId).collect(Collectors.toList()));
    }

    @PostMapping("add")
    public ResultVO<?> save(@CometParam SysRole role) {
        sysRoleService.save(role);
        return UniformResult.ok(null);
    }

    @PutMapping("update")
    public ResultVO<?> update(@CometParam SysRole role) {
        sysRoleService.updateById(role);
        return UniformResult.ok(null);
    }

    @DeleteMapping("del")
    public ResultVO<?> remove(Long id) {
        SysRole role = new SysRole();
        role.setId(id);
        boolean isRemoved = sysRoleService.removeBeforeCheckRef(role);
        if (!isRemoved) {
            return UniformResult.error("23", "当前记录被引用，不能删除！");
        }
        return UniformResult.ok(null);
    }

    @PostMapping("assign")
    public ResultVO<?> assignRole(Long roleId, @RequestParam(value = "permIds", required = false) List<Long> permIds) {
        sysRolePermissionService.assignAuthority(roleId, permIds, SysRolePermission::getRoleId,
                (permId) -> new SysRolePermission(roleId, (Long) permId));
        return UniformResult.ok(null);
    }
}
