package com.university.labequip.controller;


import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import com.university.labequip.utils.R;

import java.util.List;
import java.util.Map;

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

    @ApiOperation("用户购买顺序多条件查看自己的设备")
    @GetMapping("getUserEquipment/{current}/{limit}")
    public R getUserEquipment(@PathVariable long current, @PathVariable long limit, EquipmentRequestNoUserIdVo equipmentRequestNoUserIdVo){
        EquipmentRequestVo equipmentRequestVo=new EquipmentRequestVo();
        BeanUtils.copyProperties(equipmentRequestNoUserIdVo,equipmentRequestVo);
        //获取当前登录用户
        User user = userService.getById(StpUtil.getLoginIdAsString());
        equipmentRequestVo.setUserId(user.getUserId());
        equipmentRequestVo.setIsRetired(false);//未报废
        equipmentRequestVo.setIsAssigned(true);//已分配
        //查询用户所属设备列表
        Page< Equipment > equipmentPage = equipmentService.perPageByOrder(current,limit,equipmentRequestVo,"purchase_date");
        long total = equipmentPage.getTotal();//总记录数
        List< Equipment > records = equipmentPage.getRecords();//数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation("用户购买顺序多条件查看公用的设备")
    @GetMapping("getPublicEquipment/{current}/{limit}")
    public R getPublicEquipment(@PathVariable long current, @PathVariable long limit, EquipmentRequestNoUserIdVo equipmentRequestNoUserIdVo){
        EquipmentRequestVo equipmentRequestVo=new EquipmentRequestVo();
        BeanUtils.copyProperties(equipmentRequestNoUserIdVo,equipmentRequestVo);
        //设置用户为Dreamtech公共用户
        String Dreamtech="1111";
        equipmentRequestVo.setUserId(Dreamtech);
        equipmentRequestVo.setIsRetired(false);//未报废
        equipmentRequestVo.setIsAssigned(true);//已分配
        //查询公共用户所属设备列表
        Page< Equipment > equipmentPage = equipmentService.perPageByOrder(current,limit,equipmentRequestVo,"purchase_date");
        long total = equipmentPage.getTotal();//总记录数
        List< Equipment > records = equipmentPage.getRecords();//数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation("用户购买顺序多条件查看他人的设备")
    @GetMapping("getOthersEquipment/{userId}/{current}/{limit}")
    public R getOthersEquipment(@PathVariable String userId, @PathVariable long current, @PathVariable long limit, EquipmentRequestNoUserIdVo equipmentRequestNoUserIdVo){
        EquipmentRequestVo equipmentRequestVo=new EquipmentRequestVo();
        BeanUtils.copyProperties(equipmentRequestNoUserIdVo,equipmentRequestVo);
        equipmentRequestVo.setUserId(userId);//目标用户id
        equipmentRequestVo.setIsRetired(false);//未报废
        equipmentRequestVo.setIsAssigned(true);//已分配
        //查询该用户公开的设备列表
        Page< Equipment > equipmentPage = equipmentService.perPageByOrder(current,limit,equipmentRequestVo,"purchase_date");
        long total = equipmentPage.getTotal();//总记录数
        List< Equipment > records = equipmentPage.getRecords();//数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation("用户购买顺序多条件查看未分配的设备")
    @GetMapping("getUnassignedEquipment/{current}/{limit}")
    public R getUnassignedEquipment(@PathVariable long current, @PathVariable long limit, EquipmentRequestNoUserIdVo equipmentRequestNoUserIdVo){
        EquipmentRequestVo equipmentRequestVo=new EquipmentRequestVo();
        BeanUtils.copyProperties(equipmentRequestNoUserIdVo,equipmentRequestVo);
        equipmentRequestVo.setIsRetired(false);//未报废
        equipmentRequestVo.setIsAssigned(false);//未分配
        //查询未分配的设备列表
        Page< Equipment > equipmentPage = equipmentService.perPageByOrder(current,limit,equipmentRequestVo,"purchase_date");
        long total = equipmentPage.getTotal();//总记录数
        List< Equipment > records = equipmentPage.getRecords();//数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation("管理员购买顺序多条件查看所有设备(含已报废设备)")
    @GetMapping("getAllEquipment/{current}/{limit}")
    @SaCheckRole("ADMIN")
    public R getAllEquipment(@PathVariable long current, @PathVariable long limit, EquipmentRequestVo equipmentRequestVo){
        Page< Equipment > equipmentPage = equipmentService.perPageByOrder(current,limit,equipmentRequestVo,"purchase_date");
        long total = equipmentPage.getTotal();//总记录数
        List< Equipment > records = equipmentPage.getRecords();//数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation("设备分类统计")
    @GetMapping("getStatisticEquipment/{property}")
    @SaCheckRole("ADMIN")
    public R getStatisticEquipment(@PathVariable String property){
        if(property.equals("equipment_type")||property.equals("brand")||property.equals("is_recorded")||property.equals("is_assigned")||property.equals("place")){
            List<Map<String, Object>> list= equipmentService.countByGroup(property);
            long total = list.size();//总记录数
            return R.ok().data("total",total).data("rows",list);
        }
        else if(property.equals("purchase_date")){
            List<Map<String, Object>> list= equipmentService.countByGroup(property);
            long total = list.size();//总记录数
            return R.ok().data("total",total).data("rows",list);
        }
        else {
            return R.error().message("不允许以属性:"+property+"分类统计");
        }
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
    public R alterEquipment(@RequestBody EquipmentRequestVo equipmentRequestVo, @PathVariable Integer equipmentId) {
        if(!ObjectUtils.isEmpty(equipmentRequestVo.getUserId())){
            equipmentRequestVo.setIsAssigned(true);//修改使用人则设备设置为已分配
        }
        Equipment equipment = equipmentService.getById(equipmentId);
        BeanUtils.copyProperties(equipmentRequestVo,equipment);
        //更新设备信息
        equipmentService.updateById(equipment);
        return R.ok();
    }

    @ApiOperation("管理员报废设备")
    @PostMapping("retireEquipment/{equipmentId}")
    @SaCheckRole("ADMIN")
    public R alterEquipmentShare(@PathVariable Integer equipmentId) {
        Equipment equipment = equipmentService.getById(equipmentId);
        equipment.setIsRetired(true);
        //报废设备
        equipmentService.updateById(equipment);
        return R.ok();
    }

    @ApiOperation("管理员分配设备（不允许分配已分配的设备）")
    @PostMapping("assignEquipment/{equipmentId}/{userId}")
    @SaCheckRole("ADMIN")
    public R assignEquipment(@PathVariable Integer equipmentId, @PathVariable String userId) {
        Equipment equipment = equipmentService.getById(equipmentId);
        if(equipment.getIsAssigned()){
            return R.error().message("该设备已分配使用人");
        }
        equipment.setUserId(userId);
        equipment.setIsAssigned(true);
        //分配设备
        equipmentService.updateById(equipment);
        return R.ok();
    }

    @ApiOperation("管理员强制分配设备")
    @PostMapping("assignEquipmentByForce/{equipmentId}/{userId}")
    @SaCheckRole("ADMIN")
    public R assignEquipmentByForce(@PathVariable Integer equipmentId, @PathVariable String userId) {
        Equipment equipment = equipmentService.getById(equipmentId);
        equipment.setUserId(userId);
        equipment.setIsAssigned(true);
        //分配设备
        equipmentService.updateById(equipment);
        return R.ok().message("强制分配成功");
    }

    @ApiOperation("管理员取消分配设备")
    @PostMapping("unassignEquipmentByForce/{equipmentId}")
    @SaCheckRole("ADMIN")
    public R unassignEquipmentByForce(@PathVariable Integer equipmentId) {
        Equipment equipment = equipmentService.getById(equipmentId);
        equipment.setUserId(null);
        equipment.setIsAssigned(false);
        //取消分配设备
        equipmentService.updateById(equipment);
        return R.ok();
    }

    @ApiOperation("管理员删除设备")
    @DeleteMapping("deleteEquipment/{equipmentId}")
    @SaCheckRole("ADMIN")
    public R deleteEquipmentShare(@PathVariable Integer equipmentId) {
        //删除设备
        equipmentService.removeById(equipmentId);
        return R.ok();
    }
}

