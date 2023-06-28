package com.rabbiter.staff.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.staff.entity.Clerk;
import com.rabbiter.staff.entity.Department;
import com.rabbiter.staff.service.ClerkService;
import com.rabbiter.staff.service.DepartmentService;
import com.rabbiter.staff.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author rabbiter
 * @since 2022-12-14
 */
@RestController
@RequestMapping("/staff/department")
@CrossOrigin
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    //1.分页查询所有
    @GetMapping("listPage/{current}/{limit}")
    public R getDepartmentListPage(@PathVariable long current, @PathVariable long limit) {
        //分页 两个参数：当前页，每页数据个数
        Page<Department> departmentPage = new Page<>(current,limit);
        //查询数据放在 classifyPage 中
        departmentService.page(departmentPage,null);
        //获取总记录数
        long total = departmentPage.getTotal();
        //每页类别集合
        List<Department> departmentList = departmentPage.getRecords();
        return R.ok().data("departmentList",departmentList).data("total",total);
    }

    //3.查询所有
    @GetMapping("list")
    public R getDepartmentList() {
        List<Department> departmentList = departmentService.list(null);
        return R.ok().data("departmentList",departmentList);
    }

    //2.根据id查询
    @GetMapping("{id}")
    public R getDepartmentById(@PathVariable String id){
        Department department = departmentService.getById(id);
        if (StringUtils.isEmpty(department)){
            return R.error().message("没有此类别");
        }
        return R.ok().data("department",department);
    }
@Resource
private ClerkService clerkService;
    //4.根据id删除
    @DeleteMapping("{id}")
    public R deleteDepartmentById(@PathVariable String id){
        Clerk clerk = clerkService.getOne(
                new QueryWrapper<Clerk>().eq("department_id", id)
        );
        if(!ObjectUtils.isEmpty(clerk)) {
            return R.error().message("删除部门失败，该部门下存在职工");
        }
        boolean removeById = departmentService.removeById(id);
        if (removeById){
            return R.ok();
        }
        return R.error().message("删除失败");
    }

    //5.修改
    @PutMapping
    public R updateDepartment(@RequestBody Department department){
        boolean updateById = departmentService.updateById(department);
        if (updateById){
            return R.ok().message("修改成功");
        }
        return R.error().message("修改失败");
    }
    //6.增加
    @PostMapping
    public R addDepartment(@RequestBody Department department){
        boolean save = departmentService.save(department);

        if (save){
            return R.ok().message("增加成功");
        }
        return R.error().message("增加失败");
    }
}

