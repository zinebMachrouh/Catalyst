package org.example.catalyst.repositories;

import org.example.catalyst.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
