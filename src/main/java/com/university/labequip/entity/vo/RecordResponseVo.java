package com.university.labequip.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel(value="新增记录请求信息", description="记录请求信息")
public class RecordResponseVo {
    @ApiModelProperty(value = "主键id（收支记录）")
    private Integer recordId;

    @ApiModelProperty(value = "日期")
    private Date date;

    @ApiModelProperty(value = "收入true/支出false")
    private Boolean isIncome;

    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "详情")
    private String details;

    @ApiModelProperty(value = "记录人")
    private String recorder;
}
