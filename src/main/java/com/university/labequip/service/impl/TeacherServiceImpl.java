package com.university.labequip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.university.labequip.entity.Teacher;
import com.university.labequip.mapper.TeacherMapper;
import com.university.labequip.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Guodong
 * @since 2021-07-03
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Autowired
    private TeacherService teacherService;

    @Override
    public List<String> getAllTeacher() {
        //新建列表用于返回
        List<String> list = new ArrayList<>();
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();

        //遍历项目
        for (Teacher teacher : teacherService.list(wrapper)) {
            list.add(teacher.getTeacherName());
        }
        return list;
    }
}
