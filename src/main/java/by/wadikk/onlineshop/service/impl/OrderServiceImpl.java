package by.wadikk.onlineshop.service.impl;

import by.wadikk.onlineshop.entity.Order;
import by.wadikk.onlineshop.entity.User;
import by.wadikk.onlineshop.repository.OrderRepository;
import by.wadikk.onlineshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Order> findByUser(User user) {
        return orderRepository.findByUser(user);
    }
}
