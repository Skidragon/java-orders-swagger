package com.lambdaschool.orderssqlite.repositories;

import com.lambdaschool.orderssqlite.models.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AgentRepository extends JpaRepository<Agent, Long> {

    @Query(value="SELECT * FROM agents a WHERE a.agentcode = :agentcode", nativeQuery = true)
    Agent findAgentByCode(@Param("agentcode") long agentcode);

    @Query(value="SELECT a.agentcode, a.agentname, c.custname FROM agents AS a INNER JOIN customers AS c WHERE a.agentcode = c.agentcode ORDER BY a.agentcode ASC", nativeQuery = true)
    List<Object[]> findAllAgentsWithCustomers();

    @Query(value="SELECT a.agentname, o.ordnum, o.orddescription FROM agents AS a INNER JOIN orders as o ON a.agentcode = o.agentcode", nativeQuery = true)
    List<Object[]> findAllAgentsWithOrders();
}
