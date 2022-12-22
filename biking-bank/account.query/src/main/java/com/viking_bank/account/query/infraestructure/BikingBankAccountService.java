package com.viking_bank.account.query.infraestructure;

import com.viking_bank.account.common.events.AccountClosedEvent;
import com.viking_bank.account.common.events.AccountOpenedEvent;
import com.viking_bank.account.common.events.FoundDepositedEvent;
import com.viking_bank.account.common.events.FoundsWithdrawnEvent;
import com.viking_bank.account.query.domain.AccountRepository;
import com.viking_bank.account.query.domain.BankAccount;
import org.springframework.stereotype.Service;

@Service
public class BikingBankAccountService implements AccountService {
    private final AccountRepository accountRepository;

    public BikingBankAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void on(AccountOpenedEvent accountEvent) {
        var bankAccount = BankAccount
                .builder()
                .id(accountEvent.getId())
                .accountHolder(accountEvent.getAccountHolder())
                .accountType(accountEvent.getAccountType())
                .balance(accountEvent.getOpeningBalance())
                .creationTime(accountEvent.getCurrentDate())
                .build();

        accountRepository.save(bankAccount);
    }

    @Override
    public void on(FoundDepositedEvent accountEvent) {
        var bankAccountResponse = accountRepository.findById(accountEvent.getId());

        var bankAccount = bankAccountResponse.orElseThrow(IllegalArgumentException::new);

        var newBalance = bankAccount.getBalance() + accountEvent.getAmount();

        bankAccount.setBalance(newBalance);

        this.accountRepository.save(bankAccount);
    }

    @Override
    public void on(FoundsWithdrawnEvent accountEvent) {
        var bankAccountResponse = accountRepository.findById(accountEvent.getId());

        var bankAccount = bankAccountResponse.orElseThrow(IllegalArgumentException::new);

        var newBalance = bankAccount.getBalance() - accountEvent.getAmount();

        bankAccount.setBalance(newBalance);

        this.accountRepository.save(bankAccount);
    }

    @Override
    public void on(AccountClosedEvent accountEvent) {
        this.accountRepository.deleteById(accountEvent.getId());
    }
}
