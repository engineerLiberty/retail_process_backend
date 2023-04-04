package com.grpcretails.grpcretailprocess.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Table(name = "items")
public class Item {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String name;

        @Column(nullable = false)
        private BigDecimal price;

        @Column(nullable = false)
        private int quantity;
}

