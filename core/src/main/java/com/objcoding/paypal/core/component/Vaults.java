package com.objcoding.paypal.core.component;

import com.objcoding.paypal.core.model.CreditCardView;
import com.paypal.api.payments.CreditCard;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

/**
 * Vault API 信用卡支付
 *
 * Auth: zch
 * Email: zhangchenghui.dev@gmail.com
 * Date: 2018/4/25.
 */
public class Vaults extends Component {

    public Vaults(PayPal payPal, APIContext apiContext) {
        super(payPal, apiContext);
    }


    public CreditCard createCreditCard(CreditCardView creditCardView) throws PayPalRESTException {

        // ###CreditCard
        // A resource representing a credit card that can be
        // used to fund a payment.
        CreditCard creditCard = new CreditCard();
        creditCard.setExpireMonth(creditCardView.getExpireMonth());
        creditCard.setExpireYear(creditCardView.getExpireMonth());
        creditCard.setNumber(creditCardView.getNumber());
        creditCard.setCvv2(creditCardView.getCvv2());
        creditCard.setType(creditCardView.getType());

        // ###Save
        // Creates the credit card as a resource
        // in the PayPal vault. The response contains
        // an 'id' that you can use to refer to it
        // in the future payments.
        CreditCard createdCreditCard = creditCard.create(apiContext);

        return createdCreditCard;
    }
}
