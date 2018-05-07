package com.objcoding.paypal.core.component;

import com.objcoding.paypal.core.enums.PayMethodType;
import com.objcoding.paypal.core.enums.PaymentIntent;
import com.objcoding.paypal.core.model.PaymentRequest;
import com.objcoding.paypal.core.model.RefundRequest;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * PayPal付款组件
 * <p>
 * Auth: zch
 * Email: zhangchenghui.dev@gmail.com
 * Date: 2018/4/23.
 */
public class Payments extends Component {

    public Payments(PayPal payPal, APIContext apiContext) {
        super(payPal, apiContext);
    }

    // ************************************* Payment ******************************************

    /**
     * 创建付款信息
     */
    public Payment createPayment(PaymentRequest payPalPaymentRequest) throws PayPalRESTException {
        Amount amount = new Amount();
        amount.setCurrency(payPalPaymentRequest.getCurrency());
        amount.setTotal(String.format("%.2f", payPalPaymentRequest.getTotal()));

        Transaction transaction = new Transaction();

        transaction.setDescription(payPalPaymentRequest.getDescription());
        /**
         * 实在找不到商户订单号 item_number 究竟是哪个字段（这个是真的坑爹）了，暂时用 custom 字段来传递商户订单号
         */
        transaction.setCustom("666666");

        transaction.setNotifyUrl(payPal.getNotifyUrl()); // 支付成功异步回调地址
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(PayMethodType.PAYPAL.name());// 余额支付

        Payment payment = new Payment();
        payment.setIntent(PaymentIntent.sale.name()); // 立即付款
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        RedirectUrls redirectUrls = new RedirectUrls();
        // 如果前端不传取消付款返回页面url，给默认的取消付款页面url
        redirectUrls.setCancelUrl(payPalPaymentRequest.getCancelUrl() == null ?
                payPal.getCancelUrl() : payPalPaymentRequest.getCancelUrl());
        redirectUrls.setReturnUrl(payPal.getReturnUrl());
        payment.setRedirectUrls(redirectUrls);

        return payment.create(apiContext);
    }


    /**
     * 执行付款
     */
    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecute);
    }


    /**
     * 付款详细
     */
    public Payment details(String paymentId) throws PayPalRESTException {
        Payment payment = Payment.get(apiContext, paymentId);
        return payment;
    }



    // ************************************* Sale ******************************************

    /**
     * 退款操作
     */
    public DetailedRefund saleRefund(RefundRequest payPalRefundRequest) throws PayPalRESTException {

        // ###Sale
        // A sale transaction.
        // Create a Sale object with the
        // given sale transaction id.
        Sale sale = new Sale();
        sale.setId(payPalRefundRequest.getSaleId());

        // ###Refund
        // A refund transaction.
        // Use the amount to create
        // a refund object
        com.paypal.api.payments.RefundRequest refund = new com.paypal.api.payments.RefundRequest();

        // ###Amount
        // Create an Amount object to
        // represent the amount to be
        // refunded. Create the refund object, if the refund is partial
        // 如果退款金额为空，则全额退款
        if (StringUtils.isNotBlank(payPalRefundRequest.getTotal())) {
            Amount amount = new Amount();
            amount.setCurrency(payPalRefundRequest.getCurrency());
            amount.setTotal(payPalRefundRequest.getTotal());
            refund.setAmount(amount);
            refund.setDescription(payPalRefundRequest.getDescription());
        }

        // Refund by posting to the APIService
        // using a valid AccessToken
        return sale.refund(apiContext, refund);
    }


    /**
     * 即时付款订单详情
     *
     * @param saleId saleID
     * @return
     * @throws PayPalRESTException
     */
    public Sale saleDetails(String saleId) throws PayPalRESTException {

        // Pass an AccessToken and the ID of the sale
        // transaction from your payment resource.
        return Sale.get(apiContext, saleId);
    }

    // ************************************* Orders ******************************************
}
