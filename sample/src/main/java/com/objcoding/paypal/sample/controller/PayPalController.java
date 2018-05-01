package com.objcoding.paypal.sample.controller;

import com.objcoding.paypal.core.enums.CharsetType;
import com.objcoding.paypal.core.model.PaymentView;
import com.objcoding.paypal.core.model.RefundView;
import com.objcoding.paypal.sample.helper.PayPalHelper;
import com.paypal.api.payments.DetailedRefund;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.Constants;
import com.paypal.base.rest.PayPalRESTException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * PayPal接口
 * <p>
 * Auth: zch
 * Email: zhangchenghui.dev@gmail.com
 * Date: 2018/4/23.
 */
@Controller
@RequestMapping("/pay-center/paypal")
public class PayPalController {

    private static final Logger LOG = LoggerFactory.getLogger(PayPalController.class);

    @Autowired
    private PayPalHelper paypalHelper;


    // ***************************** PDT（Payment Data Transfer） ****************************************

    /**
     * 创建即时（余额）付款信息，并返回 付款id 和 approval_url
     *
     * @param payPalPaymentView payPal payment view
     * @return approval_url
     */
    @PostMapping(value = "/payment")
    public @ResponseBody
    Map<String, String> payment(@RequestBody PaymentView payPalPaymentView) throws PayPalRESTException {
        Payment payment = paypalHelper.createPayment(payPalPaymentView);
        if (null != payment) {
            for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    Map<String, String> result = new HashMap<>();
                    result.put("paymentId", payment.getId());
                    result.put("approvalUrl", links.getHref());
                    return result;
                }
            }
        }
        return null;
    }

    /**
     * return url
     * PayPal确认付款调用的接口
     * 用户确认付款后，paypal调用的这个方法执行付款
     *
     * @param paymentId 创建付款信息时创建的付款id
     * @param payerId   用户钱包id
     * @return 付款成功信息
     */
    @GetMapping(value = "/payment/execute")
    public String executePayment(@RequestParam("paymentId") String paymentId,
                                 @RequestParam("PayerID") String payerId)
            throws PayPalRESTException {
        Payment payment = paypalHelper.executePayment(paymentId, payerId);
        if (null != payment) {
            if (payment.getState().equals("approved")) {
                // TODO 返回成功付款页面，这个到时候再做一个漂亮的页面显示，并使用前后端分离的模式
                return "success";
            }
        }
        return "failure";
    }

    // ************************************ IPN（Instant Payment Notification） ************************

    /**
     * 前端通过 PayPal Botton 方式将支付相关参数通过表单形式直接请求 PayPal，PayPal 再通过 IPN 异步回调给商户系统
     * PayPal Botton 格式如下：
     */

    /*<div style="text-align: center">
    <!-- paypal沙盒支付测试地址 -->
    <form action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post">
        <input type="hidden" name="cmd" value="_xclick">
        <!-- 商户PayPal收款账户 -->
        <input type="hidden" name="business" value="arthur.cai.facilitator@localhome.com.cn">
        <!-- 商品名称 -->
        <input type="hidden" name="item_name" value="test">
        <!-- 商品订单号，由订单平台定义，类似于微信的 OutTredeNo -->
        <input type="hidden" name="item_number" value="999">
        <!-- 订单金额 -->
        <input type="hidden" name="amount" value="1.00">
        <!---->
        <input type="hidden" name="no_shipping" value="2">
        <!---->
        <input type="hidden" name="no_note" value="1">
        <!-- 货币类型 -->
        <input type="hidden" name="currency_code" value="USD">
        <!---->
        <input type="hidden" name="bn" value="IC_Sample">
        <input type="image" src="https://www.sandbox.paypal.com/
    en_US/i/btn/x-click-but23.gif"
    name="submit" alt="Make payments with payPal - it's fast,
    free and secure!">
        <img alt=""
    src="https://www.paypal.com/en_US/i/scr/pixel.gif"
    width="1" height="1">
    </form>
    </div>*/

    /**
     * IPN
     * 即时通知回调（Instant Payment Notification）
     */
    @PostMapping(value = "/ipn")
    public void IPNHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {

        PrintWriter out = response.getWriter();

        /**
         * 1. 将PayPal返回来的参数连成url参数
         */
        String reqParamsStr = buildRequestParams(request);

        /**
         * 2. TODO 在这里需要将消息记录到消息模版数据库表中
         */
        LOG.info("paypal传递过来的交易信息: {}", reqParamsStr);

        /**
         * 3. 将信息 POST 回给 PayPal 进行验证，以防不法分子冒充 PayPal 传输数据
         */
        String verifyInfo = verifyIpnMessage();


        // TODO 根据订单号查询订单，在根据

        /**
         * 该付款明细所有变量可参考：
         * https://www.paypal.com/IntegrationCenter/ic_ipn-pdt-variable-reference.html
         */
        String itemName = request.getParameter("item_name");//商品名
        String itemNumber = request.getParameter("item_number");//购买数量
        String paymentStatus = request.getParameter("payment_status");//交易状态
        String paymentDate = request.getParameter("payment_date");//交易时间
        String paymentAmount = request.getParameter("mc_gross");//交易钱数
        String paymentCurrency = request.getParameter("mc_currency");//货币种类
        String txnId = request.getParameter("txn_id");//交易id
        String receiverEmail = request.getParameter("receiver_email");//收款人email
        String payerEmail = request.getParameter("payer_email");//付款人email

        if (StringUtils.isBlank(verifyInfo)) {
            verifyInfo = "0";
        }
        if (verifyInfo.equals("VERIFIED")) { // verify success

            if ("Completed".equals(paymentStatus)) { // payment success

                if (!receiverEmail.equals("merchant's email")) {

                    // 1. TODO deal with merchant system

                    // 2. what should I do to tell paypal that then payment is Cheat？？？
                    // return "FAIL"???

                } else if (!paymentAmount.equals("merchant's amount")) {

                    // 1. TODO deal with merchant system

                    // 2. what should I do to tell paypal that then payment is Cheat？？？
                    // return "FAIL"???
                }


                /**
                 * 5.检查付款状态
                 *      5.1检查 txn_id 是否已经处理过
                 *      5.2检查 receiver_email 是否是您的 PayPal 账户中的 EMAIL 地址
                 *      5.3检查付款金额和货币单位是否正确
                 *      5.4 MQ通知订单平台 处理其他数据，包括写数据库
                 *
                 */


            } else if ("Failed".equals(paymentStatus)){
                // TODO MQ通知订单平台
            } else {
                // Pending
            }


        } else if (verifyInfo.equals("INVALID")) {

            // TODO MQ通知订单平台

            LOG.error("验证失败");

            out.println("confirmError");
        } else {

            // TODO MQ通知订单平台

            LOG.error("test");

            out.println("confirmError");
        }

        out.flush();
        out.close();
    }

    private String buildRequestParams(HttpServletRequest request) throws UnsupportedEncodingException {

        StringBuilder sb = new StringBuilder();

        //从 PayPal 出读取 POST 信息同时添加变量„cmd‟

        /**
         * cmd=_notify-validate&
         * mc_gross=30.00&
         * protection_eligibility=Eligible&
         * address_status=confirmed&
         * payer_id=F7D3G7FRVFBXS&
         * address_street=NO+1+Nan+Jin+Road&
         * payment_date=03%3A28%3A07+Apr+26%2C+2018+PDT&
         * payment_status=Completed&
         * charset=gb2312&
         * address_zip=200000&
         * first_name=test&
         * mc_fee=1.32&
         * address_country_code=CN&
         * address_name=buyer+test&
         * notify_version=3.9&
         * custom=&
         * payer_status=verified&
         * business=arthur.cai-facilitator%40localhome.com.cn&
         * address_country=China&
         * address_city=Shanghai&
         * quantity=1&
         * verify_sign=A1lyDcRQjoBQJkIzS7j8Y1rOA3tdAr36RbX0KkQ9FefDh44XE77edGTg&
         * payer_email=arthur.cai-buyer%40localhome.com.cn&
         *
         * txn_id=3KF37366T3927582P&
         *
         * payment_type=instant&
         * last_name=buyer&
         * address_state=Shanghai&
         * receiver_email=arthur.cai-facilitator%40localhome.com.cn&
         * payment_fee=1.32&
         * receiver_id=ZUR6686KKKWPU&
         * txn_type=express_checkout&
         * item_name=test&
         * mc_currency=USD&
         * item_number=&
         * residence_country=CN&
         * test_ipn=1&
         * transaction_subject=test&
         * payment_gross=30.00&
         * ipn_track_id=385aa8c79321f
         */
        Enumeration en = request.getParameterNames();
        sb.append("cmd=_notify-validate");
        while (en.hasMoreElements()) {
            String paramName = (String) en.nextElement();
            String paramValue = request.getParameter(paramName);
            sb.append("&")
                    .append(paramName)
                    .append("=")
                    .append(URLEncoder.encode(paramValue, CharsetType.UTF8.getName()));
        }

        return sb.toString();
    }

    private String verifyIpnMessage() throws Exception {
        URL url;
        if (Constants.SANDBOX.equals(paypalHelper.getPayPal().getMode())) {
            url = new URL(Constants.IPN_SANDBOX_ENDPOINT); // 沙箱
        } else {
            url = new URL(Constants.IPN_LIVE_ENDPOINT); // 正式
        }
        URLConnection uc = url.openConnection();
        uc.setDoOutput(true);
        uc.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        PrintWriter pw = new PrintWriter(uc.getOutputStream());
        pw.close();
        //接受 PayPal 对 IPN 回发的回复信息
        BufferedReader in = new BufferedReader(new InputStreamReader(
                uc.getInputStream()));
        String res = in.readLine();
        in.close();

        return res;
    }


    /**
     * 即时付款的退款 sale refund
     */
    @PostMapping(value = "/sale/refund")
    public @ResponseBody
    DetailedRefund refund(@RequestBody RefundView payPalRefundView)
            throws PayPalRESTException {
        return paypalHelper.saleRefund(payPalRefundView);
    }

}
