package com.university.labequip.controller;


import cn.dev33.satoken.annotation.SaCheckRole;
import com.university.labequip.entity.Place;
import com.university.labequip.entity.vo.PlaceVo;
import com.university.labequip.service.PlaceService;
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
@CrossOrigin
@RequestMapping("/api/place")
@Api(tags = "位置相关接口")
public class PlaceController {
    @Autowired
    private PlaceService placeService;

    @ApiOperation("查询所有位置")
    @GetMapping("selectAllPlace")
    public R selectAllPlace() {
        List<PlaceVo> list=placeService.getAllPlace();
        return R.ok().data("list",list);
    }

    @ApiOperation("管理员新增位置")
    @PostMapping("addPlace")
    @SaCheckRole("ADMIN")
    public R addPlace(@RequestBody PlaceVo placeVo) {
        if(!ObjectUtils.isEmpty(placeService.getById(placeVo.getPlaceId()))){
            return R.error().message("该序号已存在");
        }
        Place place = new Place();
        BeanUtils.copyProperties(placeVo,place);
        placeService.save(place);
        return R.ok();
    }

    @ApiOperation("管理员删除位置")
    @DeleteMapping("deletePlace/{placeId}")
    @SaCheckRole("ADMIN")
    public R deletePlace(@PathVariable Integer placeId) {
        placeService.removeById(placeId);
        return R.ok();
    }

    @ApiOperation("管理员修改位置")
    @PostMapping("alterPlace/{placeId}")
    @SaCheckRole("ADMIN")
    public R alterPlace(@PathVariable Integer placeId, @RequestParam String placeName) {
        Place place = placeService.getById(placeId);
        if(ObjectUtils.isEmpty(place)){
            return R.error().message("该序号的位置不存在！");
        }
        if(ObjectUtils.isEmpty(placeName)){
            return R.error().message("新位置不可为空！");
        }
        place.setPlaceName(placeName);
        placeService.updateById(place);
        return R.ok();
    }
}

