package com.dlb.lemon_bank.service;

import com.dlb.lemon_bank.domain.dto.AnalitiqueResponseDto;
import com.dlb.lemon_bank.domain.dto.AnalitiqueSummaryResponseDto;
import com.dlb.lemon_bank.domain.dto.OrderResponseDto;
import com.dlb.lemon_bank.domain.dto.OrderUpdateStatusDto;
import com.dlb.lemon_bank.domain.dto.OrderWebhookDto;
import com.dlb.lemon_bank.domain.entity.AnalitiqueEntity;
import com.dlb.lemon_bank.domain.entity.OrdersEntity;
import com.dlb.lemon_bank.domain.mapper.AnalitiqueMapper;
import com.dlb.lemon_bank.domain.mapper.OrderMapper;
import com.dlb.lemon_bank.domain.repository.AnalitiqueRepository;
import com.dlb.lemon_bank.domain.repository.HistoryRepository;
import com.dlb.lemon_bank.domain.repository.OrdersRepository;
import com.dlb.lemon_bank.handler.ErrorType;
import com.dlb.lemon_bank.handler.exception.LemonBankException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnalitiqueService {
    private final AnalitiqueRepository analitiqueRepository;
    private final AnalitiqueMapper analitiqueMapper;
    private final HistoryRepository historyRepository;

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

    @Transactional
    public List<AnalitiqueSummaryResponseDto> getAnalitiqueSummary(List<String> types, Integer year) {
        List<AnalitiqueSummaryResponseDto> result = new ArrayList<>();
        if (Objects.isNull(types)) {
            types = new ArrayList<>();
            types.addAll(List.of(
                "orders_processed",
                "lemons_accrued",
                "lemons_spend",
                "diamonds_accrued",
                "diamonds_spend",
                "new_employer"));
        }

        Map<String, List<AnalitiqueEntity>> analitiqueForTypes = getAnalitiqueForTypes(types, year);

        for (String type : types) {
            //TODO Посмотреть, как это можно оптимизировать
            List<Integer> totalMonthList = new ArrayList<>(Collections.nCopies(12, 0));
            Integer total = 0;
            List<AnalitiqueEntity> analitiqueEntities = analitiqueForTypes.get(type);
            for (AnalitiqueEntity entity: analitiqueEntities) {
                if (StringUtils.contains(entity.getType(), "reward") ||
                    StringUtils.equals(entity.getType(), "order")) {
                    int monthValue = entity.getDate().getMonthValue();
                    totalMonthList.set(monthValue - 1, totalMonthList.get(monthValue - 1) + Math.abs(entity.getCount()));
                    total += Math.abs(entity.getCount());
                } else {
                    int monthValue = entity.getDate().getMonthValue();
                    totalMonthList.set(monthValue - 1, totalMonthList.get(monthValue - 1) + 1);
                    total += 1;
                }
            }
            result.add(AnalitiqueSummaryResponseDto.builder()
                    .type(StringUtils.toRootUpperCase(type))
                    .totalMounth(totalMonthList)
                    .total(total)
                .build());
        }

        return result;
    }

    private Map<String, List<AnalitiqueEntity>> getAnalitiqueForTypes(List<String> types, Integer year) {
        Map<String, List<AnalitiqueEntity>> result = new HashMap<>();
        for (String type : types) {
            switch (type) {
                case "orders_processed" -> {
                    List<AnalitiqueEntity> countProcessedOrders = analitiqueRepository.findAllProcessedOrders(
                        null, year);
                    result.put("orders_processed", countProcessedOrders);
                }
                case "lemons_accrued" -> {
                    List<AnalitiqueEntity> findByLemonsAccrued = analitiqueMapper.toAnalitiqueEntityList(
                        historyRepository.findByLemonsAccrued(null, year));
                    result.put("lemons_accrued", findByLemonsAccrued);
                }
                case "lemons_spend" -> {
                    List<AnalitiqueEntity> findByLemonsSpend = analitiqueMapper.toAnalitiqueEntityList(
                        historyRepository.findByLemonsSpend(null, year));
                    result.put("lemons_spend", findByLemonsSpend);
                }
                case "diamonds_spend" -> {
                    List<AnalitiqueEntity> findByDiamondsSpend = analitiqueMapper.toAnalitiqueEntityList(
                        historyRepository.findByDiamondsSpend(null, year));
                    result.put("diamonds_spend", findByDiamondsSpend);
                }
                case "diamonds_accrued" -> {
                    List<AnalitiqueEntity> findByDiamondsAccrued = analitiqueMapper.toAnalitiqueEntityList(
                        historyRepository.findByDiamondsAccrued(null, year));
                    result.put("diamonds_accrued", findByDiamondsAccrued);
                }
                case "new_employer" -> {
                    List<AnalitiqueEntity> findByNewEmployers = analitiqueRepository.findNewEmployers(year);
                    result.put("new_employer", findByNewEmployers);
                }
                default -> throw new IllegalArgumentException("Unknown type: " + type);
            }
        }
        return result;
    }
}
