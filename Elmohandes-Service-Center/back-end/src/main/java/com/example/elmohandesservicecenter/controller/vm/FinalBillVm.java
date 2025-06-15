package com.example.elmohandesservicecenter.controller.vm;

import com.example.elmohandesservicecenter.dto.BillDto;
import com.example.elmohandesservicecenter.dto.BillFieldsDto;
import com.example.elmohandesservicecenter.dto.CarDto;
import com.example.elmohandesservicecenter.dto.CustomerDto;
import com.example.elmohandesservicecenter.dto.jwt.ManagerDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FinalBillVm {
    private CustomerDto customerDto;
    private CarDto carDto;
    private BillDto billDto;
    private List<BillFieldsDto> billFieldsDtos;
}
