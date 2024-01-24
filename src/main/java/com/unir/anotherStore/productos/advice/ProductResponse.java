package com.unir.anotherStore.productos.advice;


import com.unir.anotherStore.productos.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private List<Product> product;
    private String message;

    // Constructor para casos de error
    public static ProductResponse error(String errorMessage) {
        return new ProductResponse(null, errorMessage);
    }

    // Constructor para respuestas exitosas
    public static ProductResponse success(List<Product> product, String successMessage) {
        return new ProductResponse(product, successMessage);
    }

}
