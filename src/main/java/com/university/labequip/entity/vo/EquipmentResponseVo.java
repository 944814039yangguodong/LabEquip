package com.university.labequip.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel(value="设备信息返回类", description="返回设备信息")
public class EquipmentResponseVo {

    @ApiModelProperty(value = "主键id(设备)")
    private Integer equipmentId;

    @ApiModelProperty(value = "设备名称")
    private String equipmentName;

    @ApiModelProperty(value = "规格")
    private String specification;

    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

    @ApiModelProperty(value = "购买日期")
    private Date purchaseDate;

    @ApiModelProperty(value = "报废日期")
    private Date retirementDate;

    @ApiModelProperty(value = "使用人id")
    private String userId;

    @ApiModelProperty(value = "是否报账")
    private Boolean isRecorded;

    @ApiModelProperty(value = "是否公开")
    private Boolean isPublic;
}
