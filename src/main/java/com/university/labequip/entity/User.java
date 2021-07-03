package com.university.labequip.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("lab_user")
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "账号/学工号")
    @TableId(value = "user_id", type = IdType.ASSIGN_ID)
    private String userId;

    @ApiModelProperty(value = "用户密码")
    private String userPassword;

    @ApiModelProperty(value = "用户姓名")
    private String userName;

    @ApiModelProperty(value = "实验室位置")
    private String place;

    @ApiModelProperty(value = "导师（老师填自己）")
    private String instructor;

    @ApiModelProperty(value = "用户类型")
    private Integer userType;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private String gmtCreate;

    @ApiModelProperty(value = "创建者id")
    @TableField(fill = FieldFill.INSERT)
    private Integer creatorId;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String gmtModify;

    @ApiModelProperty(value = "修改者id")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer modifierId;

    @ApiModelProperty(value = "删除时间")
    private String gmtDelete;


}
