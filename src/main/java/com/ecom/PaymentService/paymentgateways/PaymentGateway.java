package com.ecom.PaymentService.paymentgateways;

import org.springframework.stereotype.Service;

@Service
public interface PaymentGateway {
    public String generatePaymentLink(Long orderId, String name, int amount);
}
