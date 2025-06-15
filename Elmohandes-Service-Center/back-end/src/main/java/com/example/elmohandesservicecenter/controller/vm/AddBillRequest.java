package com.example.elmohandesservicecenter.controller.vm;


import com.example.elmohandesservicecenter.dto.BillDto;
import com.example.elmohandesservicecenter.dto.BillFieldsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddBillRequest {
    private BillDto billDto;
    private List<BillFieldsDto> billFieldsDtos;
}
