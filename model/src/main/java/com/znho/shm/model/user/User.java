package com.znho.shm.model.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.znho.shm.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("User")
@TableName("user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("昵称")
    @TableField("name")
    private String name;

    @ApiModelProperty("密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty("邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty("手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("性别")
    @TableField("sex")
    private Integer sex;

    @ApiModelProperty("微信id")
    @TableField("wx_id")
    private String wxId;

    @ApiModelProperty("状态(0:封禁,1:正常)")
    @TableField("status")
    private Integer status;

}
