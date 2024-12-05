package org.example.catalyst.services;

import org.example.catalyst.dto.ProductDTO;
import org.example.catalyst.dto.UserDTO;
import org.example.catalyst.entities.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);
    boolean existsByUsername(String username);
    List<UserDTO> findAll();
    UserDTO updateUserRole(Long id, String role);
}
