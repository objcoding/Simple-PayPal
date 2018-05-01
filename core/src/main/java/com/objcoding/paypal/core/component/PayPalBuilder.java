package com.objcoding.paypal.core.component;

/**
 * PayPal 构建器
 * <p>
 * Auth: zch
 * Email: zhangchenghui.dev@gmail.com
 * Date: 2018/4/23.
 */
public class PayPalBuilder {

    private PayPal payPal;

    private PayPalBuilder() {

    }

    public static PayPalBuilder newBuilder(String clientId, String clientSecret, String mode,
                                           String cancelUrl, String returnUrl, String notifyUrl) {
        PayPalBuilder builder = new PayPalBuilder();
        builder.payPal = new PayPal(clientId, clientSecret, mode, cancelUrl, returnUrl, notifyUrl);
        return builder;
    }

    public PayPal build() {
        return payPal.init();
    }
}
