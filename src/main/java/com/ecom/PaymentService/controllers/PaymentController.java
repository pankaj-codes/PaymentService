package com.ecom.PaymentService.controllers;

import com.ecom.PaymentService.dtos.InitiatePaymentRequestDto;
import com.ecom.PaymentService.services.PaymentService;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//If we make it a controller then 404 will be received.
@RestController
@RequestMapping("/payments")
public class PaymentController {
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/")
    public String initiatePayment(@RequestBody InitiatePaymentRequestDto initiatePaymentRequetDto){
        String payment = null;
        try {
            payment = paymentService.initiatePayment(initiatePaymentRequetDto.getOrderId(),
                    initiatePaymentRequetDto.getName(), initiatePaymentRequetDto.getAmount());
        } catch (RazorpayException | StripeException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return payment;
    }



}
