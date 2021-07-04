package com.university.labequip.entity;

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
@TableName("lab_place")
@ApiModel(value="Place对象", description="")
public class Place implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "实验室位置编号")
    @TableId(value = "place_id", type = IdType.INPUT)
    private Integer placeId;

    @ApiModelProperty(value = "实验室位置")
    private String placeName;

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
