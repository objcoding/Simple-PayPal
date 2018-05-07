package com.objcoding.paypal.core.handler;


/**
 * Auth: zch
 * Email: zhangchenghui.dev@gmail.com
 * Date: 2018/5/7.
 */
public class EnumHandler {

    /**
     * 处理器类型
     */
    public enum HandlerType {

        PAY(1, "pay"),
        REFUND(2, "refund");

        private Integer handlerType;
        private String typeName;

        HandlerType(Integer handlerType, String typeName) {
            this.handlerType = handlerType;
            this.typeName = typeName;
        }

        public Integer getHandlerType() {
            return handlerType;
        }

        public String getTypeName() {
            return typeName;
        }
    }

    /**
     * 请求步骤
     */
    public enum Step {

        BEFORE(1, "before"),
        AFTER(2, "AFTER"),
        EXCEPTION(3, "exception");

        private Integer step;
        private String stepName;

        Step(Integer step, String stepName) {
            this.step = step;
            this.stepName = stepName;
        }

        public Integer getStep() {
            return step;
        }

        public String getStepName() {
            return stepName;
        }
    }

    /**
     * 支付类型
     */
    public enum PayType {
        ALIPAY(1, "alipay"),
        WECHATPAY(2, "wechatpay"),
        PAYPAL(3, "payal");

        private Integer payType;
        private String typeName;

        PayType(Integer payType, String typeName) {
            this.payType = payType;
            this.typeName = typeName;
        }

        public Integer getPayType() {
            return payType;
        }

        public String getTypeName() {
            return typeName;
        }
    }

    /**
     * 交易类型
     */
    public enum TradeType {

        // ****************** WechatPay *****************

        /**
         * 公众号支付
         */
        JSAPI(1, "JSAPI"),

        /**
         * 原生扫码支付
         */
        NATIVE(2, "NATIVE"),

        /**
         * APP支付
         */
        APP(3, "APP"),

        /**
         * 刷卡支付
         */
        MICROPAY(4, "MICROPAY"),


        // ******************* AliPay ******************

        /**
         * WEB支付
         */
        WEB_PAY(5, "create_direct_pay_by_user"),

        /**
         * WAP支付
         */
        WAP_PAY(6, "alipay.wap.create.direct.pay.by.user"),

        /**
         * APP支付
         */
        APP_PAY(7, "mobile.securitypay.pay"),


        // ************** PayPal ***************

        /**
         * PayPal Payment Data Transfer
         */
        PDT(8, "PDT"),

        /**
         * PayPal Instant Payment Notification
         */
        IPN(9, "IPN"),


        REFUND(1, "refund"),
        REFUND_NO_PWD(2, "refund_no_pwd"); // 无密退款

        private Integer tradeType;
        private String typeName;

        TradeType(Integer tradeType, String typeName) {
            this.tradeType = tradeType;
            this.typeName = typeName;
        }

        public Integer getTradeType() {
            return tradeType;
        }

        public String getTypeName() {
            return typeName;
        }
    }

    /**
     * 调用组件状态
     */
    public enum Status {
        SUCCESS,
        FAIL
    }

}
