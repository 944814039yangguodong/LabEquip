package com.university.labequip.controller;


import cn.dev33.satoken.annotation.SaCheckRole;
import com.university.labequip.entity.Teacher;
import com.university.labequip.entity.vo.TeacherRequestVo;
import com.university.labequip.service.TeacherService;
import com.university.labequip.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Guodong
 * @since 2021-07-03
 */
@RestController
@RequestMapping("/api/teacher")
@Api(tags = "导师相关接口")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @ApiOperation("查询所有导师")
    @GetMapping("selectAllTeacher")
    public R selectAllTeacher() {
        List<String> list=teacherService.getAllTeacher();
        return R.ok().data("list",list);
    }

    @ApiOperation("管理员新增导师")
    @PostMapping("addTeacher")
    @SaCheckRole("ADMIN")
    public R addTeacher(@RequestBody TeacherRequestVo teacherRequestVo) {
        if(!ObjectUtils.isEmpty(teacherService.getById(teacherRequestVo.getTeacherId()))){
            return R.error().message("该序号已存在");
        }
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacherRequestVo,teacher);
        teacherService.save(teacher);
        return R.ok();
    }

    @ApiOperation("管理员删除导师")
    @DeleteMapping("deleteTeacher/{teacherId}")
    @SaCheckRole("ADMIN")
    public R deleteTeacher(@PathVariable Integer teacherId) {
        teacherService.removeById(teacherId);
        return R.ok();
    }

    @ApiOperation("管理员修改导师")
    @PostMapping("alterTeacher/{teacherId}")
    @SaCheckRole("ADMIN")
    public R alterTeacher(@PathVariable Integer teacherId, @RequestParam String teacherName) {
        Teacher teacher = teacherService.getById(teacherId);
        teacher.setTeacherName(teacherName);
        teacherService.updateById(teacher);
        return R.ok();
    }
}

