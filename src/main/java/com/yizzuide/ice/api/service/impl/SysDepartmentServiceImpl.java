package com.yizzuide.ice.api.service.impl;

import com.github.yizzuide.milkomeda.sirius.PageableService;
import com.yizzuide.ice.api.domain.SysDepartment;
import com.yizzuide.ice.api.mapper.SysDepartmentMapper;
import com.yizzuide.ice.api.service.SysDepartmentService;
import org.springframework.stereotype.Service;

/**
 * SysDepartmentServiceImpl
 * @author yizzuide
 */
@Service
public class SysDepartmentServiceImpl extends PageableService<SysDepartmentMapper, SysDepartment>
    implements SysDepartmentService {

    /*@Override
    public UniformPage<SysDepartment> selectByPage(QueryPageData<SysDepartment> queryPageData) {
        Page<SysDepartment> page = new Page<>();
        page.setCurrent(queryPageData.getPageStart());
        page.setSize(queryPageData.getPageSize());

        // 链式调用+对象lambda方法引用方式
        LambdaQueryChainWrapper<SysDepartment> wrapper = new LambdaQueryChainWrapper<>(this.baseMapper);
        Page<SysDepartment> recordPage = wrapper.like(Objects.nonNull(queryPageData.getEntity()) &&
                        Objects.nonNull(queryPageData.getEntity().getDepartmentName()),
                        SysDepartment::getDepartmentName, queryPageData.getEntity().getDepartmentName())
                .ge(Objects.nonNull(queryPageData.getStartDate()), SysDepartment::getCreateTime, queryPageData.getStartDate())
                .le(Objects.nonNull(queryPageData.getEndDate()), SysDepartment::getCreateTime, queryPageData.getEndDate())
                .page(page);

        // QueryWrapper表字段方式
        //QueryWrapper<SysDepartment> queryWrapper = new QueryWrapper<>();
        //if (queryPageData.getEntity() != null && StringUtils.isNotBlank(queryPageData.getEntity().getDepartmentName())) {
            //queryWrapper.like("department_name", queryPageData.getEntity().getDepartmentName());
        //}
        //if (queryPageData.getStartDate() != null) {
            //queryWrapper.ge("create_time", queryPageData.getStartDate());
        //}
        //if (queryPageData.getEndDate() != null) {
            //queryWrapper.le("create_time", queryPageData.getEndDate());
        //}
        //Page<SysDepartment> recordPage = this.baseMapper.selectPage(page, queryWrapper);

        UniformPage<SysDepartment> uniformPage = new UniformPage<>();
        uniformPage.setTotalSize(recordPage.getTotal());
        uniformPage.setPageCount(recordPage.getPages());
        List<SysDepartment> records = recordPage.getRecords();
        records.sort(Comparator.comparingInt(SysDepartment::getOrderNum));
        uniformPage.setList(records);
        return uniformPage;
    }*/
}




