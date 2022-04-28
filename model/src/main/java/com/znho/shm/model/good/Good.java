package com.znho.shm.model.good;

import com.baomidou.mybatisplus.annotation.TableField;
import com.znho.shm.model.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Good extends BaseEntity {
    @ApiModelProperty("昵称")
    @TableField("name")
    private String name;

    @ApiModelProperty("经度")
    @TableField("latitude")
    private BigDecimal latitude;

    @ApiModelProperty("纬度")
    @TableField("longitude")
    private BigDecimal longitude;

    @ApiModelProperty("图片")
    @TableField("img")
    private String img;

    @ApiModelProperty("发布地点")
    @TableField("description")
    private String description;

    @ApiModelProperty("价格")
    @TableField("price")
    private BigDecimal price;

    @ApiModelProperty("发布地点")
    @TableField("site")
    private String site;

    @ApiModelProperty("发布者")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("状态 0-未支付  1-已支付未发货  2-已发货 3-已签收 4上架中")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("购买者")
    @TableField("buy_user_id")
    private Long buyUserId;
}
