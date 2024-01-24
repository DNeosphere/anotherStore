package com.unir.anotherStore.productos.controllers.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;
    @NotBlank(message = "La descripción no puede estar vacía")
    private String description;
    @DecimalMin(value = "0.01", inclusive = true, message = "El precio debe ser igual o mayor a 0.01")
    private Integer price;
    @Min(value = 1, message = "El stock no puede ser menor o igual a 0")
    private Integer stock;
    @NotNull(message = "El campo 'activo' no puede ser nulo")
    @AssertTrue(message = "El campo 'disponible' debe ser true o false")
    private boolean disponible;
}
