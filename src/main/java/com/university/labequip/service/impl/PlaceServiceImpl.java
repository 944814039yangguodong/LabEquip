package com.university.labequip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.university.labequip.entity.Place;
import com.university.labequip.mapper.PlaceMapper;
import com.university.labequip.service.PlaceService;
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
public class PlaceServiceImpl extends ServiceImpl<PlaceMapper, Place> implements PlaceService {

    @Autowired
    private PlaceService placeService;

    @Override
    public List<String> getAllPlace() {
        //新建列表用于返回
        List<String> list = new ArrayList<>();
        QueryWrapper<Place> wrapper = new QueryWrapper<>();

        //遍历项目
        for (Place place : placeService.list(wrapper)) {
            list.add(place.getPlaceName());
        }
        return list;
    }
}
