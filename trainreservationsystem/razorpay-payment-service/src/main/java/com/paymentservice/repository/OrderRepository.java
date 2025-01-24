package com.paymentservice.repository;

import com.paymentservice.entity.MyOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<MyOrder,String> {
    public MyOrder findByOrderId(String orderId);
    public MyOrder findByUsername(String username);
}
