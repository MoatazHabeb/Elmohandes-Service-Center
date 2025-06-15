package com.example.elmohandesservicecenter.dto.jwt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerLoginDto {
    private String email;
    private String password;
}
