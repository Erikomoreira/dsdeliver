package br.com.erik.dsdeliver.services;

import br.com.erik.dsdeliver.dto.OrderDTO;
import br.com.erik.dsdeliver.entities.Order;
import br.com.erik.dsdeliver.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll(){
        List<Order> orders = repository.findOrdersWithProducts();
        return orders.stream().map(OrderDTO::new).collect(Collectors.toList());
    }

}
