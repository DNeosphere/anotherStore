package com.unir.anotherStore.productos.services;


import com.unir.anotherStore.productos.controllers.DTO.ProductDTO;
import com.unir.anotherStore.productos.entities.Product;
import com.unir.anotherStore.productos.persistence.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    //Traer todos los productos
    public List<Product> getAllProducts() {
        return (List<Product>) this.productRepository.findAll();
    }


    //Crear producto
    public Product createProduct(ProductDTO productDTO) {
        Product product = Product.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .stock(productDTO.getStock())
                .disponible(productDTO.isDisponible())
                .build();

        return this.productRepository.save(product);
    }


    //Actualizar un producto
    public Product updateProduct(ProductDTO productDTO, Long id) {
        if(!productRepository.existsById(id)) {
            throw new RuntimeException("Employee not found");
        }

        Product product = Product.builder()
                .id(id)
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .stock(productDTO.getStock())
                .disponible(productDTO.isDisponible())
                .build();
        return this.productRepository.save(product);
    }


    //Traer un producto por id
    public Product getProductById(Long id) {
        return this.productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }


    //Eliminar un producto
    public Product deleteProduct(Long id) {
        Product product = this.getProductById(id);
        this.productRepository.delete(product);
        return product;
    }


    //Validar si un producto existe
    public boolean productExists(Long id) {
        return this.productRepository.existsById(id);
    }

}
