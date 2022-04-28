package com.znho.shm.model.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.znho.shm.model.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("address")
public class Address extends BaseEntity {
    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮编")
    private String code;

    @ApiModelProperty("地区")
    private String region;

    @ApiModelProperty("详细地址")
    private String verbose;

    @ApiModelProperty("状态 1:默认 0:无")
    private Integer status;
}
