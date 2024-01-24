package com.unir.anotherStore.productos.persistence;

import com.unir.anotherStore.productos.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
