package io.mogtabaintellegence.services;

import io.mogtabaintellegence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Interface 3 on 2/21/2020.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
}
