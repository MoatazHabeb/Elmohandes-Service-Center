package com.example.elmohandesservicecenter.model.managermodel;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Entity
@Getter
@Setter
public class Rols extends ManagerBaseEntity{
    private String code;

    @ManyToMany(mappedBy = "rols")
    private List<Manager> managers;
}
