package com.unir.anotherStore.productos.controllers;


import com.unir.anotherStore.productos.advice.ProductResponse;
import com.unir.anotherStore.productos.controllers.DTO.ProductDTO;
import com.unir.anotherStore.productos.entities.Product;
import com.unir.anotherStore.productos.services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;


    @GetMapping("/all")
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        return ResponseEntity.ok(this.productService.getAllProducts());
    }


    @PostMapping("/create")
    public ResponseEntity<Product> createEmployee(@RequestBody  @Valid  ProductDTO productDTO) {
        return new ResponseEntity<>(this.productService.createProduct(productDTO), HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponse> updateEmployee(@RequestBody @Valid ProductDTO productDTO, @PathVariable Long id) {
        try{
            if(!productService.productExists(id)){
                return new ResponseEntity<>(ProductResponse.error("Producto no encontrado"), HttpStatus.NOT_FOUND);
            }
            //Actualizamos el producto
            this.productService.updateProduct(productDTO, id);

            //Devolvemos el producto actualizado
            return new ResponseEntity<>(ProductResponse.success(this.productService.getAllProducts(), "Producto actualizado correctamente"), HttpStatus.OK);

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
            this.productService.deleteProduct(id);

            //Devolvemos el producto eliminado
            return new ResponseEntity<>(ProductResponse.success(this.productService.getAllProducts(), "Producto eliminado correctamente"), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(ProductResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}