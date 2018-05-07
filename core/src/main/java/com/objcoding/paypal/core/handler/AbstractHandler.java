package com.objcoding.paypal.core.handler;

/**
 * 处理器抽象类
 * <p>
 * Auth: zch
 * Email: zhangchenghui.dev@gmail.com
 * Date: 2018/5/3.
 */
public abstract class AbstractHandler<T, K> implements Handler<T, K> {

    /**
     * 由具体的实现类执行业务处理
     */
    protected abstract K execute(T t) throws Exception;

    /**
     * 核心方法
     */
    public K handle(T t, Integer handlerType) {
        K k = null;
        try {
            before(t, handlerType);
            k = execute(t);
            after(k, handlerType);
        } catch (Exception e) {
            System.out.println("handle error: " + e);
            exception(t, handlerType, e.getMessage());
        }
        return k;
    }

    /**
     * 前处理
     */
    protected abstract void before(T t, Integer handlerType);

    /**
     * 后处理
     */
    protected abstract void after(K k, Integer handlerType);

    /**
     * 异常处理
     */
    protected abstract void exception(T t, Integer handlerType, String exceptionMessage);

}
