package com.bingo.qa.response;




import com.bingo.qa.enums.ResponseStatusEnum;

import java.io.Serializable;

/**
 * @ClassName grocery-dtp-service
 * @Description
 * @Author wangfeng78
 * @Date 2022/3/10 17:15
 */

public class Response<T> implements Serializable {
    private int code;   //响应状态码（在ReponseStatusEnum中已经预定义了一部分）
    private String msg; //响应状态说明
    private T data; //数据载荷

    public Response() {
    }

    public Response(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /* ============ static tools ============= */

    /**
     * 快速生成“成功”结果
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Response<T> success(T data) {
        return new Response<>(
                ResponseStatusEnum.SUCCESS.getCode(),
                ResponseStatusEnum.SUCCESS.getMsg(),
                data
        );
    }

    /**
     * 快速生成“成功”结果
     * （自定义成功消息）
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Response<T> success(String msg, T data) {
        return new Response<>(
                ResponseStatusEnum.SUCCESS.getCode(),
                msg, data
        );
    }

    /**
     * 快速生成“参数错误”结果
     *
     * @param <T>
     * @return
     */
    public static <T> Response<T> paramError() {
        return new Response<>(
                ResponseStatusEnum.PARAM_ERROR.getCode(),
                ResponseStatusEnum.PARAM_ERROR.getMsg(),
                null
        );
    }

    /**
     * 快速生成“参数错误”结果
     * （自定义错误消息）
     *
     * @param <T>
     * @return
     */
    public static <T> Response<T> paramError(String msg) {
        return new Response<>(
                ResponseStatusEnum.PARAM_ERROR.getCode(),
                msg, null
        );
    }

    /**
     * 快速生成“服务器内部错误”结果
     *
     * @param <T>
     * @return
     */
    public static <T> Response<T> internalError() {
        return new Response<>(
                ResponseStatusEnum.INTERNAL_ERROR.getCode(),
                ResponseStatusEnum.INTERNAL_ERROR.getMsg(),
                null
        );
    }

    /**
     * 快速生成“服务器内部错误”结果
     * （自定义错误消息）
     *
     * @param <T>
     * @return
     */
    public static <T> Response<T> internalError(String msg) {
        return new Response<>(
                ResponseStatusEnum.INTERNAL_ERROR.getCode(),
                msg, null
        );
    }

    public static <T> Response<T> internalError(T data, String msg) {
        return new Response<>(
                ResponseStatusEnum.INTERNAL_ERROR.getCode(),
                msg, data
        );
    }

    /**
     * 快速生成自定义错误结果
     *
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Response<T> customError(int code, String msg) {
        return new Response<>(code, msg, null);
    }

    public static <T> Response<T> customError(int code, String msg, T data) {
        return new Response<>(code, msg, data);
    }

    /* ============ getters & setters ============= */

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
