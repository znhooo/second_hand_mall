package com.znho.shm.model.dict;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("Dict")
@TableName("dict")
public class Dict {
    @ApiModelProperty("id")
    @TableField("id")
    private Long id;

    @ApiModelProperty("字典名字")
    @TableField("name")
    private String name;

    @ApiModelProperty("父id")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty("hasChildren")
    @TableField(exist = false)
    private boolean hasChildren;
}
