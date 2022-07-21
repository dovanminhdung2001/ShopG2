package com.example.shopg2.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SaleBill")
public class SaleBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int billId;
    private String shoeId;
    private int shoeSize;
    private int quantity;
    private int cost;
    private LocalDateTime billDate;
    private String phoneNumber;
    private String customerName;
    private String email;
    private String address;
}
