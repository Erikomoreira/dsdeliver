package br.com.erik.dsdeliver.services;

import br.com.erik.dsdeliver.dto.OrderDTO;
import br.com.erik.dsdeliver.dto.ProductDTO;
import br.com.erik.dsdeliver.entities.Order;
import br.com.erik.dsdeliver.entities.OrderStatus;
import br.com.erik.dsdeliver.entities.Product;
import br.com.erik.dsdeliver.repositories.OrderRepository;
import br.com.erik.dsdeliver.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll(){
        List<Order> orders = orderRepository.findOrdersWithProducts();
        return orders.stream().map(OrderDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto){
        Order order = new Order(null, dto.getAddress(), dto.getLatitude(),
                dto.getLongitude(), Instant.now(), OrderStatus.PENDING);

        for (ProductDTO p : dto.getProducts()){
            Product product = productRepository.getOne(p.getId());
            order.getProducts().add(product);
        }
        order = orderRepository.save(order);
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO setDelivered(Long id){
        Order order = orderRepository.getOne(id); // getOne não vai no banco de dados
        order.setStatus(OrderStatus.DELIVERED);
        order = orderRepository.save(order);
        return new OrderDTO(order);
    }

}
