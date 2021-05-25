package by.wadikk.onlineshop.service;

import by.wadikk.onlineshop.entity.Order;
import by.wadikk.onlineshop.entity.User;

import java.util.List;

public interface OrderService {

    List<Order> findByUser(User user);
}
