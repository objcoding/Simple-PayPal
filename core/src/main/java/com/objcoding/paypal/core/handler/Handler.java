package com.objcoding.paypal.core.handler;


import com.objcoding.paypal.core.model.Request;
import com.objcoding.paypal.core.model.Response;

/**
 * 处理器接口
 * <p>
 * Auth: zch
 * Email: zhangchenghui.dev@gmail.com
 * Date: 2018/5/4.
 */
public interface Handler<T extends Request, K extends Response> {

    K handle(T t);
}
