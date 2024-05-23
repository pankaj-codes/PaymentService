package com.ecom.PaymentService.paymentgateways;

import org.springframework.stereotype.Service;

@Service("stripePaymentGateway")
public class StripePaymentGateway implements PaymentGateway {
    @Override
    public String generatePaymentLink(Long orderId, String name, int amount) {
        return "";
    }
}
