package com.dlb.lemon_bank.service;

import com.dlb.lemon_bank.domain.dto.AnalitiqueResponseDto;
import com.dlb.lemon_bank.domain.dto.OrderResponseDto;
import com.dlb.lemon_bank.domain.dto.OrderUpdateStatusDto;
import com.dlb.lemon_bank.domain.dto.OrderWebhookDto;
import com.dlb.lemon_bank.domain.entity.AnalitiqueEntity;
import com.dlb.lemon_bank.domain.entity.OrdersEntity;
import com.dlb.lemon_bank.domain.mapper.AnalitiqueMapper;
import com.dlb.lemon_bank.domain.mapper.OrderMapper;
import com.dlb.lemon_bank.domain.repository.AnalitiqueRepository;
import com.dlb.lemon_bank.domain.repository.OrdersRepository;
import com.dlb.lemon_bank.handler.ErrorType;
import com.dlb.lemon_bank.handler.exception.LemonBankException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnalitiqueService {
    private final AnalitiqueRepository analitiqueRepository;
    private final AnalitiqueMapper analitiqueMapper;

    public List<AnalitiqueResponseDto> getAnalitique(String type, Integer year, Integer month, Integer day) {
        List<AnalitiqueEntity> analitiqueEntityList = analitiqueRepository
            .findByTypeAndDate(type, year, month, day);
        return analitiqueMapper.toAnalitiqueResponseDtoList(analitiqueEntityList);
    }

    @Transactional
    public void saveAnalitique(String type,
                                Integer count,
                                String currency) {
        AnalitiqueEntity analitiqueEntity = AnalitiqueEntity.builder()
            .type(type)
            .count(count)
            .currency(currency)
            .date(LocalDateTime.now())
            .build();
        analitiqueRepository.save(analitiqueEntity);
    }
}
