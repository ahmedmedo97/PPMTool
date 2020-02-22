package io.mogtabaintellegence.services;

import io.mogtabaintellegence.domain.User;
import io.mogtabaintellegence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Interface 3 on 2/21/2020.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

      @Autowired
      private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User not found");
        return user;
    }


    @Transactional
    public User loadUserById(Long id) {
        User user = userRepository.getById(id);
        if (user == null) throw new UsernameNotFoundException("User not found");
        return user;
    }
}
