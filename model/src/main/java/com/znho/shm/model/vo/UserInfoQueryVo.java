package com.znho.shm.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ApiModel(description = "用户搜索")
public class UserInfoQueryVo {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("关键词")
    private String keyword;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("创建时间开始")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTimeBegin;

    @ApiModelProperty("创建时间结束")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTimeEnd;

}
