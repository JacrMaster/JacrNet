package com.jacrnet.presentation.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanDTO {
    private Long id;
    @NotBlank(message = "Ingrese el nombre")
    private String name;
    @NotNull(message = "El precio no puede ser nulo")
    private BigDecimal price;
    @NotNull(message = "El precio no puede ser nulo")
    private BigDecimal installationPrice;
    private String description;
}
