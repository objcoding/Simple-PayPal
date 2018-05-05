package com.objcoding.paypal.core.handler;


/**
 * 处理器接口
 * <p>
 * Auth: zch
 * Email: zhangchenghui.dev@gmail.com
 * Date: 2018/5/4.
 */
public interface Handler<T, K> {

    K handle(T t);
}
