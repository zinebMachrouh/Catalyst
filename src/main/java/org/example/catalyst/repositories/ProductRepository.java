package org.example.catalyst.repositories;

import org.example.catalyst.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByDesignationContainingIgnoreCase(String designation, Pageable pageable);
    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);
}
