package com.viking_bank.account.common.events;

import com.viking_bank.cqrs.core.infrastructure.events.BaseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder
public class AccountClosedEvent extends BaseEvent { }
