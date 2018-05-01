package com.objcoding.paypal.sample.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Auth: zch
 * Email: zhangchenghui.dev@gmail.com
 * Date: 2018/4/24.
 */
@ConfigurationProperties(prefix = "paypal")
public class PayPalProperties {

    /**
     * api ID
     */
    private String clientId;

    /**
     * api 密钥
     */
    private String clientSecret;

    /**
     * sandbox
     * live
     */
    private String mode;

    /**
     * 客户端用户取消支付返回到商家的页面url
     */
    private String cancelUrl;

    /**
     * 客户端用户确认支付调用的接口地址
     */
    private String returnUrl;

    /**
     * 支付成功返回页面url
     */
    private String successUrl;

    /**
     * 支付失败返回页面url
     */
    private String failureUrl;

    /**
     * 支付成功异步回调地址
     */
    private String notifyUrl;

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

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getFailureUrl() {
        return failureUrl;
    }

    public void setFailureUrl(String failureUrl) {
        this.failureUrl = failureUrl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}
