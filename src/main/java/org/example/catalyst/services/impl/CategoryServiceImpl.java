package org.example.catalyst.services.impl;

import lombok.AllArgsConstructor;
import org.example.catalyst.dto.CategoryDTO;
import org.example.catalyst.dto.ProductDTO;
import org.example.catalyst.entities.Category;
import org.example.catalyst.entities.Product;
import org.example.catalyst.repositories.CategoryRepository;
import org.example.catalyst.repositories.ProductRepository;
import org.example.catalyst.services.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    public final CategoryRepository categoryRepository;
    public final ProductRepository productRepository;

    @Override
    public Page<CategoryDTO> getAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable).map(this::toDTO);
    }

    @Override
    public Page<CategoryDTO> searchCategories(String name, Pageable pageable) {
        return categoryRepository.findByNameContainingIgnoreCase(name, pageable).map(this::toDTO);
    }

    @Override
    public Page<ProductDTO> getProductsByCategory(Long categoryId, Pageable pageable) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Page<Product> products = productRepository.findByCategoryId(category.getId(), pageable);

        return products.map(product -> ProductDTO.builder()
                .id(product.getId())
                .designation(product.getDesignation())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .categoryId(product.getCategory().getId())
                .build());

    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = toEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return toDTO(savedCategory);
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (existingCategory.isEmpty()) {
            throw new RuntimeException("Category not found");
        }

        Category category = existingCategory.get();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        return toDTO(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    private CategoryDTO toDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    private Category toEntity(CategoryDTO categoryDTO) {
        return Category.builder()
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .build();
    }
}
