package by.wadikk.onlineshop.service;


import by.wadikk.onlineshop.entity.Role;
import by.wadikk.onlineshop.entity.User;

public interface UserService {

    User findById(Long id);

    User findByUsername(String username);

    User findByEmail(String email);

    void save(User user);

    User createUser(String username, String email, String password, Role role);

}
