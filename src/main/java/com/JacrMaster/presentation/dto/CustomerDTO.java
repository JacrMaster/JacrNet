package com.JacrMaster.presentation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerDTO {
    private Long id;
    @NotBlank(message = "El nombre es obligatorio")
    private String name;
    @NotBlank(message = "El número de teléfono es obligatorio")
    @Pattern(regexp = "\\d{10}", message = "El número de teléfono debe tener 10 dígitos")
    private String phone;
    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico no es válido")
    private String email;
}
