package com.lambdaschool.orderssqlite.repositories;

import com.lambdaschool.orderssqlite.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value ="SELECT * FROM customers c WHERE c.custcode = :custcode", nativeQuery = true)
    Customer findCustomerByCode(@Param("custcode") long custcode);

    @Query(value="SELECT c.custname, o.ordnum FROM customers AS c INNER JOIN orders AS o ON c.custcode = o.custcode ORDER BY c.custcode ASC", nativeQuery = true)
    List<Object[]> findAllCustomersWithOrders();

    @Query(value = "SELECT c.custname, o.ordnum FROM customers AS c INNER JOIN orders AS o WHERE c.custname Like :custname", nativeQuery = true)
    List<Object[]> findCustomerByNameWithOrders(@Param("custname") String custname);

    @Query(value = "SELECT c.custname, o.ordnum FROM customers AS c INNER JOIN orders AS o WHERE c.custcode = :custcode", nativeQuery = true)
    List<Object[]> findCustomerByNameWithOrders(@Param("custcode") long custname);
}
