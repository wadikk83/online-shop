package by.wadikk.onlineshop.service.impl;

import by.wadikk.onlineshop.entity.Role;
import by.wadikk.onlineshop.entity.User;
import by.wadikk.onlineshop.repository.UserRepository;
import by.wadikk.onlineshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


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
        User userFromDB = userRepository.findByLogin(user.getLogin());

        if (userFromDB != null) return false;

        user.setRole(Role.USER);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;

    }

    @Override
    public User createUser(String login, String password, String email, Role role) {
        return null;
    }
}
