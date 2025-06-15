package com.example.elmohandesservicecenter.sevice;

import com.example.elmohandesservicecenter.dto.BillFieldsDto;

import java.util.List;

public interface BillFieldsService {
    List<BillFieldsDto> saveBillFields( List<BillFieldsDto> billFieldsDtos);
    List<BillFieldsDto> getByBillId(Long id);

    void deleteBillFieldsById(Long billId);

}
