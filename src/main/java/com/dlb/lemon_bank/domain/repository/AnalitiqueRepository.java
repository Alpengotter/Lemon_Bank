package com.dlb.lemon_bank.domain.repository;

import com.dlb.lemon_bank.domain.entity.AnalitiqueEntity;
import com.dlb.lemon_bank.domain.entity.OrdersEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
        + "AND a.type = :type")
    List<AnalitiqueEntity> findByTypeAndYear(
        String type,
        Integer year
    );

    @Query("SELECT count(a) FROM AnalitiqueEntity a "
        + "where YEAR(a.date) = :year "
        + "and MONTH(a.date) = :month "
        + "and (a.type = 'accept_order' or a.type = 'decline_order')")
    Integer countAllProccessedOrders(
        Integer month,
        Integer year
    );
}
