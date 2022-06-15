package com.university.labequip.controller;


import cn.dev33.satoken.annotation.SaCheckRole;
import com.university.labequip.entity.Record;
import com.university.labequip.entity.vo.RecordResponseVo;
import com.university.labequip.entity.vo.RecordVo;
import com.university.labequip.service.RecordService;
import com.university.labequip.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Guodong
 * @since 2021-08-04
 */
@RestController
@CrossOrigin
@RequestMapping("/api/record")
@Api(tags = "收支记录相关接口")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @ApiOperation("查询所有收支记录")
    @GetMapping("selectAllRecord")
    public R selectAllRecord() {
        List<Record> recordList = recordService.list();
        List<RecordResponseVo> list = new ArrayList<>();
        for(int i=0;i<recordList.size();i++){
            RecordResponseVo recordResponseVo=new RecordResponseVo();
            BeanUtils.copyProperties(recordList.get(i),recordResponseVo);
            list.add(recordResponseVo);
        }
        return R.ok().data("list",list);
    }

    @ApiOperation("新增收支记录")
    @PostMapping("addRecord")
    @SaCheckRole("ADMIN")
    public R addRecord(RecordVo recordVo) {
        Record record = new Record();
        BeanUtils.copyProperties(recordVo,record);
        recordService.save(record);
        return R.ok();
    }

    @ApiOperation("删除收支记录")
    @DeleteMapping("deleteRecord/{recordId}")
    @SaCheckRole("ADMIN")
    public R deleteRecord(@PathVariable Integer recordId) {
        recordService.removeById(recordId);
        return R.ok();
    }
}

