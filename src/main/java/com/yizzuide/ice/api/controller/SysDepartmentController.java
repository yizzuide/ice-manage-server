package com.yizzuide.ice.api.controller;

import com.github.yizzuide.milkomeda.comet.core.CometParam;
import com.github.yizzuide.milkomeda.hydrogen.uniform.ResultVO;
import com.github.yizzuide.milkomeda.hydrogen.uniform.UniformPage;
import com.github.yizzuide.milkomeda.hydrogen.uniform.UniformQueryPageData;
import com.github.yizzuide.milkomeda.hydrogen.uniform.UniformResult;
import com.yizzuide.ice.api.domain.SysDepartment;
import com.yizzuide.ice.api.service.SysDepartmentService;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * SysDepartmentController
 *
 * @author yizzuide
 * <br>
 * Create at 2022/10/29 19:02
 */
@RequestMapping("/manage/department")
@RestController
public class SysDepartmentController {

    @Resource
    private SysDepartmentService sysDepartmentService;

    @GetMapping("list")
    public ResultVO<?> queryPage(@CometParam UniformQueryPageData<SysDepartment> queryPageData) {
        UniformPage<SysDepartment> uniformPage = sysDepartmentService.selectByPage(queryPageData);
        return UniformResult.ok(uniformPage);
    }

    @GetMapping("all")
    public ResultVO<List<SysDepartment>> queryAll() {
        return UniformResult.ok(sysDepartmentService.list());
    }

    @PostMapping("add")
    public ResultVO<?> save(@CometParam SysDepartment department) {
        sysDepartmentService.save(department);
        return UniformResult.ok(null);
    }

    @PutMapping("update")
    public ResultVO<?> update(@CometParam SysDepartment department) {
        sysDepartmentService.updateById(department);
        return UniformResult.ok(null);
    }

    @DeleteMapping("del")
    public ResultVO<?> remove(Long id) {
        SysDepartment department = new SysDepartment();
        department.setId(id);
        boolean isRemoved = sysDepartmentService.removeBeforeCheckRef(department);
        if (!isRemoved) {
            return UniformResult.error("23", "当前记录被引用，不能删除！");
        }
        return UniformResult.ok(null);
    }
}
