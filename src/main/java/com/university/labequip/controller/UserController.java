package com.university.labequip.controller;


import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.university.labequip.entity.Equipment;
import com.university.labequip.entity.User;
import com.university.labequip.entity.vo.*;
import com.university.labequip.service.UserService;
import com.university.labequip.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/user")
@Api(tags = "用户相关接口")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("用户登录接口")
    @PostMapping("login")
    public R userLogin(@RequestBody LoginRequestVo loginUser){
        //获取用户Id
        String userId = loginUser.getUserId();
        //获取用户封装信息
        User user = userService.getById(userId);
        if (user!=null){
            boolean equals = SaSecureUtil.md5(loginUser.getUserPassword()).equals(user.getUserPassword());
            if(equals){
                StpUtil.setLoginId(user.getUserId());
                UserInfoResponseVo userInfo = new UserInfoResponseVo();
                BeanUtils.copyProperties(user,userInfo);
                return R.ok().data(StpUtil.getTokenName(),StpUtil.getTokenValue()).data("userInfo",userInfo);
            }else {
                return R.error().message("密码错误 请重新输入");
            }
        }else {
            return R.error().message("没有该用户");
        }
    }

    @ApiOperation("获取当前登录用户信息")
    @GetMapping("getUserInfo")
    public R getUserInfo(){
        User user = userService.getById((String) StpUtil.getLoginId());
        UserInfoResponseVo userInfo = new UserInfoResponseVo();
        BeanUtils.copyProperties(user,userInfo);
        return R.ok().data("userInfo",userInfo);
    }

    @ApiOperation("根据用户id获取用户信息")
    @GetMapping("getUserInfoById")
    public R getUserInfoById(@RequestParam String userId){
        User user = userService.getById(userId);
        UserInfoResponseVo userInfo = new UserInfoResponseVo();
        BeanUtils.copyProperties(user,userInfo);
        return R.ok().data("userInfo",userInfo);
    }

    @ApiOperation("查询所有普通用户")
    @PostMapping("selectUser")
    public R selectUser() {
        List<UserInfoResponseVo> list=userService.getUser();
        return R.ok().data("list",list);
    }

    @ApiOperation("管理员查询所有用户")
    @PostMapping("selectAllUser")
    @SaCheckRole("ADMIN")
    public R selectAllUser() {
        List<UserResponseVo> list=userService.getAllUser();
        return R.ok().data("list",list);
    }

    @ApiOperation("管理员新增用户")
    @PostMapping("addUser")
    @SaCheckRole("ADMIN")
    public R addUser(@RequestBody UserRequestVo userRequestVo) {
        User user = new User();
        BeanUtils.copyProperties(userRequestVo,user);
        String MD5Password = SaSecureUtil.md5(userRequestVo.getUserPassword());
        user.setUserPassword(MD5Password);
        userService.save(user);
        return R.ok();
    }

    @ApiOperation("管理员删除用户")
    @DeleteMapping("deleteUser/{userId}")
    @SaCheckRole("ADMIN")
    public R deleteUser(@PathVariable String userId) {
        //删除用户
        userService.removeById(userId);
        return R.ok();
    }

    @ApiOperation("管理员修改用户")
    @PostMapping("alterUser/{userId}")
    @SaCheckRole("ADMIN")
    public R alterUser(@PathVariable String userId, @RequestBody UserRequestNoUserIdVo userRequestNoUserIdVo) {
        User user = userService.getById(userId);
        BeanUtils.copyProperties(userRequestNoUserIdVo,user);
        //更新用户信息
        userService.updateById(user);
        return R.ok();
    }

}

