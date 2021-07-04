package com.university.labequip.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="新增类型请求信息", description="类型请求信息")
public class TypeRequestVo {
    @ApiModelProperty(value = "设备类型编号")
    private Integer typeId;

    @ApiModelProperty(value = "设备类型名")
    private String typeName;
}
