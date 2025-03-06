package com.dlb.lemon_bank.domain.mapper.service;

import com.dlb.lemon_bank.domain.dto.ExcelDateFilterDto;
import com.dlb.lemon_bank.domain.entity.UserEntity;
import com.dlb.lemon_bank.domain.repository.AnalitiqueRepository;
import com.dlb.lemon_bank.domain.repository.OrdersRepository;
import com.dlb.lemon_bank.domain.repository.UserRepository;
import com.dlb.lemon_bank.handler.ErrorType;
import com.dlb.lemon_bank.handler.exception.LemonBankException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderMapperService {
    private final UserRepository userRepository;
    private final OrdersRepository ordersRepository;
    private final AnalitiqueRepository analitiqueRepository;

    @Named("mapEmailToId")
    public UserEntity mapEmailToId(String email) {
        Optional<UserEntity> user = userRepository.findByEmailContainingIgnoreCaseAndIsActiveIsTrue(email);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new LemonBankException(ErrorType.USER_NOT_FOUND);
        }
    }

    @Named("mapDateFromString")
    public LocalDateTime mapDateFromString(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDateTime.of(LocalDate.parse(dateString, formatter), LocalTime.now());
    }

    @Named("mapDateToMonth")
    public String mapDateToMonth(Integer monthNumber) {
        Locale russianLocale = new Locale("ru", "RU");
        return Month.of(monthNumber).getDisplayName(
            java.time.format.TextStyle.FULL_STANDALONE, russianLocale
        );
    }

    @Named("mapCountOrders")
    public Integer countOrders(ExcelDateFilterDto filterDto) {
        return analitiqueRepository.countAllProcessedOrders(filterDto.getMonth(), filterDto.getYear());
    }
}
