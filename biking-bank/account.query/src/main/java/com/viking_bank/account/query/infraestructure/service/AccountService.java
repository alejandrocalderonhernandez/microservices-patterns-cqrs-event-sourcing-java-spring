package com.viking_bank.account.query.infraestructure.service;

import com.viking_bank.account.common.events.AccountClosedEvent;
import com.viking_bank.account.common.events.AccountOpenedEvent;
import com.viking_bank.account.common.events.FoundDepositedEvent;
import com.viking_bank.account.common.events.FoundsWithdrawnEvent;

public interface AccountService {

    void on(AccountOpenedEvent accountEvent);
    void on(FoundDepositedEvent accountEvent);
    void on(FoundsWithdrawnEvent accountEvent);
    void on(AccountClosedEvent accountEvent);

}
