package com.university.labequip.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel(value="设备信息返回类", description="返回设备信息")
public class EquipmentResponseVo {

    @ApiModelProperty(value = "主键id（设备）")
    private Integer equipmentId;

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

    @ApiModelProperty(value = "购买年")
    private String purchaseYear;

    @ApiModelProperty(value = "报废日期")
    private Date retirementDate;

    @ApiModelProperty(value = "是否报废")
    private Boolean isRetired;

    @ApiModelProperty(value = "使用人id")
    private String userId;

    @ApiModelProperty(value = "使用人姓名")
    private String userName;

    @ApiModelProperty(value = "购买人id")
    private String purchaserId;

    @ApiModelProperty(value = "存放地点")
    private String place;

    @ApiModelProperty(value = "是否报账")
    private Boolean isRecorded;

    @ApiModelProperty(value = "是否分配")
    private Boolean isAssigned;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "创建者id")
    @TableField(fill = FieldFill.INSERT)
    private String creatorId;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModify;

    @ApiModelProperty(value = "修改者id")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String modifierId;

    @ApiModelProperty(value = "删除时间")
    private Date gmtDelete;
}
