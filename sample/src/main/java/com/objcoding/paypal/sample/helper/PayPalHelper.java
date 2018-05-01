package com.objcoding.paypal.sample.helper;

import com.objcoding.paypal.core.component.PayPal;
import com.objcoding.paypal.core.component.PayPalBuilder;
import com.objcoding.paypal.core.model.PaymentView;
import com.objcoding.paypal.core.model.RefundView;
import com.objcoding.paypal.sample.config.PayPalConfig;
import com.paypal.api.payments.DetailedRefund;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * PayPay 支付构建帮助类
 * Auth: zch
 * Email: zhangchenghui.dev@gmail.com
 * Date: 2018/4/24.
 */
@Component
public class PayPalHelper {

    private static Logger log = LoggerFactory.getLogger(PayPalHelper.class);

    private PayPal payPal;

    @PostConstruct
    private void init() {
        payPal = PayPalBuilder.newBuilder(PayPalConfig.CLIENT_ID,
                PayPalConfig.CLIENT_SECRET, PayPalConfig.MODE,
                PayPalConfig.CANCEL_URL, PayPalConfig.RETURN_URL, PayPalConfig.NOTIFY_URL)
                .build();
    }

    public PayPal getPayPal() {
        return payPal;
    }

    // *********************************** payments ***********************************

    /**
     * 创建付款信息，并返回验证url
     */
    public Payment createPayment(PaymentView payPalPaymentView) throws PayPalRESTException {
        return payPal.payments().createPayment(payPalPaymentView);
    }

    /**
     * 执行付款
     */
    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        return payPal.payments().executePayment(paymentId, payerId);
    }

    /**
     * 退款
     */
    public DetailedRefund saleRefund(RefundView payPalRefundView) throws PayPalRESTException {
        return payPal.payments().saleRefund(payPalRefundView);
    }

    // *********************************** payouts ************************************


}
