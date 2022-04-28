package com.znho.shm.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel("封装展示商品列表的对象")
public class GoodListVo {
    private String goodName;
    private String userName;
    private String goodImg;
    private String description;
    private String avatar;
    private Long goodId;
    private Long sellUserId;
    private Long buyUserId;
    private BigDecimal price;
    private Date createTime;
    private Date updateTime;
    private String site;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Integer status;
}
