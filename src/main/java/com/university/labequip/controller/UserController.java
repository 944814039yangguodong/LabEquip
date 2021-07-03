package com.university.labequip.controller;


import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.university.labequip.entity.User;
import com.university.labequip.entity.vo.LoginRequestVo;
import com.university.labequip.entity.vo.UserInfoResponseVo;
import com.university.labequip.service.UserService;
import com.university.labequip.utils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Guodong
 * @since 2021-07-03
 */
@RestController
@RequestMapping("/labequip/user")
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
}

