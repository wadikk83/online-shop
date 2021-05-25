package by.wadikk.onlineshop.repository;

import by.wadikk.onlineshop.entity.Order;
import by.wadikk.onlineshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);
}
