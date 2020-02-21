package io.mogtabaintellegence.services;

import io.mogtabaintellegence.domain.User;
import io.mogtabaintellegence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Interface 3 on 2/21/2020.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser(User newUser) {
       newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
       // username has to be unique(custom exception)

        //Make sure that password and confirmPassword match

        // we don't persist or show confirmPassword
       return userRepository.save(newUser);
    }
}
