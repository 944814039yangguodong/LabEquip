package com.university.labequip.controller;


import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import com.university.labequip.entity.Equipment;
import com.university.labequip.entity.User;
import com.university.labequip.entity.vo.EquipmentRequestNoIsAssignedVo;
import com.university.labequip.entity.vo.EquipmentRequestNoUserIdVo;
import com.university.labequip.entity.vo.EquipmentRequestVo;
import com.university.labequip.service.EquipmentService;
import com.university.labequip.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.university.labequip.utils.R;

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
@RequestMapping("/api/equipment")
@Api(tags = "设备相关接口")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private UserService userService;

    @ApiOperation("用户多条件查看自己的设备")
    @GetMapping("getUserEquipment")
    public R getUserEquipment(@RequestBody EquipmentRequestNoUserIdVo equipmentRequestNoUserIdVo){
        EquipmentRequestVo equipmentRequestVo=new EquipmentRequestVo();
        BeanUtils.copyProperties(equipmentRequestNoUserIdVo,equipmentRequestVo);
        //获取当前登录用户
        User user = userService.getById(StpUtil.getLoginIdAsString());
        equipmentRequestVo.setUserId(user.getUserId());
        equipmentRequestVo.setIsRetired(false);//未报废
        //查询用户所属设备列表
        List< Equipment > equipmentList = equipmentService.selectEquipment(equipmentRequestVo);
        return R.ok().data("list",equipmentList);
    }

    @ApiOperation("用户多条件查看公用的设备")
    @GetMapping("getPublicEquipment")
    public R getPublicEquipment(@RequestBody EquipmentRequestNoUserIdVo equipmentRequestNoUserIdVo){
        EquipmentRequestVo equipmentRequestVo=new EquipmentRequestVo();
        BeanUtils.copyProperties(equipmentRequestNoUserIdVo,equipmentRequestVo);
        //设置用户为Dreamtech公共用户
        String Dreamtech="1111";
        equipmentRequestVo.setUserId(Dreamtech);
        equipmentRequestVo.setIsRetired(false);//未报废
        //查询公共用户所属设备列表
        List< Equipment > equipmentList = equipmentService.selectEquipment(equipmentRequestVo);
        return R.ok().data("list",equipmentList);
    }

    @ApiOperation("用户多条件查看他人的设备")
    @GetMapping("getOthersEquipment/{userId}")
    public R getOthersEquipment(@PathVariable String userId, @RequestBody EquipmentRequestNoUserIdVo equipmentRequestNoUserIdVo){
        EquipmentRequestVo equipmentRequestVo=new EquipmentRequestVo();
        BeanUtils.copyProperties(equipmentRequestNoUserIdVo,equipmentRequestVo);
        equipmentRequestVo.setUserId(userId);//目标用户id
        equipmentRequestVo.setIsRetired(false);//未报废
        //查询该用户公开的设备列表
        List< Equipment > equipmentList = equipmentService.selectEquipment(equipmentRequestVo);
        return R.ok().data("list",equipmentList);
    }

    @ApiOperation("管理员多条件查看所有设备(含已报废设备)")
    @GetMapping("getAllEquipment")
    @SaCheckRole("ADMIN")
    public R getAllEquipment(@RequestBody EquipmentRequestVo equipmentRequestVo){
        List<Equipment> equipmentList = equipmentService.selectEquipment(equipmentRequestVo);
        return R.ok().data("list",equipmentList);
    }

    @ApiOperation("管理员设备入库")
    @PostMapping("addEquipment")
    @SaCheckRole("ADMIN")
    public R addEquipment(@RequestParam(defaultValue = "1") Integer amount, @RequestBody EquipmentRequestNoIsAssignedVo equipmentRequestNoIsAssignedVo) {
        Equipment equipment = new Equipment();
        BeanUtils.copyProperties(equipmentRequestNoIsAssignedVo,equipment);
        equipment.setIsRetired(false);//入库设置为未报废
        equipment.setIsAssigned(false);//入库设置为未分配
        for(int i=0;i<amount;i++){
            //保存设备一次
            equipmentService.save(equipment);
        }
        return R.ok();
    }

    @ApiOperation("管理员修改设备信息")
    @PostMapping("alterEquipment/{equipmentId}")
    @SaCheckRole("ADMIN")
    public R alterEquipmentShare(@RequestBody EquipmentRequestVo equipmentRequestVo, @PathVariable String equipmentId) {
        Equipment equipment = equipmentService.getById(equipmentId);
        BeanUtils.copyProperties(equipmentRequestVo,equipment);
        //更新设备信息
        equipmentService.updateById(equipment);
        return R.ok();
    }

    @ApiOperation("管理员报废设备")
    @PostMapping("retireEquipment/{equipmentId}")
    @SaCheckRole("ADMIN")
    public R alterEquipmentShare(@PathVariable String equipmentId) {
        Equipment equipment = equipmentService.getById(equipmentId);
        equipment.setIsRetired(true);
        //报废设备
        equipmentService.updateById(equipment);
        return R.ok();
    }

    @ApiOperation("管理员分配设备")
    @PostMapping("assignEquipment/{equipmentId}/{userId}")
    @SaCheckRole("ADMIN")
    public R alterEquipmentShare(@PathVariable String equipmentId, @PathVariable String userId) {
        Equipment equipment = equipmentService.getById(equipmentId);
        equipment.setUserId(userId);
        //分配设备
        equipmentService.updateById(equipment);
        return R.ok();
    }

    @ApiOperation("管理员删除设备")
    @DeleteMapping("deleteEquipment/{equipmentId}")
    @SaCheckRole("ADMIN")
    public R deleteEquipmentShare(@PathVariable String equipmentId) {
        //删除设备
        equipmentService.removeById(equipmentId);
        return R.ok();
    }
}

