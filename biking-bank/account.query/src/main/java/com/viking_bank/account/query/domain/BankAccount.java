package com.viking_bank.account.query.domain;

import com.viking_bank.account.common.dto.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BankAccount {
    @Id
    private String id;
    private String accountHolder;
    private LocalDateTime creationTime;
    private AccountType accountType;
    private Double balance;
}
