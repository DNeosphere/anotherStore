package com.unir.anotherStore.products.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.unir.anotherStore.products.data.BookRepository;
import com.unir.anotherStore.products.model.pojo.BookDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.unir.anotherStore.products.model.pojo.Book;
import com.unir.anotherStore.products.model.request.CreateBookRequest;

@Service
@Slf4j
public class ProductsServiceImpl implements ProductsService {

	@Autowired
	private BookRepository repository;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public List<Book> getProducts(String name, String author, String description, Boolean visible) {

		if (StringUtils.hasLength(name) || StringUtils.hasLength(author) || StringUtils.hasLength(description)
				|| visible != null) {
			return repository.search(name, author, description, visible);
		}

		List<Book> products = repository.getProducts();
		return products.isEmpty() ? null : products;
	}

	@Override
	public Book getProduct(String productId) {
		return repository.getById(Long.valueOf(productId));
	}

	@Override
	public Boolean removeProduct(String productId) {

		Book product = repository.getById(Long.valueOf(productId));

		if (product != null) {
			repository.delete(product);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	@Override
	public Book createProduct(CreateBookRequest request) {

		//Otra opcion: Jakarta Validation: https://www.baeldung.com/java-validation
		if (request != null && StringUtils.hasLength(request.getName().trim())
				&& StringUtils.hasLength(request.getDescription().trim())
				&& StringUtils.hasLength(request.getAuthor().trim()) && request.getVisible() != null) {

			Book product = Book.builder().name(request.getName()).description(request.getDescription())
					.author(request.getAuthor()).visible(request.getVisible()).build();

			return repository.save(product);
		} else {
			return null;
		}
	}

	@Override
	public Book updateProduct(String productId, String request) {

		//PATCH se implementa en este caso mediante Merge Patch: https://datatracker.ietf.org/doc/html/rfc7386
		Book product = repository.getById(Long.valueOf(productId));
		if (product != null) {
			try {
				JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(request));
				JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(product)));
				Book patched = objectMapper.treeToValue(target, Book.class);
				repository.save(patched);
				return patched;
			} catch (JsonProcessingException | JsonPatchException e) {
				log.error("Error updating product {}", productId, e);
                return null;
            }
        } else {
			return null;
		}
	}

	@Override
	public Book updateProduct(String productId, BookDto updateRequest) {
		Book product = repository.getById(Long.valueOf(productId));
		if (product != null) {
			product.update(updateRequest);
			repository.save(product);
			return product;
		} else {
			return null;
		}
	}

}
