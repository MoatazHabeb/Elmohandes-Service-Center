package com.example.elmohandesservicecenter.sevice.impl;

import com.example.elmohandesservicecenter.dto.BankDto;
import com.example.elmohandesservicecenter.mapper.BankMapper;
import com.example.elmohandesservicecenter.model.Bank;
import com.example.elmohandesservicecenter.repo.BankRepository;
import com.example.elmohandesservicecenter.sevice.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class BankServiceImpl implements BankService {
    @Autowired
    private BankRepository bankRepository;

    @Override
    public BankDto addField(BankDto bankDto) {
        if (bankRepository.existsByName(bankDto.getName())){
            throw new RuntimeException("error.nameExist");
        }
        Bank bank = bankRepository.save(BankMapper.BANK_MAPPER.toEntity(bankDto));
        return BankMapper.BANK_MAPPER.toDto(bank);
    }

    @Override
    public BankDto updateField(BankDto bankDto) {
        Optional<Bank> optionalBank = bankRepository.findById(bankDto.getId());

        if (optionalBank.isEmpty()) {
            throw new RuntimeException("Field not found with ID: " + bankDto.getId());
        }

        Bank bank = optionalBank.get();
        bank.setName(bankDto.getName());
        bank.setPrice(bankDto.getPrice());
        bank.setQuantity(bankDto.getQuantity());
        Bank savedBank = bankRepository.save(bank);
        return BankMapper.BANK_MAPPER.toDto(savedBank);
    }

    @Override
    public void deleteField(Long id) {

        bankRepository.deleteById(id);
    }

    @Override
    public List<BankDto> getFields() {
        List<Bank> banks = bankRepository.findAll();

        // Sort by name alphabetically (A-Z)
        banks.sort(Comparator.comparing(Bank::getName, String.CASE_INSENSITIVE_ORDER));

        return BankMapper.BANK_MAPPER.toDtoList(banks);
    }

    @Override
    public BankDto getFieldById(Long id) {
        Optional<Bank> optionalBank = bankRepository.findById(id);
        Bank bank = optionalBank.get();
        return BankMapper.BANK_MAPPER.toDto(bank);
    }

    @Override
    @Transactional // ensures the changes are committed
    public void doCalc(List<BankDto> bankDtos) {
        for (BankDto dto : bankDtos) {
            if (bankRepository.existsByName(dto.getName())) {
                Bank bank = bankRepository.findByName(dto.getName()).orElse(null);

                if (bank != null) {
                    Long updatedQuantity = bank.getQuantity() - dto.getQuantity();

                    // Prevent negative quantity
                    if (updatedQuantity < 0) {
                        throw new RuntimeException("error.warn");

                    }

                    bank.setQuantity(updatedQuantity);
                    bankRepository.save(bank); // save updated quantity

                    System.out.println("Updated " + bank.getName() + " to quantity: " + updatedQuantity);
                }
            }
        }
    }
}
