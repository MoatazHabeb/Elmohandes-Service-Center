package com.example.elmohandesservicecenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDto {
    private Long id;
    private String reason;
    private Long newBalance;
    private String dateOfReason;
    private Long team_id;
}
