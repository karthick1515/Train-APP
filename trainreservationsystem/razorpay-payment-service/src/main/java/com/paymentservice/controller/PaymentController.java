package com.paymentservice.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymentservice.config.RazorPayClientConfig;
import com.paymentservice.entity.MyOrder;
import com.paymentservice.repository.OrderRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class PaymentController {

    private RazorpayClient client;
    private RazorPayClientConfig razorPayClientConfig;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    public PaymentController(RazorPayClientConfig razorPayClientConfig) throws RazorpayException{
        this.razorPayClientConfig=razorPayClientConfig;
        //System.out.println(razorPayClientConfig.getKey());
        //System.out.println(razorPayClientConfig.getSecret());
        this.client=new RazorpayClient(razorPayClientConfig.getKey(), razorPayClientConfig.getSecret());
    }
    @PostMapping("/createOrder")
    public String createOrder(@RequestBody MyOrder myOrder) throws RazorpayException {
       
    	System.out.println(myOrder);
        int amount = Integer.parseInt(myOrder.getAmount());
        JSONObject obj=new JSONObject();
        obj.put("amount",amount*100);
        obj.put("currency","INR");
        obj.put("receipt","txn_235425");
 
        //creating order //hitting razorpay endpoint
        Order order=client.orders.create(obj);
        System.out.println(order);
   
        //save order in db
        MyOrder myOrder1=new MyOrder();
        myOrder1.setAmount(order.get("amount")+"");
        myOrder1.setOrderId(order.get("id"));
        myOrder1.setPaymentId(null);
        myOrder1.setStatus("created");
        myOrder1.setUsername(myOrder.getUsername());
        MyOrder save = this.orderRepository.save(myOrder1);
        System.out.println(save);
 
    return order.toString();
    }
 

    @PutMapping("/updateOrder")
    public ResponseEntity<?> updateOrder(@RequestBody MyOrder order){
    	//System.out.println("Hi");
        MyOrder myOrder = orderRepository.findByUsername(order.getUsername());
        myOrder.setPaymentId(order.getPaymentId());
        myOrder.setStatus("paid");
        orderRepository.save(myOrder);
        return  ResponseEntity.ok("");
    }
   
}

