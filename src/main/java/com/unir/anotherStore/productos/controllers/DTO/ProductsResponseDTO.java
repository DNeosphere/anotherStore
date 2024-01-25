package com.unir.anotherStore.productos.controllers.DTO;

import com.unir.anotherStore.productos.entities.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter@Getter
public class ProductsResponseDTO {
    private int productCount;
    private List<Product> products;

    public ProductsResponseDTO(int productCount, List<Product> products) {
        this.productCount = productCount;
        this.products = products;
    }

}
