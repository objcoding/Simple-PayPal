package com.objcoding.paypal.core.handler;

import com.objcoding.paypal.core.model.Request;
import com.objcoding.paypal.core.model.Response;

/**
 * 日志处理器抽象类
 * <p>
 * Auth: zch
 * Email: zhangchenghui.dev@gmail.com
 * Date: 2018/5/3.
 */
public abstract class AbstractLogHandler<T extends Request, K extends Response> extends AbstractHandler<T, K> {

    // 日志处理类
//    protected abstract LogBizService getLogBizService();

    // 支付宝、微信、PayPal
    protected abstract Integer getPayType();

    // app, wap, web,refund
    protected abstract Integer getTradeType();

    /**
     * 请求前日志记录
     */
    @Override
    protected void before(T t, Integer handlerType) {

//        LogBizService logBizService = getLogBizService();
//
//        if (handlerType.equals(EnumHandler.HandlerType.PAY.getHandlerType())) { // 支付
//            PayRequest request = (PayRequest) t;
//            logBizService.record(request, null, null, getPayType(), getTradeType(), EnumHandler.Step.BEFORE.getStep());
//        } else { // 退款
//            RefundRequest request = (RefundRequest) t;
//            logBizService.record(request, null, null, getPayType(), getTradeType(), EnumHandler.Step.BEFORE.getStep());
//        }

    }

    /**
     * 请求后日志记录
     */
    @Override
    protected void after(K k, Integer handlerType) {

//        LogBizService logBizService = getLogBizService();
//
//        if (handlerType.equals(EnumHandler.HandlerType.PAY.getHandlerType())) { // 支付
//            PayResponse response = (PayResponse) k;
//            logBizService.record(null, response, null, getPayType(), getTradeType(), EnumHandler.Step.AFTER.getStep());
//        } else { // 退款
//            RefundResponse response = (RefundResponse) k;
//            logBizService.record(null, response, null, getPayType(), getTradeType(), EnumHandler.Step.AFTER.getStep());
//        }

    }

    /**
     * 异常情况日志记录
     */
    @Override
    protected void exception(T t, Integer handlerType, String exceptionMessage) {

//        LogBizService logBizService = getLogBizService();
//
//        if (handlerType.equals(EnumHandler.HandlerType.PAY.getHandlerType())) { // 支付
//            PayRequest request = (PayRequest) t;
//            logBizService.record(request, null, exceptionMessage, getPayType(), getTradeType(), EnumHandler.Step.EXCEPTION.getStep());
//        } else { // 退款
//            RefundRequest request = (RefundRequest) t;
//            logBizService.record(request, null, exceptionMessage, getPayType(), getTradeType(), EnumHandler.Step.EXCEPTION.getStep());
//        }
    }


}
