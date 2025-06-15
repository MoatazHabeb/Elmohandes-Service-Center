package com.example.elmohandesservicecenter.sevice.impl;

import com.example.elmohandesservicecenter.controller.vm.FinalBillVm;
import com.example.elmohandesservicecenter.dto.*;
import com.example.elmohandesservicecenter.mapper.BillMapper;
import com.example.elmohandesservicecenter.mapper.CarMapper;
import com.example.elmohandesservicecenter.mapper.CustomerMapper;
import com.example.elmohandesservicecenter.model.Bill;
import com.example.elmohandesservicecenter.model.BillFields;
import com.example.elmohandesservicecenter.model.Car;
import com.example.elmohandesservicecenter.model.Customer;
import com.example.elmohandesservicecenter.repo.BillRepository;
import com.example.elmohandesservicecenter.sevice.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BillFieldsService billFieldsService;
    @Autowired
    private CarService carService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BankService bankService;

    @Override
    public FinalBillVm addBill(BillDto billDto, List<BillFieldsDto> billFieldsDtos) {
        // 1. Convert DTO and save Bill first
        Bill billBeforeSaving = BillMapper.BILL_MAPPER.toEntity(billDto);
        Bill savedBill = billRepository.save(billBeforeSaving);
        Long billId = savedBill.getId();

        // 2. Set bill_id for each field
        for (BillFieldsDto dto : billFieldsDtos) {
            dto.setBill_id(billId);
        }

        // 3. Save BillFields with correct bill_id
        List<BillFieldsDto> savedBillFieldsDto = billFieldsService.saveBillFields(billFieldsDtos);

        // 4. Calculate total price
        Long finalPrice = savedBillFieldsDto.stream()
                .mapToLong(dto -> dto.getPrice() * dto.getQuantity())
                .sum();

        // 5. Update bill with net total
        savedBill.setSpareParts(finalPrice);
        savedBill.setNetBill(finalPrice + savedBill.getFactory() + savedBill.getExternalWorks() +savedBill.getTaxes()
                - savedBill.getFactoryDiscount() - savedBill.getSparePartsDiscount() - savedBill.getSpecialDiscount());
        billRepository.save(savedBill); // update with total

        // 6. Do bank-related logic
        List<BankDto> bankDtos = bankService.getFields();
        List<BankDto> matchedBanks = savedBillFieldsDto.stream()
                .map(billField -> {
                    BankDto matchingBank = bankDtos.stream()
                            .filter(bank -> bank.getName().equals(billField.getField()))
                            .findFirst()
                            .orElse(null);

                    if (matchingBank != null) {
                        BankDto newBank = new BankDto();
                        newBank.setId(matchingBank.getId());
                        newBank.setName(billField.getField());
                        newBank.setQuantity(billField.getQuantity());
                        newBank.setPrice(billField.getPrice());
                        return newBank;
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        bankService.doCalc(matchedBanks);

        // 7. Assemble final view model
        BillDto billDto1 = BillMapper.BILL_MAPPER.toDto(savedBill);
        CustomerDto customerDto = customerService.getCustomerById(billDto.getCustomer_id());
        CarDto carDto = carService.getCarById(billDto.getCar_id());

        FinalBillVm finalBillVm = new FinalBillVm();
        finalBillVm.setCustomerDto(customerDto);
        finalBillVm.setCarDto(carDto);
        finalBillVm.setBillDto(billDto1);
        finalBillVm.setBillFieldsDtos(savedBillFieldsDto);

        return finalBillVm;
    }

    @Override
    public List<FinalBillVm> getAllBills() {
        List<FinalBillVm> finalBillVmsList = new ArrayList<>();
        List<Bill> bills = billRepository.findAll();
        for (int i = 0;i< bills.size(); i++){
            FinalBillVm finalBillVms = new FinalBillVm();

            Bill bill = bills.get(i);


            Car car = bill.getCar();


            Customer customer = bill.getCustomer();


            List<BillFieldsDto> billFields = billFieldsService.getByBillId(bill.getId());


            finalBillVms.setCustomerDto(CustomerMapper.CUSTOMER_MAPPER.toDto(customer));
            finalBillVms.setCarDto(CarMapper.CAR_MAPPER.toDto(car));
            finalBillVms.setBillDto(BillMapper.BILL_MAPPER.toDto(bill));
            finalBillVms.setBillFieldsDtos(billFields);
            finalBillVmsList.add(finalBillVms);

        }
        finalBillVmsList .sort((a, b) -> Long.compare(b.getBillDto().getId(), a.getBillDto().getId()));
        return finalBillVmsList;
    }

    @Override
    public List<FinalBillVm> getBillsByCustomerId(Long customerId) {
        List<FinalBillVm> myFinalBillVms = new ArrayList<>();

        List<FinalBillVm> finalBillVms = getAllBills();
        for (int i = 0;i<finalBillVms.size();i++){
            FinalBillVm finalBillVm = finalBillVms.get(i);
            if (finalBillVm.getCustomerDto().getId().equals(customerId)) {
                myFinalBillVms.add(finalBillVm);
            }

        }
        myFinalBillVms.sort((a, b) -> Long.compare(b.getBillDto().getId(), a.getBillDto().getId()));
        return myFinalBillVms;
    }

    @Override
    @Transactional
    public void deleteBillById(Long billId) {
        billFieldsService.deleteBillFieldsById(billId);
        billRepository.deleteById(billId);
    }

}
