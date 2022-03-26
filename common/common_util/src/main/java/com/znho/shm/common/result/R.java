package com.znho.shm.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 全局统一返回结果类
 */
@Data
@ApiModel(value = "全局统一返回结果")
public class R<T> {

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;

    public R(){}

    protected static <T> R<T> build(T data) {
        R<T> r = new R<T>();
        if (data != null)
            r.setData(data);
        return r;
    }

    public static <T> R<T> build(T body, ResultCodeEnum resultCodeEnum) {
        R<T> r = build(body);
        r.setCode(resultCodeEnum.getCode());
        r.setMessage(resultCodeEnum.getMessage());
        return r;
    }

    public static <T> R<T> build(Integer code, String message) {
        R<T> r = build(null);
        r.setCode(code);
        r.setMessage(message);
        return r;
    }

    public static<T> R<T> ok(){
        return R.ok(null);
    }

    /**
     * 操作成功
     * @param data
     * @param <T>
     * @return
     */
    public static<T> R<T> ok(T data){
        R<T> r = build(data);
        return build(data, ResultCodeEnum.SUCCESS);
    }

    public static<T> R<T> fail(){
        return R.fail(null);
    }

    /**
     * 操作失败
     * @param data
     * @param <T>
     * @return
     */
    public static<T> R<T> fail(T data){
        R<T> r = build(data);
        return build(data, ResultCodeEnum.FAIL);
    }

    public R<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    public R<T> code(Integer code){
        this.setCode(code);
        return this;
    }

    public boolean isOk() {
        if(this.getCode().intValue() == ResultCodeEnum.SUCCESS.getCode().intValue()) {
            return true;
        }
        return false;
    }
}
