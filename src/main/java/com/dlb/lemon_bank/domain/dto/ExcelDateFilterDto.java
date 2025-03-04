package com.dlb.lemon_bank.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExcelDateFilterDto {
    private Integer month;
    private Integer year;
}
