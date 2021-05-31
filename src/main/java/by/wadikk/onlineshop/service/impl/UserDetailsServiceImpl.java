package by.wadikk.onlineshop.service.impl;

import by.wadikk.onlineshop.entity.User;
import by.wadikk.onlineshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("MyUserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userFindByUsername = userRepository.findByUsername(username);
        User userFindByEmail = userRepository.findByEmail(username);
        //User userFindByGoogleUsername = userRepository.findByGoogleUsername(username);
        //User userFindByGoogleName = userRepository.findByGoogleName(username);


        /*ser findByUsername(String email);
        User findByName(String name);*/
        //User userFindByName = userRepository.findByName(username);
        //User userFindByGoogleUsername = userRepository.findByGoogleUsername(username);
        //User userFindByGoogleName = userRepo.findByGoogleName(username);

        if (userFindByUsername != null) {
            return userFindByUsername;
        }

       /* if (userFindByName != null) {
            return userFindByName;
        }

        if (userFindByGoogleUsername != null) {
            return userFindByGoogleUsername;
        }

        if (userFindByGoogleName != null) {
            return userFindByGoogleName;
        }*/

        return null;
    }
}
