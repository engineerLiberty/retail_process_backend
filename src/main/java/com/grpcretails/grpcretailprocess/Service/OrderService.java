package com.grpcretails.grpcretailprocess.Service;

import com.grpcretails.grpcretailprocess.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> getOrders(String username);

    Order getOrder(String username, int orderId);

    void createOrder(Order order);

    void cancelOrder(Order order);
}
