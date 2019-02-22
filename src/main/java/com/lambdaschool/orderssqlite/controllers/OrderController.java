package com.lambdaschool.orderssqlite.controllers;

import com.lambdaschool.orderssqlite.models.Order;
import com.lambdaschool.orderssqlite.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path={"/orders"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    @Autowired
    OrderRepository orderRepo;

    @GetMapping("")
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    @GetMapping("ordnum/{ordnum}")
    public Order getOrderByOrderNumber(@PathVariable long ordnum) {
        return orderRepo.findOrderByOrderNumber(ordnum);
    }

    @PostMapping("")
    public Order newOrder(@RequestBody Order newOrder) throws URISyntaxException {
        return orderRepo.save(newOrder);
    }
    @PutMapping("ordnum/{ordnum}")
    public Order updateOrder(@RequestBody Order updatedOrder, @PathVariable long ordnum) throws URISyntaxException {
        Optional<Order> foundOrder = orderRepo.findById(ordnum);
        if(foundOrder.isPresent()) {
            updatedOrder.setOrdnum(ordnum);
            orderRepo.save(updatedOrder);
            return updatedOrder;
        }
        return null;
    }
    @DeleteMapping("ordnum/{ordnum}")
    public Order deleteOrder(@PathVariable long ordnum) {
        Order foundOrder = orderRepo.findOrderByOrderNumber(ordnum);
        if(foundOrder != null) {
            orderRepo.deleteById(ordnum);
            return foundOrder;
        }
        return null;
    }
}
