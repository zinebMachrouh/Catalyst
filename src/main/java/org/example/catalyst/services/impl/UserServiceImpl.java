package org.example.catalyst.services.impl;

import lombok.AllArgsConstructor;
import org.example.catalyst.entities.User;
import org.example.catalyst.repositories.UserRepository;
import org.example.catalyst.services.UserService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
