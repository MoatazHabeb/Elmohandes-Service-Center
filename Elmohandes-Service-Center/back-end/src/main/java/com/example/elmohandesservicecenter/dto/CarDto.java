package com.example.elmohandesservicecenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarDto {
    private Long id;
    private String chassisNumber;
    private String boardNumber;
    private String model;
    private String engineNumber;

    private Long customer_id;

}
