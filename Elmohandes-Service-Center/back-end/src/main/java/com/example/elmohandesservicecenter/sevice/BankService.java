package com.example.elmohandesservicecenter.sevice;

import com.example.elmohandesservicecenter.dto.BankDto;

import java.util.List;

public interface BankService {
    BankDto addField(BankDto bankDto);
    BankDto updateField(BankDto bankDto);
    void deleteField(Long id);
    List<BankDto> getFields();
    BankDto getFieldById(Long id);

    void doCalc(List<BankDto> bankDtos);
}
