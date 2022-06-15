package com.university.labequip.service;

import com.university.labequip.entity.Place;
import com.baomidou.mybatisplus.extension.service.IService;
import com.university.labequip.entity.vo.PlaceVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Guodong
 * @since 2021-07-03
 */
public interface PlaceService extends IService<Place> {

    List<PlaceVo> getAllPlace();
}
