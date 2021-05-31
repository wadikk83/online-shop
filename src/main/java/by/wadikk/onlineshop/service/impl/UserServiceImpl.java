package by.wadikk.onlineshop.service.impl;

import java.util.List;
import java.util.Optional;

import by.wadikk.onlineshop.entity.Role;
import by.wadikk.onlineshop.entity.User;
import by.wadikk.onlineshop.repository.UserRepository;
import by.wadikk.onlineshop.service.UserService;
import by.wadikk.onlineshop.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User findById(Long id) {
        Optional<User> opt = userRepository.findById(id);
        return opt.get();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public User createUser(String username, String email, String password, Role role) {
        User user = findByUsername(username);
        if (user != null) {
            return user;
        } else {
            user = new User();
            user.setUsername(username);
            user.setPassword(SecurityUtility.passwordEncoder().encode(password));
            user.setEmail(email);
            user.setRole(role);
            return userRepository.save(user);
        }
    }

    @Override
    public User createActiveUser(String username, String email, String password, Role role) {
        User user = this.createUser(username, email, password, role);
        user.setEnabled(true);
        user.setBlackList(false);
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByEmailIgnoreCase(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

}
