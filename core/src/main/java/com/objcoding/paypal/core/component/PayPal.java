package com.objcoding.paypal.core.component;

import com.paypal.base.rest.APIContext;

import java.util.HashMap;
import java.util.Map;

/**
 * PayPal核心类
 * Auth: zch
 * Email: zhangchenghui.dev@gmail.com
 * Date: 2018/4/23.
 */
public class PayPal {

    private Orders orders;

    private Payments payments;

    private Payouts payouts;

    private String clientId;

    private String clientSecret;

    private String mode;

    private String cancelUrl;

    private String returnUrl;

    private String notifyUrl;

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public Orders orders() {
        return orders;
    }

    public Payments payments() {
        return payments;
    }

    public Payouts payouts() {
        return payouts;
    }

    public String getCancelUrl() {
        return cancelUrl;
    }

    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public PayPal init() {
        Map<String, String> sdkConfig = new HashMap<String, String>();
		sdkConfig.put("mode", mode);
        APIContext apiContext = new APIContext(clientId, clientSecret, mode, sdkConfig);

        orders = new Orders(this, apiContext);
        payments = new Payments(this, apiContext);
        payouts = new Payouts(this, apiContext);
        return this;
    }

    public PayPal(String clientId, String clientSecret, String mode, String cancelUrl, String returnUrl, String notifyUrl) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.mode = mode;
        this.cancelUrl = cancelUrl;
        this.returnUrl = returnUrl;
        this.notifyUrl = notifyUrl;
    }
}
