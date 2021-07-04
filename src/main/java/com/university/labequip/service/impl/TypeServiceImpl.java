package com.university.labequip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.university.labequip.entity.Type;
import com.university.labequip.mapper.TypeMapper;
import com.university.labequip.service.TypeService;
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
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {

    @Autowired
    private TypeService typeService;

    @Override
    public List<String> getAllType() {
        //新建列表用于返回
        List<String> list = new ArrayList<>();
        QueryWrapper<Type> wrapper = new QueryWrapper<>();

        //遍历项目
        for (Type type : typeService.list(wrapper)) {
            list.add(type.getTypeName());
        }
        return list;
    }
}
