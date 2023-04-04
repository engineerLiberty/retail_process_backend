package com.grpcretails.grpcretailprocess.repository;

import com.grpcretails.grpcretailprocess.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
