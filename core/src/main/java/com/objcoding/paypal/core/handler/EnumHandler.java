package com.objcoding.paypal.core.handler;


/**
 * Auth: zch
 * Email: zhangchenghui.dev@gmail.com
 * Date: 2018/5/7.
 */
public class EnumHandler {

    // 支付类型
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

    public enum TradeType {

    }

    // 退款类型
    public enum RefundType {
        REFUND(1, "refund"),
        REFUND_NO_PWD(2, "refund_no_pwd"); // 无密退款

        private Integer refundType;
        private String typeName;

        RefundType(Integer refundType, String typeName) {
            this.refundType = refundType;
            this.typeName = typeName;
        }

        public void setRefundType(Integer refundType) {
            this.refundType = refundType;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }
    }

    // 调用组件状态
    public enum Status {
        SUCCESS,
        FAIL
    }

}
