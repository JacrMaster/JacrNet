package com.jacrnet.presentation.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private BigDecimal installationPrice;
    private String description;
}
