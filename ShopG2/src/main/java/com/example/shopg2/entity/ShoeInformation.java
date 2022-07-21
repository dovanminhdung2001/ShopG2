package com.example.shopg2.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ShoeInformation")
@Data
public class ShoeInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String shoeId;
    private String shoeName;
    private int price;
    private String manufactId;
    private String information;
    private String url1;
    private String url2;
    private String url3;
    private String url4;
    private String url5;
}
