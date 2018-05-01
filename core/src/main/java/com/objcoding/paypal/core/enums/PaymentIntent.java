package com.objcoding.paypal.core.enums;


/**
 * <p>
 * Auth: zch
 * Email: zhangchenghui.dev@gmail.com
 * Date: 2018/4/24.
 */
public enum PaymentIntent {

	sale, // 及时付款  Makes an immediate payment.
	authorize, //  Authorizes a payment for capture later.
	order // 订单 . Creates an order.
	
}
