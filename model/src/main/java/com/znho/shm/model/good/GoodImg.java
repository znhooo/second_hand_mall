package com.znho.shm.model.good;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.znho.shm.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@TableName("good_img")
@ApiModel("商品图片")
@Data
public class GoodImg extends BaseEntity {

    @TableField("good_id")
    @ApiModelProperty("商品id")
    private Long goodId;

    @TableField("img")
    @ApiModelProperty("商品图片链接")
    private String img;

    @TableField("flag")
    @ApiModelProperty("记录图片索引")
    private String flag;

}
