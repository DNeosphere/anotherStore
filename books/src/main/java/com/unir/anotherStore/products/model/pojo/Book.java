package com.unir.anotherStore.products.model.pojo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", unique = true)
	private String name;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "description")
	private String description;

	@Column(name = "isbn")
	private Long isbn;

	@Column(name = "genre")
	private String genre;

	@Column(name = "language")
	private String language;

	@Column(name = "image")
	private String image;
	
	@Column(name = "visible")
	private Boolean visible;




	public void update(BookDto bookDto) {
		this.name = bookDto.getName();
		this.author = bookDto.getAuthor();
		this.description = bookDto.getDescription();
		this.isbn = bookDto.getIsbn();
		this.genre = bookDto.getGenre();
		this.language = bookDto.getLanguage();
		this.visible = bookDto.getVisible();
	}

}
