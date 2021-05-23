package by.wadikk.onlineshop.service.impl;

import by.wadikk.onlineshop.entity.Role;
import by.wadikk.onlineshop.entity.User;
import by.wadikk.onlineshop.repository.UserRepository;
import by.wadikk.onlineshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.get();
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean save(User user) {
        /*User userFromDB = userRepository.findByLogin(user.getLogin());
        if (userFromDB != null) return false;
        user.setRole(Role.USER);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);*/
        userRepository.save(user);
        return true;
    }

    @Override
    public User createUser(String login, String password, String email, Role role) {
        User user = findByLogin(login);
        if (user != null) {
            return user;
        } else {
            user = new User();
            user.setLogin(login);
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(password));
            user.setEmail(email);
            //user.setRoles(Collections.singleton(Role.USER));
            user.setRole(Role.USER);
            return userRepository.save(user);
        }
    }
}
