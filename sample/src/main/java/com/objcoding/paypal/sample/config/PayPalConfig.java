package com.objcoding.paypal.sample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Auth: zch
 * Email: zhangchenghui.dev@gmail.com
 * Date: 2018/4/24.
 */
@Configuration
@EnableConfigurationProperties(PayPalProperties.class)
public class PayPalConfig {

	@Autowired
	private PayPalProperties paypalProperties;

    public static String CLIENT_ID;
	public static String CLIENT_SECRET;

	// 正式环境live  沙箱环境 sandbox
	public static String MODE;

	// 用户取消付款调用的接口地址
	public static String CANCEL_URL;

	// 用户确认付款调用的接口地址
	public static String RETURN_URL;

	// 支付成功显示页面
	public static String SUCCESS_URL;

	// 支付失败显示页面
	public static String FAILURE_URL;

	// 支付成功异步回调地址
	public static String NOTIFY_URL;


	@PostConstruct
	public void initConstants() {
		PayPalConfig.CLIENT_ID = paypalProperties.getClientId();
		PayPalConfig.CLIENT_SECRET = paypalProperties.getClientSecret();
		PayPalConfig.MODE = paypalProperties.getMode();
		PayPalConfig.CANCEL_URL = paypalProperties.getCancelUrl();
		PayPalConfig.RETURN_URL = paypalProperties.getReturnUrl();
		PayPalConfig.SUCCESS_URL = paypalProperties.getSuccessUrl();
		PayPalConfig.FAILURE_URL = paypalProperties.getFailureUrl();
		PayPalConfig.NOTIFY_URL = paypalProperties.getNotifyUrl();
	}

}
