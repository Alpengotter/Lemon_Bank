package com.dlb.lemon_bank.domain.repository;

import com.dlb.lemon_bank.domain.entity.HistoryEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HistoryRepository extends JpaRepository<HistoryEntity, Integer> {

    @Query("select h from HistoryEntity h "
        + "left join UserEntity u "
        + "on u.id = h.user.id "
        + "where (h.date between :dateFrom and :dateTo) "
        + "and (lower(u.email) like lower(concat('%', :email, '%')))"
        + "order by h.id desc ")
    List<HistoryEntity> findAllByDateBetweenAndUserEmailContainingOrderByIdDesc(
        LocalDateTime dateFrom,
        LocalDateTime dateTo,
        String email);

    @Query("select h from HistoryEntity h "
        + "left join UserEntity u "
        + "on u.id = h.user.id "
        + "where (h.date between :dateFrom and :dateTo) "
        + "and ((lower(u.firstName) like lower(concat('%', :firstName, '%'))) or (lower(u.lastName) like lower(concat('%', :lastName, '%'))))"
        + "order by h.id desc ")
    List<HistoryEntity> findAllByDateBetweenAndUserFirstNameContainingOrUserLastNameContainingOrderByIdDesc(
        LocalDateTime dateFrom,
        LocalDateTime dateTo,
        String firstName,
        String lastName);

    List<HistoryEntity> findAllByUserIdOrderByIdDesc(Integer id);

    @Query("select abs(sum(h.value)) from HistoryEntity h "
        + "where YEAR(h.date) = :year "
        + "and (:month IS NULL OR MONTH(h.date) = :month) "
        + "and h.currency = 'lemons'"
        + "and h.value < 0")
    Integer countLemonsSpend(Integer month, Integer year);

    @Query("select sum(h.value) from HistoryEntity h "
        + "where YEAR(h.date) = :year "
        + "and (:month IS NULL OR MONTH(h.date) = :month) "
        + "and h.currency = 'lemons'"
        + "and h.value > 0")
    Integer countLemonsAccrued(Integer month, Integer year);

    @Query("select abs(sum(h.value)) from HistoryEntity h "
        + "where YEAR(h.date) = :year "
        + "and (:month IS NULL OR MONTH(h.date) = :month) "
        + "and h.currency = 'diamonds'"
        + "and h.value < 0")
    Integer countDiamondsSpend(Integer month, Integer year);

    @Query("select sum(h.value) from HistoryEntity h "
        + "where YEAR(h.date) = :year "
        + "and (:month IS NULL OR MONTH(h.date) = :month) "
        + "and h.currency = 'diamonds'"
        + "and h.value > 0")
    Integer countDiamondsAccrued(Integer month, Integer year);

    @Query("select h from HistoryEntity h "
        + "where YEAR(h.date) = :year "
        + "and (:month IS NULL OR MONTH(h.date) = :month) "
        + "and h.currency = 'lemons'"
        + "and h.value < 0")
    List<HistoryEntity> findByLemonsSpend(Integer month, Integer year);

    @Query("select h from HistoryEntity h "
        + "where YEAR(h.date) = :year "
        + "and (:month IS NULL OR MONTH(h.date) = :month) "
        + "and h.currency = 'lemons'"
        + "and h.value > 0")
    List<HistoryEntity> findByLemonsAccrued(Integer month, Integer year);

    @Query("select h from HistoryEntity h "
        + "where YEAR(h.date) = :year "
        + "and (:month IS NULL OR MONTH(h.date) = :month) "
        + "and h.currency = 'diamonds'"
        + "and h.value < 0")
    List<HistoryEntity> findByDiamondsSpend(Integer month, Integer year);

    @Query("select h from HistoryEntity h "
        + "where YEAR(h.date) = :year "
        + "and (:month IS NULL OR MONTH(h.date) = :month) "
        + "and h.currency = 'diamonds'"
        + "and h.value > 0")
    List<HistoryEntity> findByDiamondsAccrued(Integer month, Integer year);
}
