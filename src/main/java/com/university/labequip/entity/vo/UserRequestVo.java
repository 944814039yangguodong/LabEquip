package com.university.labequip.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="新增用户请求信息", description="用户请求信息")
public class UserRequestVo {

    @ApiModelProperty(value = "账号/学工号")
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
}
