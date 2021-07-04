package com.university.labequip.service;

import com.university.labequip.entity.Type;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Guodong
 * @since 2021-07-03
 */
public interface TypeService extends IService<Type> {

    List<String> getAllType();
}
