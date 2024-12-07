package org.example.catalyst.services.impl;

import org.example.catalyst.dto.CategoryDTO;
import org.example.catalyst.dto.ProductDTO;
import org.example.catalyst.entities.Category;
import org.example.catalyst.entities.Product;
import org.example.catalyst.exceptions.AlreadyExistsException;
import org.example.catalyst.exceptions.DoesNotExistsException;
import org.example.catalyst.repositories.CategoryRepository;
import org.example.catalyst.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private Category category;
    private CategoryDTO categoryDTO;
    private Product product;
    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        category = Category.builder()
                .id(1L)
                .name("Electronics")
                .description("Electronic gadgets and devices")
                .build();

        categoryDTO = CategoryDTO.builder()
                .id(1L)
                .name("Electronics")
                .description("Electronic gadgets and devices")
                .build();

        product = Product.builder()
                .id(1L)
                .designation("Smartphone")
                .price(699.99)
                .quantity(50)
                .category(category)
                .build();

        productDTO = ProductDTO.builder()
                .id(1L)
                .designation("Smartphone")
                .price(699.99)
                .quantity(50)
                .categoryId(1L)
                .build();
    }

    @Test
    @DisplayName("Test createCategory")
    void testCreateCategory() {
        given(categoryRepository.save(any(Category.class))).willReturn(category);

        CategoryDTO result = categoryService.createCategory(categoryDTO);

        assertEquals(categoryDTO.getName(), result.getName());
        verify(categoryRepository).save(any(Category.class));
    }

    @Test
    @DisplayName("Test getAllCategories")
    void testGetAllCategories() {
        Page<Category> categories = new PageImpl<>(Collections.singletonList(category));
        given(categoryRepository.findAll(PageRequest.of(0, 10))).willReturn(categories);

        Page<CategoryDTO> result = categoryService.getAllCategories(PageRequest.of(0, 10));

        assertEquals(1, result.getTotalElements());
        assertEquals("Electronics", result.getContent().get(0).getName());
    }

    @Test
    @DisplayName("Test searchCategories")
    void testSearchCategories() {
        Page<Category> categories = new PageImpl<>(Collections.singletonList(category));
        given(categoryRepository.findByNameContainingIgnoreCase("Elect", PageRequest.of(0, 10))).willReturn(categories);

        Page<CategoryDTO> result = categoryService.searchCategories("Elect", PageRequest.of(0, 10));

        assertEquals(1, result.getTotalElements());
        assertEquals("Electronics", result.getContent().get(0).getName());
    }

    @Test
    @DisplayName("Test getProductsByCategory")
    void testGetProductsByCategory() {
        Page<Product> products = new PageImpl<>(Collections.singletonList(product));
        given(categoryRepository.findById(1L)).willReturn(Optional.of(category));
        given(productRepository.findByCategoryId(1L, PageRequest.of(0, 10))).willReturn(products);

        Page<ProductDTO> result = categoryService.getProductsByCategory(1L, PageRequest.of(0, 10));

        assertEquals(1, result.getTotalElements());
        assertEquals("Smartphone", result.getContent().get(0).getDesignation());
    }

    @Test
    @DisplayName("Test updateCategory")
    void testUpdateCategory() {
        given(categoryRepository.findById(1L)).willReturn(Optional.of(category));
        given(categoryRepository.save(category)).willReturn(category);

        CategoryDTO result = categoryService.updateCategory(1L, categoryDTO);

        assertEquals("Electronics", result.getName());
        verify(categoryRepository).save(category);
    }


    @Test
    @DisplayName("Test deleteCategory")
    void testDeleteCategory() {
        lenient().when(categoryRepository.existsById(1L)).thenReturn(true);

        categoryService.deleteCategory(1L);

        verify(categoryRepository).deleteById(1L);
    }

}
