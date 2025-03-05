package com.dlb.lemon_bank.domain.mapper.service;

import com.dlb.lemon_bank.domain.dto.ExcelDateFilterDto;
import com.dlb.lemon_bank.domain.repository.HistoryRepository;
import java.time.Month;
import java.util.Locale;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HistoryMapperService {
    private final HistoryRepository historyRepository;

    @Named("mapDateToMonth")
    public String mapDateToMonth(Integer monthNumber) {
        Locale russianLocale = new Locale("ru", "RU");
        return Month.of(monthNumber).getDisplayName(
            java.time.format.TextStyle.FULL_STANDALONE, russianLocale
        );
    }

    @Named("mapCountLemonsSpend")
    public Integer mapCountLemontSpend(ExcelDateFilterDto filterDto) {
        return Optional.ofNullable(historyRepository.countLemonsSpend(filterDto.getMonth(), filterDto.getYear()))
            .orElse(0);
    }

    @Named("mapCountLemonsAccrued")
    public Integer mapCountLemontAccrued(ExcelDateFilterDto filterDto) {
        return Optional.ofNullable(historyRepository.countLemonsAccrued(filterDto.getMonth(), filterDto.getYear()))
            .orElse(0);
    }

    @Named("mapCountDiamondsSpend")
    public Integer mapCountDiamondsSpend(ExcelDateFilterDto filterDto) {
        return Optional.ofNullable(historyRepository.countDiamondsSpend(filterDto.getMonth(), filterDto.getYear()))
            .orElse(0);
    }

    @Named("mapCountDiamondsAccrued")
    public Integer mapCountDiamondsAccrued(ExcelDateFilterDto filterDto) {
        return Optional.ofNullable(historyRepository.countDiamondsAccrued(filterDto.getMonth(), filterDto.getYear()))
            .orElse(0);
    }
}
