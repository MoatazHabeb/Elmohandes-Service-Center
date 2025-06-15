package com.example.elmohandesservicecenter.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BillFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String field;
    private Long quantity;
    private Long price;
    @ManyToOne
    @JoinColumn(name = "bill_id", nullable = false)
    private Bill bill;


}
