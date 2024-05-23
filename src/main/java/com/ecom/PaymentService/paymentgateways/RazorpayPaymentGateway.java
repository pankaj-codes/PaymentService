package com.ecom.PaymentService.paymentgateways;

import org.springframework.stereotype.Service;

@Service("razorpayPaymentGateway")
public class RazorpayPaymentGateway implements PaymentGateway {
    @Override
    public String generatePaymentLink(Long orderId, String name, int amount) {
        return "";
    }
}
