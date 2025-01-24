package com.paymentservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "orderDetails")
public class MyOrder {
    @Id
    private String myOrderId;

    private String username;
    private String orderId;
    private  String amount;
    private String status;
    private String paymentId;

}
