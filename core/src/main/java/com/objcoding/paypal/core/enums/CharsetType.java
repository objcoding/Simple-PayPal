package com.objcoding.paypal.core.enums;

/**
 * alipay 业务错误码
 * Auth: zch
 * Email: zhangchenghui.dev@gmail.com
 * Date: 2018/4/16.
 */
public enum CharsetType {

    UTF8("UTF-8");

    private String name;

    CharsetType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
