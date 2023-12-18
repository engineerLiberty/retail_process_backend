package com.grpcretails.grpcretailprocess.Service.serviceImpl;

import com.grpcretails.grpcretailprocess.Service.OrderService;
import com.grpcretails.grpcretailprocess.model.Item;
import com.grpcretails.grpcretailprocess.model.Order;
import com.grpcretails.grpcretailprocess.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private Map<String, List<Order>> userOrders = new HashMap<>();

    private final InventoryServiceImpl inventoryServiceImpl;

    private final CartServiceImpl cartServiceImpl;

    private Long lastOrderId;
    private final UserRepo userRepo;

    @Override
    public List<Order> getOrders(String username) {
        if (userOrders.containsKey(username)) {
            return userOrders.get(username);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Order getOrder(String username, int orderId) {
        List<Order> orders = getOrders(username);
        for (Order order : orders) {
            if (order.getId() == orderId) {
                return order;
            }
        }
        return null;
    }

    @Override
    public void createOrder(Order order) {

        // generate new order id
//        lastOrderId++;
//        order = Order.builder().id(lastOrderId).build();

        // update inventory stock
        for (Item orderItem : order.getOrderItems()) {
            inventoryServiceImpl.updateItemStock(orderItem.getId(), orderItem.getQuantity());
        }

        // add order to user's order history
        String username = order.getUser().getUsername();
        if (userOrders.containsKey(username)) {
            userOrders.get(username).add(order);
        } else {
            List<Order> orders = new ArrayList<>();
            orders.add(order);
            userOrders.put(username, orders);
        }

        // clear user's cart
        cartServiceImpl.clearCart(username);
    }

    @Override
    public void cancelOrder(Order order) {

        // update inventory stock
        for (Item orderItem : order.getOrderItems()) {
            inventoryServiceImpl.updateItemStock(orderItem.getId(), orderItem.getQuantity());
        }

    }
}
