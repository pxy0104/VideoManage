package com.pxy.ggkt.vod.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pxy.ggkt.exception.GgktException;
import com.pxy.ggkt.result.Result;
import com.pxy.ggkt.vo.vod.TeacherQueryVo;
import com.pxy.ggkt.model.vod.Teacher;
import com.pxy.ggkt.vod.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author pxy
 * @since 2023-03-15
 */
@Api(tags = "管理讲师") //总的注解
@RestController
@RequestMapping("/admin/vod/teacher")
//@CrossOrigin
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    //http://localhost:8301/admin/vod/teacher/findAll
    //1.查询所有讲师
    @ApiOperation("查询所有讲师")
    @GetMapping("findAll")
    public Result findAllTeacher() {
//        List<Teacher> list = teacherService.list();
        List<Teacher> list = teacherService.list();
//        try{
//            int i = 1/0;   //custom exception test
//        }catch (Exception e){
//            throw new GgktExce
//            010406
//            ption(200,"custom exception handler");
//        }

//        int i = 1/0;   //global exception test
        return Result.ok(list);
    }

    @ApiOperation("逻辑删除讲师")  //单个操作注解
    @DeleteMapping("remove/{id}")
    public Result removeTeacher(@ApiParam(name = "id", value = "ID") @PathVariable Long id) {
        boolean isSuccess = teacherService.removeById(id);
        if (isSuccess) {
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }

    }

    //3.条件查询分页接口  required = false,提交数据格式以json格式提交；条件值可以为空；要和post一起提交
    @ApiOperation("条件查询分页")
    @PostMapping("findQueryPage/{current}/{limit}")
    public Result findPage(@PathVariable long current,
                           @PathVariable long limit,
                           @RequestBody(required = false) TeacherQueryVo teacherQueryVo) {
        //创建page对象
        Page<Teacher> pageParam = new Page<>(current, limit);

        //如果讲师数据为空
        if (teacherQueryVo == null) {
            //调用方法分页查询
            Page<Teacher> Page = teacherService.page(pageParam, null);
//            List<Teacher> list = teacherService.list();
            return Result.ok(Page);
        } else {
            String name = teacherQueryVo.getName();
            Integer level = teacherQueryVo.getLevel();
            String joinDateBegin = teacherQueryVo.getJoinDateBegin();
            String joinDateEnd = teacherQueryVo.getJoinDateEnd();
            //进行非空判断，条件封装
            QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
            if (!StringUtils.isEmpty(name)) {
                wrapper.like("name", name);
            }
            if (!StringUtils.isEmpty(level)) {
                wrapper.like("level", level);
            }
            if (!StringUtils.isEmpty(joinDateBegin)) {
                wrapper.ge("join_date", joinDateBegin);
            }
            if (!StringUtils.isEmpty(joinDateEnd)) {
                wrapper.le("join_date", joinDateEnd);
            }
            Page<Teacher> page = teacherService.page(pageParam, wrapper);
            return Result.ok(page);
        }
    }


    //添加讲师
    @ApiOperation("add vocalist")
    @PostMapping("saveTeacher")
    public Result saveTeacher(@RequestBody Teacher teacher) {
        boolean isSuccess = teacherService.save(teacher);
//        return Result.ok(isSuccess);
        if (isSuccess) {
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }

    //修改-根据id查询
    @ApiOperation("update teacher by id")
    @GetMapping("getTeacher/{id}")
    public Result getTeacher(@PathVariable Long id) {
        Teacher teacher = teacherService.getById(id);
        return Result.ok(teacher);
    }

    //修改-实现修改
    @ApiOperation("update teacher")
    @PostMapping("updateTeacher")
    public Result updateTeacher(@RequestBody Teacher teacher) {
        boolean isSuccess = teacherService.updateById(teacher);
        if (isSuccess) {
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }

    //批量删除讲师接口
    @ApiOperation("batch delete teacher by id_list")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        boolean isSuccess = teacherService.removeByIds(idList);
        if (isSuccess) {
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }
}

