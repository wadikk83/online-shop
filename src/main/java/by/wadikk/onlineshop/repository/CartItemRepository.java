package by.wadikk.onlineshop.repository;

import by.wadikk.onlineshop.entity.CartItem;
import by.wadikk.onlineshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findAllByUser(User user);

}
