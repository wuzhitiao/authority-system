package com.lens.utils;

import lombok.Data;

@Data
public class Result<T> {

    private Boolean success; //是否成功

    private Integer code;   //状态码

    private String message;  //返回消息


    private T data;  //返回数据


    /**
     * 私有化构造方法，禁止在其它类创建对象
     */
    private Result() {
    }


    /**
     * 成功执行,不返回数据
     */
    public static <T> Result<T> ok() {
        Result<T> result = new Result<T>();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("执行成功");
        return result;
    }

    /**
     * 成功执行，返回数据
     */
    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<T>();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("执行成功");
        result.setData(data);
        return result;
    }

    /**
     * 失败
     */
    public static <T> Result<T> error() {
        Result<T> result = new Result<T>();
        result.setSuccess(false);
        result.setCode(ResultCode.ERROR);
        result.setMessage("执行失败");
        return result;
    }

    /**
     * 设置是否成功
     */
    public Result<T> success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    /**
     * 设置状态码
     */
    public Result<T> code(Integer code) {
        this.setCode(code);
        return this;
    }

    /**
     * 设置返回消息
     */
    public Result<T> message(String message) {
        this.setMessage(message);
        return this;
    }


    /**
     * 是否存在
     */
    public static <T> Result<T> exist() {
        Result<T> result = new Result<T>();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("执行成功");
        return result;
    }




}
