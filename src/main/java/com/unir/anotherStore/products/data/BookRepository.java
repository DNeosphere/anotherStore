package com.unir.anotherStore.products.data;

import com.unir.anotherStore.products.data.utils.SearchCriteria;
import com.unir.anotherStore.products.data.utils.SearchOperation;
import com.unir.anotherStore.products.data.utils.SearchStatement;
import com.unir.anotherStore.products.model.pojo.Book;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository {

    private final BookJpaRepository repository;

    public List<Book> getBooks() {
        return repository.findAll();
    }

    public Book getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Book save(Book product) {
        return repository.save(product);
    }

    public void delete(Book product) {
        repository.delete(product);
    }

    public List<Book> search(String name, String country, String description, Boolean visible) {
        SearchCriteria<Book> spec = new SearchCriteria<>();
        if (StringUtils.isNotBlank(name)) {
            spec.add(new SearchStatement("name", name, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(country)) {
            spec.add(new SearchStatement("country", country, SearchOperation.EQUAL));
        }

        if (StringUtils.isNotBlank(description)) {
            spec.add(new SearchStatement("description", description, SearchOperation.MATCH));
        }

        if (visible != null) {
            spec.add(new SearchStatement("visible", visible, SearchOperation.EQUAL));
        }
        return repository.findAll(spec);
    }

}
