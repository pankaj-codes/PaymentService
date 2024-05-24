package com.ecom.PaymentService.paymentgateways;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service("razorpayPaymentGateway")
public class RazorpayPaymentGateway implements PaymentGateway {

    private final RazorpayClient razorpay;

    public RazorpayPaymentGateway(RazorpayClient razorpay) {
        this.razorpay = razorpay;
    }

    @Override
    public String generatePaymentLink(Long orderId, String name, int amount) {
        //Order order = orderService.getOrder(orderId)
        //amount = order.getAmount();
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount", 1000); // 10.00
        paymentLinkRequest.put("currency","INR");
//        paymentLinkRequest.put("accept_partial",true);
//        paymentLinkRequest.put("first_min_partial_amount",100);

        // Get the current time
        Instant now = Instant.now();

        // Add 10 minutes to the current time
        Instant paymentExpiry = now.plus(Duration.ofMinutes(30));

        // Get the epoch time for 10 minutes from now
        long epochPayExpiry = paymentExpiry.getEpochSecond();

        paymentLinkRequest.put("expire_by", epochPayExpiry);
        paymentLinkRequest.put("reference_id", orderId.toString());
        paymentLinkRequest.put("description","Payment for order id: " + orderId.toString());
        JSONObject customer = new JSONObject();
        customer.put("name","+919910897943");
        customer.put("contact","Pankaj Yadav");
        customer.put("email","yadavpankaj28@gmail.com");
        paymentLinkRequest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",true);
        JSONObject notes = new JSONObject();
        notes.put("policy_name","Scaler");
        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://www.scaler.com/academy/instructor-dashboard/");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink payment = null;
        try {
            payment = razorpay.paymentLink.create(paymentLinkRequest);
        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }

        return payment.toString();
    }
}
