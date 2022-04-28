package com.znho.shm.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ApiModel(description = "订单搜索")
public class OrderQueryVo {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("订单号")
    private Long orderId;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("购买者")
    private Long buyUserId;

    @ApiModelProperty("发布者")
    private Long sellUserId;

    @ApiModelProperty("商品")
    private Long goodId;

    @ApiModelProperty("创建时间开始")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTimeBegin;

    @ApiModelProperty("创建时间结束")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTimeEnd;

}
