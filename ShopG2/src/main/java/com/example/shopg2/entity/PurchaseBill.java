package com.example.shopg2.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PurchaseBill")
public class PurchaseBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int billId;
    private String shoeId;
    private int shoeSize;
    private int quantity;
    private int cost;
    private LocalDateTime billDate;
}
