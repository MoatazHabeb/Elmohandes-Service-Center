package com.example.elmohandesservicecenter.sevice;


import com.example.elmohandesservicecenter.controller.vm.FinalBillVm;
import com.example.elmohandesservicecenter.dto.BillDto;
import com.example.elmohandesservicecenter.dto.BillFieldsDto;

import java.util.List;

public interface BillService {
    FinalBillVm addBill(BillDto billDto, List<BillFieldsDto> billFieldsDtos);
    List<FinalBillVm> getAllBills();
    List<FinalBillVm> getBillsByCustomerId(Long customerId);
    void deleteBillById(Long billId);
}
