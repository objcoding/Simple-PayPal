package com.objcoding.paypal.core.handler;

import com.alibaba.fastjson.JSON;
import com.objcoding.paypal.core.model.Request;
import com.objcoding.paypal.core.model.Response;

/**
 * 处理器抽象类
 * <p>
 * Auth: zch
 * Email: zhangchenghui.dev@gmail.com
 * Date: 2018/5/3.
 */
public abstract class AbstractHandler<T extends Request, K extends Response> {

    // 支付宝、微信、PayPal
    protected abstract String getPayType();

    // app, wap, web,refund
    protected abstract String getTradeType();

    /**
     * 调用组件实现方法
     */
    protected abstract K execute(T t) throws Exception;

    /**
     * 核心方法
     */
    public K handle(T t) {
        K k = null;
        try {
            k = execute(t);
            if (k.getStatus().equals("SUCCESS")) {
                onSuccess(k);
            } else {
                onFail(k);
            }
        } catch (Exception e) {
            onFail(k);
        }
        return k;
    }

    /**
     * 成功日志记录
     */
    private void onSuccess(K k) {
        System.out.println(JSON.toJSONString(k));
    }

    /**
     * 失败日志记录
     */
    private void onFail(K k) {
        System.out.println(JSON.toJSONString(k));
    }

}
