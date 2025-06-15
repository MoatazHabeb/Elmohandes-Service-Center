package com.example.elmohandesservicecenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BillFieldsDto {
    private Long id;
    private String field;
    private Long quantity;
    private Long price;
    private Long bill_id;
}
