package by.wadikk.onlineshop.service;

import by.wadikk.onlineshop.entity.Order;
import by.wadikk.onlineshop.entity.ShoppingCart;
import by.wadikk.onlineshop.entity.User;

import java.util.List;

public interface OrderService {

    List<Order> findByUser(User user);

    Order createOrder(ShoppingCart shoppingCart,User user);

    Order findOrderById(Long id);

    List<Order> findAllOrders();

    void deleteOrderById(Long id);

    void saveOrder(Order newOrder);
}
