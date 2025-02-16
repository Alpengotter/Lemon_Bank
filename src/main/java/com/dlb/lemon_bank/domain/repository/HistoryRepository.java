package com.dlb.lemon_bank.domain.repository;

import com.dlb.lemon_bank.domain.entity.HistoryEntity;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<HistoryEntity, Integer> {

    List<HistoryEntity> findAllByDateBetweenAndUserEmailContainingOrderByIdDesc(
        LocalDate dateFrom,
        LocalDate dateTo,
        String email);
    List<HistoryEntity> findAllByDateBetweenAndUserFirstNameContainingOrUserLastNameContainingOrderByIdDesc(
        LocalDate dateFrom,
        LocalDate dateTo,
        String firstName,
        String lastName);
    List<HistoryEntity> findAllByUserIdOrderByIdDesc(Integer id);
}
