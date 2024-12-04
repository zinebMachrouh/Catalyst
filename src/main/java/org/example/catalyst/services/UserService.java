package org.example.catalyst.services;

import org.example.catalyst.entities.User;

public interface UserService {
    void saveUser(User user);
    boolean existsByUsername(String username);
}
