package com.objcoding.paypal.core.model;


/**
 * 信用卡支付请求对象
 * Auth: zch
 * Email: zhangchenghui.dev@gmail.com
 * Date: 2018/4/25.
 */
public class CreditCardView {

    private String number;

    private String type;

    private int expireMonth;

    private String expireYear;

    private String cvv2;


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getExpireMonth() {
        return expireMonth;
    }

    public void setExpireMonth(int expireMonth) {
        this.expireMonth = expireMonth;
    }

    public String getExpireYear() {
        return expireYear;
    }

    public void setExpireYear(String expireYear) {
        this.expireYear = expireYear;
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }
}
