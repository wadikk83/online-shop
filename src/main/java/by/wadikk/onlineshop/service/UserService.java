package by.wadikk.onlineshop.service;


import by.wadikk.onlineshop.entity.Role;
import by.wadikk.onlineshop.entity.User;

import java.util.List;

public interface UserService {

    User findById(Long id);

    User findByLogin(String login);

    User findByEmail(String email);

    boolean save(User user);

    User createUser(String login, String password, String email, Role role);

}
