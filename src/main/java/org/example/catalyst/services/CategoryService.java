package org.example.catalyst.services;

import org.example.catalyst.dto.CategoryDTO;
import org.example.catalyst.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Page<CategoryDTO> getAllCategories(Pageable pageable);
    Page<CategoryDTO> searchCategories(String name, Pageable pageable);
    Page<ProductDTO> getProductsByCategory(Long categoryId, Pageable pageable);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO);
    void deleteCategory(Long id);
}
