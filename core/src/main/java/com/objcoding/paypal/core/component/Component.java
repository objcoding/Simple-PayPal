package com.objcoding.paypal.core.component;

import com.paypal.base.rest.APIContext;

/**
 * paypal组件抽象类
 * Auth: zch
 * Email: zhangchenghui.dev@gmail.com
 * Date: 2018/4/23.
 */
public abstract class Component {

    protected PayPal payPal;

    protected APIContext apiContext;

    public Component(PayPal payPal, APIContext apiContext) {
        this.payPal = payPal;
        this.apiContext = apiContext;
    }
}
