package org.example.catalyst.services.impl;

import lombok.AllArgsConstructor;
import org.example.catalyst.dto.UserDTO;
import org.example.catalyst.entities.User;
import org.example.catalyst.entities.enums.Role;
import org.example.catalyst.repositories.UserRepository;
import org.example.catalyst.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public UserDTO updateUserRole(Long id, String role) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setRole(Role.ADMIN);
        User updatedUser = userRepository.save(user);

        return toDTO(updatedUser);
    }

    private UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
