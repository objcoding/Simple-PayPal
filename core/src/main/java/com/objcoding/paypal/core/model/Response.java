package com.objcoding.paypal.core.model;

/**
 * Auth: zch
 * Email: zhangchenghui.dev@gmail.com
 * Date: 2018/5/3.
 */
public interface Response {

    String getOutTradeNo();

    String getTradeNo();

    String getStatus();

    String getCode();

    String getSubCode();

    String getMsg();

    String getSubMsg();

    String getSign();

    String getBody();
}
