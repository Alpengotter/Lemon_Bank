package com.dlb.lemon_bank.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AnalitiqueType {
    NEW_ORDER("new_order"),
    DECLINE_ORDER("decline_order"),
    REWARD("reward"),
    DEACTIVATE("deactivate"),
    NEW_EMPLOYER("new_employer"),
    ACCEPT_ORDER("accept_order");

    public final String message;
}
