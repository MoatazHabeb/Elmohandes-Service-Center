package com.example.elmohandesservicecenter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "account") // or "\"account\"" if the table was created with quotes
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reason;

    @Column(name = "new_balance")
    private Long newBalance;

    private String dateOfReason;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
