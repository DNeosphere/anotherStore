package com.unir.anotherStore.products.service;

import com.unir.anotherStore.products.model.pojo.Book;
import com.unir.anotherStore.products.model.pojo.BookDto;
import com.unir.anotherStore.products.model.request.CreateBookRequest;

import java.util.List;

public interface BooksService {
	
	List<Book> getBooks(String name, String author, String description, Long isbn, String genre, String language, String image, Boolean visible);

	Book getBook(String bookId);
	
	Boolean removeBook(String bookId);

	Book createBook(CreateBookRequest request);

	Book updateBook(String bookId, String updateRequest);

	Book updateBook(String bookId, BookDto updateRequest);

}
