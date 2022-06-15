package com.university.labequip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.university.labequip.entity.User;
import com.university.labequip.entity.vo.UserInfoResponseVo;
import com.university.labequip.entity.vo.UserResponseVo;
import com.university.labequip.mapper.UserMapper;
import com.university.labequip.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserService userService;

    @Resource
    UserMapper userMapper;

    @Override
    public List<UserResponseVo> getAllUser() {
        //新建列表用于返回
        List<UserResponseVo> list = new ArrayList<>();
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        //遍历项目
        for (User user : userService.list(wrapper)) {
            UserResponseVo userResponseVo = new UserResponseVo();
            BeanUtils.copyProperties(user,userResponseVo);
            list.add(userResponseVo);
        }
        return list;
    }

    @Override
    public List<UserInfoResponseVo> getUser() {
        //新建列表用于返回
        List<UserInfoResponseVo> list = new ArrayList<>();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //排除管理员
        wrapper.ne("user_type",0);
        //遍历项目
        for (User user : userService.list(wrapper)) {
            UserInfoResponseVo userInfoResponseVo = new UserInfoResponseVo();
            BeanUtils.copyProperties(user, userInfoResponseVo);
            list.add(userInfoResponseVo);
        }
        return list;
    }

    @Override
    public User getByIdIncludeDeleted(String userId) {
        String sql="SELECT\r\n" + "  * \r\n" + "FROM\r\n" + "  lab_user \r\n" + "WHERE\r\n"
                + "  user_id = " + userId + "\r\n" + "";
        return userMapper.selectByIdIncludeDeleted(sql);
    }

    @Override
    public int forceSave(User user) {
        if(!ObjectUtils.isEmpty(getByIdIncludeDeleted(user.getUserId()))){
            String sql="DELETE\r\n" + "FROM\r\n" + "  lab_user \r\n" + "WHERE\r\n"
                    + "  user_id = " + user.getUserId() + "\r\n" + "";
            userMapper.forceDelete(sql);
        }
        return userMapper.insert(user);
    }

}
