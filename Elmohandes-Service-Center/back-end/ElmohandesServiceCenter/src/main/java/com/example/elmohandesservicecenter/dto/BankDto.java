package com.example.elmohandesservicecenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BankDto {
    private Long id;
    private String name;
    private Long quantity;
    private Long price;
}
