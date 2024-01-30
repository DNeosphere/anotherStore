package com.unir.anotherStore.products.service;

import java.util.List;

import com.unir.anotherStore.products.model.pojo.Book;
import com.unir.anotherStore.products.model.pojo.BookDto;
import com.unir.anotherStore.products.model.request.CreateBookRequest;

public interface BooksService {
	
	List<Book> getBooks(String name, String country, String description, Boolean visible);

	Book getBook(String productId);
	
	Boolean removeProduct(String productId);

	Book createProduct(CreateBookRequest request);

	Book updateProduct(String productId, String updateRequest);

	Book updateProduct(String productId, BookDto updateRequest);

}
