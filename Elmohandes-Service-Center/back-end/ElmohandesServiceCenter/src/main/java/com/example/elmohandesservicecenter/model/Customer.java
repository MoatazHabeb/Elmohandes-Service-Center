package com.example.elmohandesservicecenter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "customer")
    @JsonIgnore
    private List<Car> cars;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "customer")
    @JsonIgnore
    private List<Bill> bills;
}
