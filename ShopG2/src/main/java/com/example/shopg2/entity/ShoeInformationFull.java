package com.example.shopg2.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ShoeInformationFulls")
public class ShoeInformationFull {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String shoeId;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private  int price;
}
