package com.znho.shm.model.order;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.znho.shm.model.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("orders")
public class Order extends BaseEntity {
    @ApiModelProperty("订单号")
    @TableField("order_id")
    private String orderId;

    @ApiModelProperty("发布者")
    @TableField("sell_user_id")
    private Long sellUserId;

    @ApiModelProperty("购买者")
    @TableField("buy_user_id")
    private Long buyUserId;

    @ApiModelProperty("商品")
    @TableField("good_id")
    private Long goodId;

    @ApiModelProperty("地址id")
    @TableField("address_id")
    private Long addressId;

    @ApiModelProperty("快递单号")
    @TableField("track")
    private String track;

    @ApiModelProperty("发货时间")
    @TableField("dispatch_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dispatchTime;

    @ApiModelProperty("状态 0-未支付  1-已支付未发货  2-已发货 3-已签收")
    @TableField("status")
    private Integer status;
}
