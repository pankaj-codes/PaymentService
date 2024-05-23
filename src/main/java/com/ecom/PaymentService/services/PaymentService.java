package com.ecom.PaymentService.services;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentService {
    public String initiatePayment(Long userId, String name, int amount) throws RazorpayException, StripeException;
}
