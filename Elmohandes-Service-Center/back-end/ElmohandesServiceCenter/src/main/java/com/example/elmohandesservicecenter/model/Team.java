package com.example.elmohandesservicecenter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phoneNumber;
    private String age;
    private String image;
    private Long balance;
    private boolean active;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "team")
    @JsonIgnore
    private List<Account> accountList;
}
