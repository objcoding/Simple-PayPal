package com.objcoding.paypal.core.model;


/**
 * Auth: zch
 * Email: zhangchenghui.dev@gmail.com
 * Date: 2018/4/25.
 */
public class RefundRequest {

    /**
     * The ID of the sale transaction to refund.
     */
    private String saleId;

    /**
     * 3-letter [currency code](https://developer.paypal.com/docs/integration/direct/rest_api_payment_country_currency_support/). PayPal does not support all currencies.
     */
    private String currency;

    /**
     * Total amount charged from the payer to the payee. In case of a refund, this is the refunded amount to the original payer from the payee. 10 characters max with support for 2 decimal places.
     */
    private String total;

    /**
     * Description of what is being refunded for. Character length and limitations: 255 single-byte alphanumeric characters.
     */
    private String description;

    /**
     * Type of refund you are making.
     */
    private String refundType;

    /**
     * Type of PayPal funding source (balance or eCheck) that can be used for auto refund.
     */
    private String refundSource;

    /**
     * Reason description for the Sale transaction being refunded.
     */
    private String reason;

    /**
     * The invoice number that is used to track this payment. Character length and limitations: 127 single-byte alphanumeric characters.
     */
    private String invoiceNumber;

    /**
     * Flag to indicate that the buyer was already given store credit for a given transaction.
     */
    private Boolean refundAdvice;

    /**
     * It indicates that the resource id passed is not processed by payments platform
     */
    private String isNonPlatformTransaction;


    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRefundType() {
        return refundType;
    }

    public void setRefundType(String refundType) {
        this.refundType = refundType;
    }

    public String getRefundSource() {
        return refundSource;
    }

    public void setRefundSource(String refundSource) {
        this.refundSource = refundSource;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Boolean getRefundAdvice() {
        return refundAdvice;
    }

    public void setRefundAdvice(Boolean refundAdvice) {
        this.refundAdvice = refundAdvice;
    }

    public String getIsNonPlatformTransaction() {
        return isNonPlatformTransaction;
    }

    public void setIsNonPlatformTransaction(String isNonPlatformTransaction) {
        this.isNonPlatformTransaction = isNonPlatformTransaction;
    }
}
