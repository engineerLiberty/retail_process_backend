package com.grpcretails.grpcretailprocess.repository;

import com.grpcretails.grpcretailprocess.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<Order, Long> {
}
