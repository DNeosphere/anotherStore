package com.unir.anotherStore.productos.controllers;


import com.unir.anotherStore.productos.advice.ProductResponse;
import com.unir.anotherStore.productos.controllers.DTO.ProductDTO;
import com.unir.anotherStore.productos.controllers.DTO.ProductsResponseDTO;
import com.unir.anotherStore.productos.entities.Product;
import com.unir.anotherStore.productos.services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;


    @GetMapping("/all")
    public ResponseEntity<ProductsResponseDTO> getAllProducts() {
        List<Product> products = this.productService.getAllProducts();
        int productCount = products.size();

        ProductsResponseDTO responseDTO = new ProductsResponseDTO(productCount, products);

        return ResponseEntity.ok(responseDTO);
    }


    @PostMapping("/create")
    public ResponseEntity<Product> createEmployee(@RequestBody  @Valid  ProductDTO productDTO) {
        return new ResponseEntity<>(this.productService.createProduct(productDTO), HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody @Valid ProductDTO productDTO, @PathVariable Long id) {
        try {
            //Valdiamos que el producto exista
            if (!productService.productExists(id)) {
                return new ResponseEntity<>(ProductResponse.error("Producto no encontrado"), HttpStatus.NOT_FOUND);
            }

            // Actualizamos el producto y obtenemos el producto actualizado
            Product updatedProduct = this.productService.updateProduct(productDTO, id);

            // Devolvemos una lista que contiene solo el producto actualizado
            List<Product> updatedProductList = Collections.singletonList(updatedProduct);

            return new ResponseEntity<>(ProductResponse.success(updatedProductList, "Producto actualizado correctamente"), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(ProductResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(this.productService.getProductById(id));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductResponse> deleteProduct(@PathVariable Long id) {
        try{
            if(!productService.productExists(id)){
                return new ResponseEntity<>(ProductResponse.error("Producto no encontrado"), HttpStatus.NOT_FOUND);
            }
            //Eliminamos el producto
            Product deletedProduct = this.productService.deleteProduct(id);

            // Modificamos el mensaje para incluir la información del producto actualizado
            String successMessage = String.format("Producto con ID %d Eliminado  correctamente", id);

            // Devolvemos una lista que contiene solo el producto eliminado
            List<Product> deletedProductList = Collections.singletonList(deletedProduct);

            //Devolvemos el producto eliminado
            return new ResponseEntity<>(ProductResponse.success(deletedProductList, successMessage), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(ProductResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}