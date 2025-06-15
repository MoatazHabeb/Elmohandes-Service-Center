package com.example.elmohandesservicecenter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dateOfNow;
    private String receptionDate;
    private String deliveryDate;
    private String customerRepresentative;
    private String lastServiceDate;
    private Long factory;
    private Long spareParts;
    private Long externalWorks;
    private Long taxes;
    private Long factoryDiscount;
    private Long sparePartsDiscount;
    private Long specialDiscount;
    private Long netBill;
    private String kilometers;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    private List<BillFields> billFields;



    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
}
