package com.university.labequip.config.satoken;

import cn.dev33.satoken.stp.StpInterface;
import com.university.labequip.entity.User;
import com.university.labequip.service.UserService;
import com.university.labequip.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限验证接口扩展
 */
@Component    // 保证此类被SpringBoot扫描，完成sa-token的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private UserService userService;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginKey) {
        // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询权限
        return null;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginKey) {
        // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询角色
        List<String> list = new ArrayList<String>();
        User user = userService.getById((String) loginId);

        if(user.getUserType().equals(Constant.ADMIN)){
            list.add("ADMIN");
        }
        if(user.getUserType().equals(Constant.TEACHER)){
            list.add("TEACHER");
        }
        if(user.getUserType().equals(Constant.STUDENT)){
            list.add("STUDENT");
        }
        return list;
    }

}
