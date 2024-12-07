package org.example.catalyst.services.impl;

import lombok.AllArgsConstructor;
import org.example.catalyst.dto.ProductDTO;
import org.example.catalyst.entities.Category;
import org.example.catalyst.entities.Product;
import org.example.catalyst.repositories.CategoryRepository;
import org.example.catalyst.repositories.ProductRepository;
import org.example.catalyst.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Page<ProductDTO> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(this::toDTO);
    }

    @Override
    public Page<ProductDTO> searchProductsByDesignation(String designation, Pageable pageable) {
        return productRepository.findByDesignationContainingIgnoreCase(designation, pageable)
                .map(this::toDTO);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = Product.builder()
                .designation(productDTO.getDesignation())
                .price(productDTO.getPrice())
                .quantity(productDTO.getQuantity())
                .category(category)
                .build();

        Product savedProduct = productRepository.save(product);
        return toDTO(savedProduct);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setDesignation(productDTO.getDesignation());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setCategory(category);

        return toDTO(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    private ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .designation(product.getDesignation())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .categoryId(product.getCategory().getId())
                .build();
    }
}
