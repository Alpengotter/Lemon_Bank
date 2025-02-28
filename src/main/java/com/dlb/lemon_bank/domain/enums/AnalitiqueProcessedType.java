package com.dlb.lemon_bank.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

// Нужен для обработки типов аналитики, запрашиваемых с фронта
@AllArgsConstructor
@Getter
public enum AnalitiqueProcessedType {
    ORDERS_PROCESSED("new_order"),
    LEMONS_ACCRUED("decline_order"),
    LEMONS_SPENT("reward"),
    DIAMONDS_ACCRUED("deactivate"),
    DIAMONDS_SPENT("new_employer"),
    NEW_EMPLOYERS("accept_order");

    public final String message;
}
