package com.dlb.lemon_bank.domain.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnalitiqueSummaryResponseDto {
    private Integer total;
    private List<Integer> totalMounth;
}
