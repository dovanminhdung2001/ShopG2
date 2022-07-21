package com.example.shopg2.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "QuantityInStock")
public class QuantityInStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String shoeId;
    private int shoeSize;
    private int quantity;
}
