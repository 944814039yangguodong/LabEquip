package com.university.labequip.controller;


import cn.dev33.satoken.annotation.SaCheckRole;
import com.university.labequip.entity.Type;
import com.university.labequip.entity.vo.TypeRequestVo;
import com.university.labequip.service.TypeService;
import com.university.labequip.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
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
@RequestMapping("/api/type")
@Api(tags = "类型相关接口")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @ApiOperation("查询所有类型")
    @PostMapping("selectAllType")
    public R selectAllType() {
        List<String> list=typeService.getAllType();
        return R.ok().data("list",list);
    }

    @ApiOperation("管理员新增类型")
    @PostMapping("addType")
    @SaCheckRole("ADMIN")
    public R addType(@RequestBody TypeRequestVo typeRequestVo) {
        if(!ObjectUtils.isEmpty(typeService.getById(typeRequestVo.getTypeId()))){
            return R.error().message("该序号已存在");
        }
        Type type = new Type();
        BeanUtils.copyProperties(typeRequestVo,type);
        typeService.save(type);
        return R.ok();
    }

    @ApiOperation("管理员删除类型")
    @DeleteMapping("deleteType/{typeId}")
    @SaCheckRole("ADMIN")
    public R deleteType(@PathVariable Integer typeId) {
        typeService.removeById(typeId);
        return R.ok();
    }

    @ApiOperation("管理员修改类型")
    @PostMapping("alterType/{typeId}")
    @SaCheckRole("ADMIN")
    public R alterType(@PathVariable Integer typeId, @RequestParam String typeName) {
        Type type = typeService.getById(typeId);
        type.setTypeName(typeName);
        typeService.updateById(type);
        return R.ok();
    }
}

