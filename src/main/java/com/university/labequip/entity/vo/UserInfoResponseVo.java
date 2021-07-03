package com.university.labequip.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="用户信息返回类", description="当前登录的用户信息")
public class UserInfoResponseVo {

    @ApiModelProperty(value = "账号/学工号")
    private String userId;

    @ApiModelProperty(value = "用户姓名")
    private String userName;

    @ApiModelProperty(value = "实验室位置")
    private String place;

    @ApiModelProperty(value = "导师（老师填自己）")
    private String instructor;

    @ApiModelProperty(value = "用户类别")
    private Integer userType;
}
