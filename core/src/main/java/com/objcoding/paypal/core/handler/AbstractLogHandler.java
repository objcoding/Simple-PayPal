package com.objcoding.paypal.core.handler;

import com.alibaba.fastjson.JSON;
import com.objcoding.paypal.core.model.Request;
import com.objcoding.paypal.core.model.Response;

/**
 * 日志处理器抽象类
 * <p>
 * Auth: zch
 * Email: zhangchenghui.dev@gmail.com
 * Date: 2018/5/3.
 */
public abstract class AbstractLogHandler<T extends Request, K extends Response> implements Handler<T, K> {

    // 支付宝、微信、PayPal
    protected abstract Integer getPayType();

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
            onPre(t);
            // 执行支付组件
            k = execute(t);
            if (k.getStatus().equals(EnumHandler.Status.SUCCESS.name())) {
                onSuccess(k);
            } else {
                onFail(k);
            }
        } catch (Exception e) {
            System.out.println("handle error" + e);
            onException(t, e.getMessage());
        }
        return k;
    }

    /**
     * 请求前日志记录
     */
    private void onPre(T t) {
        System.out.println("onPre >>>>>>> " + JSON.toJSONString(t));
    }

    /**
     * 异常情况日志记录
     */
    private void onException(T t, String exceptionMessage) {
        System.out.println("onException >>>>>> " + JSON.toJSONString(t));
    }

    /**
     * 成功日志记录
     */
    private void onSuccess(K k) {
        System.out.println("onSuccess >>>>>>>>> " + JSON.toJSONString(k));
    }

    /**
     * 失败日志记录
     */
    private void onFail(K k) {
        System.out.println("onFail >>>>>>>>>> " + JSON.toJSONString(k));
    }

}
