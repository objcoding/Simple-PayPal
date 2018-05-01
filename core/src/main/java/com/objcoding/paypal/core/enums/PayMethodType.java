package com.objcoding.paypal.core.enums;

/**
 * 付款方式
 * <p>
 * Auth: zch
 * Email: zhangchenghui.dev@gmail.com
 * Date: 2018/4/17.
 */
public enum PayMethodType {

    /**
     * 信用卡支付
     */
    CREDIT_CART("credit_card"),

    /**
     * paypal 支付
     */
    PAYPAL("paypal");


    private String value;

    private PayMethodType(String value){
        this.value = value;
    }

    public String value(){
        return value;
    }
}
