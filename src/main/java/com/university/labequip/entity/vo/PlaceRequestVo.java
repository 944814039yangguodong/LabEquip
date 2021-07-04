package com.university.labequip.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="新增位置请求信息", description="位置请求信息")
public class PlaceRequestVo {
    @ApiModelProperty(value = "实验室位置编号")
    private Integer placeId;

    @ApiModelProperty(value = "实验室位置")
    private String placeName;
}
