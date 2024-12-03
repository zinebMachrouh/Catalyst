package org.example.catalyst.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductDTO {
    @Builder.Default
    private Long id = 0L;

    @NotBlank(message = "Designation is required")
    @Size(min = 3, max = 50, message = "Designation must be between 3 and 50 characters")
    private String designation;

    @NotBlank(message = "Price is required")
    @DecimalMin(value = "0.0", message = "Price must be greater than 0")
    private Double price;

    @NotBlank(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be greater than 0")
    private Integer quantity;

    @NotNull(message = "Category is required")
    private Long categoryId;
}
