package com.university.labequip.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="新增导师请求信息", description="导师请求信息")
public class TeacherRequestVo {
    @ApiModelProperty(value = "导师编号")
    private Integer teacherId;

    @ApiModelProperty(value = "导师姓名")
    private String teacherName;
}
