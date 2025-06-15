package com.example.elmohandesservicecenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeamDto {
    private Long id;
    private String name;
    private String phoneNumber;
    private String age;
    private String image;
    private Long balance;
    private boolean active;
}
