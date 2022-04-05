package com.znho.shm.model.good;

import com.baomidou.mybatisplus.annotation.TableField;
import com.znho.shm.model.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Good extends BaseEntity {
    @ApiModelProperty("昵称")
    @TableField("name")
    private String name;

    @ApiModelProperty("图片")
    @TableField("img")
    private String img;

    @ApiModelProperty("发布地点")
    @TableField("description")
    private String description;

    @ApiModelProperty("发布地点")
    @TableField("site")
    private String site;

    @ApiModelProperty("发布者")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("状态")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("购买者")
    @TableField("buy_user_id")
    private Long buyUserId;
}
