# Simple-PayPal
Simple-PayPal enables you to use PayPal-Java-SDK more gracefully.



## Base usage

```java
// create PayPal componet core class
PayPal payPal = PayPalBuilder.newBuilder("clientId", "clientSecret", "mode", "cancelUrl", "returnUrl", "notifyUrl").build();

// create payment
payPal.payments().createPayment(payPalPaymentRequest);
// execute payment（return_url）
payPal.payments().executePayment("paymentId", "payerId");
// sale refund
payPal.payments().saleRefund(payPalRefundRequest);
```

