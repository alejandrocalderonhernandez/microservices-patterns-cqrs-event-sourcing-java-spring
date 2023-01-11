package com.viking_bank.account.query.domain;

import com.viking_bank.account.common.dto.AccountType;
import com.viking_bank.cqrs.core.domain.entities.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class BankAccount extends BaseEntity {
    @Id
    private String id;
    private String accountHolder;
    private LocalDateTime creationTime;
    private AccountType accountType;
    private Double balance;
}
