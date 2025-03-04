package com.dlb.lemon_bank.domain.repository;

import com.dlb.lemon_bank.domain.entity.OrdersEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrdersRepository extends JpaRepository<OrdersEntity, Integer> {
    @Query("select o from OrdersEntity o where o.status = 'ACTIVE'")
    List<OrdersEntity> findAllActiveOrders();

    @Query("select count(o) from OrdersEntity o "
        + "where YEAR(o.date) = :year "
        + "and MONTH(o.date) = :month "
        + "and (o.status = 'ACCEPTED' or o.status = 'REJECTED')")
    Integer countAllProccessedOrders(Integer month, Integer year);
}
