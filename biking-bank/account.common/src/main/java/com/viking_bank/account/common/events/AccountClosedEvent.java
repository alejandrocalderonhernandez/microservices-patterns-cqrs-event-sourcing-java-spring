package com.viking_bank.account.common.events;

import com.viking_bank.cqrs.core.events.BaseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AccountClosedEvent extends BaseEvent { }
