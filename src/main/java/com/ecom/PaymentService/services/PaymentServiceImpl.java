package com.ecom.PaymentService.services;

import com.ecom.PaymentService.paymentgateways.PaymentGateway;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

private final PaymentGateway paymentGateway;

    public PaymentServiceImpl(@Qualifier("razorpayPaymentGateway") PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    @Override
    public String initiatePayment(Long userId, String name, int amount) throws RazorpayException, StripeException {
        return paymentGateway.generatePaymentLink(userId, name, amount);
    }
}
