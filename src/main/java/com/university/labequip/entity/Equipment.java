package com.university.labequip.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Guodong
 * @since 2021-07-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("lab_equipment")
@ApiModel(value="Equipment对象", description="")
public class Equipment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id（设备）")
    @TableId(value = "equipment_id", type = IdType.ASSIGN_ID)
    private String equipmentId;

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

    @ApiModelProperty(value = "是否报废")
    private Boolean isRetired;

    @ApiModelProperty(value = "使用人id")
    private String userId;

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
