package com.example.clothesshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date deleteDate;

    @ManyToOne
    private Salesman salesman;

    @ManyToOne
    private Buyer buyer;

    @ManyToOne
    private Product product;
    private  int quantity;


}
