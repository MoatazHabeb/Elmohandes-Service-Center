package com.example.elmohandesservicecenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BillDto {
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
    private Long customer_id;
    private Long car_id;
}
