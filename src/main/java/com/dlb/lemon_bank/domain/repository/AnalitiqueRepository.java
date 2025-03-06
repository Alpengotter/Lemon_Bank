package com.dlb.lemon_bank.domain.repository;

import com.dlb.lemon_bank.domain.entity.AnalitiqueEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnalitiqueRepository extends JpaRepository<AnalitiqueEntity, Integer> {

    @Query("SELECT a FROM AnalitiqueEntity a WHERE " +
        "(:year IS NULL OR YEAR(a.date) = :year) AND " +
        "(:month IS NULL OR MONTH(a.date) = :month) AND " +
        "(:day IS NULL OR DAY(a.date) = :day) AND"
        + " a.type = :type")
    List<AnalitiqueEntity> findByTypeAndDate(
        String type,
        Integer year,
        Integer month,
        Integer day
    );

    @Query("SELECT a FROM AnalitiqueEntity a "
        + "WHERE (:year IS NULL OR YEAR(a.date) = :year)"
        + "AND a.type in :type")
    List<AnalitiqueEntity> findByTypeAndYear(
        List<String> type,
        Integer year
    );

    @Query("SELECT count(a) FROM AnalitiqueEntity a "
        + "WHERE (:year IS NULL OR YEAR(a.date) = :year)"
        + "AND a.type in :type")
    Integer countByTypeAndYear(
        List<String> type,
        Integer year
    );

    @Query("SELECT count(a) FROM AnalitiqueEntity a "
        + "where YEAR(a.date) = :year "
        + "and (:month IS NULL OR MONTH(a.date) = :month) "
        + "and (a.type = 'accept_order' or a.type = 'decline_order')")
    Integer countAllProcessedOrders(
        Integer month,
        Integer year
    );

    @Query("SELECT a FROM AnalitiqueEntity a "
        + "where YEAR(a.date) = :year "
        + "and (:month IS NULL OR MONTH(a.date) = :month) "
        + "and (a.type = 'accept_order' or a.type = 'decline_order')")
    List<AnalitiqueEntity> findAllProcessedOrders(
        Integer month,
        Integer year
    );

    @Query("SELECT count(a) FROM AnalitiqueEntity a "
        + "where YEAR(a.date) = :year "
        + "and a.type = 'new_employer'")
    Integer countNewEmployers(Integer year);

    @Query("SELECT a FROM AnalitiqueEntity a "
        + "where YEAR(a.date) = :year "
        + "and a.type = 'new_employer'")
    List<AnalitiqueEntity> findNewEmployers(Integer year);

    @Query("select h from AnalitiqueEntity h "
        + "where YEAR(h.date) = :year "
        + "and (:month IS NULL OR MONTH(h.date) = :month) "
        + "and h.currency = 'lemons'"
        + "and h.count < 0 ")
    List<AnalitiqueEntity> findByLemonsSpend(Integer month, Integer year);

    @Query("select h from AnalitiqueEntity h "
        + "where YEAR(h.date) = :year "
        + "and (:month IS NULL OR MONTH(h.date) = :month) "
        + "and h.currency = 'lemons'"
        + "and h.count > 0 "
        + "and h.type = 'reward'")
    List<AnalitiqueEntity> findByLemonsAccrued(Integer month, Integer year);

    @Query("select h from AnalitiqueEntity h "
        + "where YEAR(h.date) = :year "
        + "and (:month IS NULL OR MONTH(h.date) = :month) "
        + "and h.currency = 'diamonds'"
        + "and h.count < 0 ")
    List<AnalitiqueEntity> findByDiamondsSpend(Integer month, Integer year);

    @Query("select h from AnalitiqueEntity h "
        + "where YEAR(h.date) = :year "
        + "and (:month IS NULL OR MONTH(h.date) = :month) "
        + "and h.currency = 'diamonds'"
        + "and h.count > 0 "
        + "and h.type = 'reward'")
    List<AnalitiqueEntity> findByDiamondsAccrued(Integer month, Integer year);
}
