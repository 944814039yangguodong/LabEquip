package com.university.labequip.service;

import com.university.labequip.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.university.labequip.entity.vo.UserInfoResponseVo;
import com.university.labequip.entity.vo.UserResponseVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Guodong
 * @since 2021-07-03
 */
public interface UserService extends IService<User> {

    List<UserResponseVo> getAllUser();

    List<UserInfoResponseVo> getUser();
}
