package by.wadikk.onlineshop.repository;

import by.wadikk.onlineshop.entity.ConfirmationToken;
import by.wadikk.onlineshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);

    ConfirmationToken findConfirmationTokenByUser(User user);
}
