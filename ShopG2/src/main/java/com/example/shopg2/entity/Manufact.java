package com.example.shopg2.entity;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Manufact")
public class Manufact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String manufactId;
    private String manufactName;
    private String backGroundColor;
}
