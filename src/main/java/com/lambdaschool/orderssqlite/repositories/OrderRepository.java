package com.lambdaschool.orderssqlite.repositories;

import com.lambdaschool.orderssqlite.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value="SELECT * FROM orders o WHERE o.ordnum = :ordnum", nativeQuery = true)
    Order findOrderByOrderNumber(@Param("ordnum") long ordnum);
}
