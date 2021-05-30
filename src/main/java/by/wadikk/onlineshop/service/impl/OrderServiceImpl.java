package by.wadikk.onlineshop.service.impl;

import by.wadikk.onlineshop.entity.*;
import by.wadikk.onlineshop.repository.CartItemRepository;
import by.wadikk.onlineshop.repository.OrderRepository;
import by.wadikk.onlineshop.repository.ProductRepository;
import by.wadikk.onlineshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Override
    public List<Order> findByUser(User user) {
        return orderRepository.findByUser(user);
    }

    public synchronized Order createOrder(ShoppingCart shoppingCart, User user) {
        Order order = new Order();
        order.setUser(user);
        order.setOrderTotal(shoppingCart.getTotal());
        LocalDate today = LocalDate.now();
        order.setOrderStatus(OrderStatus.values()[0]);
        order = orderRepository.save(order);

        List<CartItem> cartItems = shoppingCart.getCartItems();
        for (CartItem item : cartItems) {
            Product product = item.getProduct();
            product.reduceStock(item.getQuantity());
            productRepository.save(product);
            item.setOrder(order);
            cartItemRepository.save(item);
        }
        return order;
    }

    @Override
    public Order findOrderById(Long id) {
        Optional<Order> optional = orderRepository.findById(id);
        return optional.get();
    }

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }


}
