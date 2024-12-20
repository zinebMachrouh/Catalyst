package org.example.catalyst.controllers;

import lombok.AllArgsConstructor;
import org.example.catalyst.dto.CategoryDTO;
import org.example.catalyst.dto.ProductDTO;
import org.example.catalyst.services.CategoryService;
import org.example.catalyst.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping("/categories")
    public ResponseEntity<Page<CategoryDTO>> listCategories(Pageable pageable) {
        return ResponseEntity.ok(categoryService.getAllCategories(pageable));
    }

    @GetMapping("/categories/search")
    public ResponseEntity<Page<CategoryDTO>> searchCategories(
            @RequestParam String name,
            Pageable pageable) {
        return ResponseEntity.ok(categoryService.searchCategories(name, pageable));
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Page<ProductDTO>> listProductsByCategory(
            @PathVariable Long id,
            Pageable pageable) {
        return ResponseEntity.ok(categoryService.getProductsByCategory(id, pageable));
    }

    @GetMapping("/products")
    public ResponseEntity<Page<ProductDTO>> listProducts(Pageable pageable) {
        return ResponseEntity.ok(productService.getAllProducts(pageable));
    }

    @GetMapping("/products/search")
    public ResponseEntity<Page<ProductDTO>> searchProducts(
            @RequestParam String designation,
            Pageable pageable) {
        return ResponseEntity.ok(productService.searchProductsByDesignation(designation, pageable));
    }

}