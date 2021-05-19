package by.wadikk.onlineshop.repository;


import by.wadikk.onlineshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);

    User findByEmail(String email);
}
