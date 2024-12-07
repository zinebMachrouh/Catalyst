package org.example.catalyst.services.impl;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.example.catalyst.dto.ProductDTO;
import org.example.catalyst.entities.Category;
import org.example.catalyst.entities.Product;
import org.example.catalyst.repositories.CategoryRepository;
import org.example.catalyst.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Category category;
    private Product product;
    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        category = Category.builder()
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
    @DisplayName("Test createProduct")
    void testCreateProduct() {
        given(categoryRepository.findById(1L)).willReturn(Optional.of(category));
        given(productRepository.save(any(Product.class))).willReturn(product);

        ProductDTO result = productService.createProduct(productDTO);

        assertEquals(productDTO.getDesignation(), result.getDesignation());
        verify(productRepository).save(any(Product.class));
    }

    @Test
    @DisplayName("Test getAllProducts")
    void testGetAllProducts() {
        Page<Product> products = new PageImpl<>(Collections.singletonList(product));
        given(productRepository.findAll(PageRequest.of(0, 10))).willReturn(products);

        Page<ProductDTO> result = productService.getAllProducts(PageRequest.of(0, 10));

        assertEquals(1, result.getTotalElements());
        assertEquals("Smartphone", result.getContent().get(0).getDesignation());
    }

    @Test
    @DisplayName("Test searchProductsByDesignation")
    void testSearchProductsByDesignation() {
        Page<Product> products = new PageImpl<>(Collections.singletonList(product));
        given(productRepository.findByDesignationContainingIgnoreCase("Smart", PageRequest.of(0, 10))).willReturn(products);

        Page<ProductDTO> result = productService.searchProductsByDesignation("Smart", PageRequest.of(0, 10));

        assertEquals(1, result.getTotalElements());
        assertEquals("Smartphone", result.getContent().get(0).getDesignation());
    }

    @Test
    @DisplayName("Test updateProduct")
    void testUpdateProduct() {
        given(productRepository.findById(1L)).willReturn(Optional.of(product));
        given(categoryRepository.findById(1L)).willReturn(Optional.of(category));
        given(productRepository.save(product)).willReturn(product);

        ProductDTO result = productService.updateProduct(1L, productDTO);

        assertEquals("Smartphone", result.getDesignation());
        verify(productRepository).save(product);
    }

    @Test
    @DisplayName("Test deleteProduct")
    void testDeleteProduct() {
        productService.deleteProduct(1L);

        verify(productRepository).deleteById(1L);
    }
}

