package com.unir.anotherStore.products.data;

import com.unir.anotherStore.products.model.pojo.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

interface BookJpaRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

	List<Book> findByName(String name);

	List<Book> findByAuthor(String author);

	List<Book> findByVisible(Boolean visible);

	List<Book> findByNameAndAuthor(String name, String author);

}
