package org.example.catalyst.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RoleDTO {
    @NotBlank(message = "Name is required")
    private String name;
}
