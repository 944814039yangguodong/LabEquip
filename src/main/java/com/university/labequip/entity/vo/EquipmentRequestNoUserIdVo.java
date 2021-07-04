package com.university.labequip.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel(value="设备多条件请求类（无使用者id，无是否报废）", description="封装设备信息")
public class EquipmentRequestNoUserIdVo {

    @ApiModelProperty(value = "设备名称")
    private String equipmentType;

    @ApiModelProperty(value = "品牌")
    private String brand;

    @ApiModelProperty(value = "型号")
    private String model;

    @ApiModelProperty(value = "规格")
    private String specification;

    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

    @ApiModelProperty(value = "购买日期")
    private Date purchaseDate;

    @ApiModelProperty(value = "报废日期")
    private Date retirementDate;

    @ApiModelProperty(value = "购买人id")
    private String purchaserId;

    @ApiModelProperty(value = "存放地点")
    private String place;

    @ApiModelProperty(value = "是否报账")
    private Boolean isRecorded;

    @ApiModelProperty(value = "是否分配")
    private Boolean isAssigned;

}
