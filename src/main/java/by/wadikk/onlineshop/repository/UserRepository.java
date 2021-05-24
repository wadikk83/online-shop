package by.wadikk.onlineshop.repository;

import by.wadikk.onlineshop.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);

}
