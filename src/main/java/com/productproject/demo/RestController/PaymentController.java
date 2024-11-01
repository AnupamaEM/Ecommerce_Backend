package com.productproject.demo.RestController;

import com.productproject.demo.Service.DemoService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/store")
public class PaymentController {

    @Autowired
    private DemoService demoservice;

    @PostMapping("/user/payment/{uid}")
    public Map<String, String> createPayment(@PathVariable int uid) throws StripeException {
        try{
        int amount = demoservice.gettotalprice(uid); 
        if(amount<=0){
            throw new RuntimeException("Amount to pay is 0");
        }
    
        PaymentIntentCreateParams createParams = PaymentIntentCreateParams.builder()
                .setCurrency("USD")  
                .setAmount((long) amount)
                .build();

        PaymentIntent intent = PaymentIntent.create(createParams);

        Map<String, String> response = new HashMap<>();
        response.put("clientSecret", intent.getClientSecret());  
        System.out.println("this is ur payment id generated:"+response);
        return response;}
        catch(Exception e){
            System.out.println("error in payment try again later");
        }
        return null;
    }
}

