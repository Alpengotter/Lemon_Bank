package com.dlb.lemon_bank.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class AnalitiqueResponseDto {
    private LocalDateTime date;
    private String type;
    private Integer count;
    private String currency;
}
