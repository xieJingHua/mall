package com.xiejh.common.exception;

import lombok.Data;
import org.omg.CORBA.UNKNOWN;

/**
 * 响应码
 * 错误码列表
 *  10：通用
 *       001： 参数格式校验
 *  11：商品
 *  12：订单
 *  13：购物车
 *  14：物流
 *
 * @author xiejh
 * @Date 2020/11/7 14:48
 **/
public enum  ResponseCode {

    UNKNOWN_EXCEPTION(10000,"系统未知异常"),
    VALID_EXCEPTION(10001, "参数格式校验失败"),
    PRODUCT_UP_EXCEPTION(11000,"商品上架异常");

    private int code;
    private String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

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
}
