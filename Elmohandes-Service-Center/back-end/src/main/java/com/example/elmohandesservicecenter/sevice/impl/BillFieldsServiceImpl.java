package com.example.elmohandesservicecenter.sevice.impl;

import com.example.elmohandesservicecenter.dto.BillFieldsDto;
import com.example.elmohandesservicecenter.mapper.BillFieldsMapper;
import com.example.elmohandesservicecenter.model.BillFields;
import com.example.elmohandesservicecenter.repo.BillFieldsRepository;
import com.example.elmohandesservicecenter.sevice.BillFieldsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BillFieldsServiceImpl implements BillFieldsService {
    @Autowired
    private BillFieldsRepository billFieldsRepository;

    @Override
    public List<BillFieldsDto> saveBillFields(List<BillFieldsDto> billFieldsDtos) {
        List<BillFields> billFields = BillFieldsMapper.BILL_FIELDS_MAPPER.toEntityList(billFieldsDtos);
        List<BillFields> savedBillFields = billFieldsRepository.saveAll(billFields);

        return BillFieldsMapper.BILL_FIELDS_MAPPER.toDtoList(savedBillFields);
    }

    @Override
    public List<BillFieldsDto> getByBillId(Long id) {
        List<BillFields> billFields = billFieldsRepository.findByBill_Id(id);
        return BillFieldsMapper.BILL_FIELDS_MAPPER.toDtoList(billFields);
    }

    @Override
    @Transactional
    public void deleteBillFieldsById(Long billId) {
        billFieldsRepository.deleteBillFieldsByBill_Id(billId);
    }
}
