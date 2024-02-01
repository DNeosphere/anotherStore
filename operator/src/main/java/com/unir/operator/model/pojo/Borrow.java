package com.unir.operator.model.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@Entity
@Table(name = "borrows")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Borrow {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "bookId")
	private Long bookId;
	
	@Column(name = "personId")
	private Long personId;
	
	@Column(name = "date")
	@JsonDeserialize( using = LocalDateTimeDeserializer.class )
	@JsonSerialize( using = LocalDateTimeSerializer.class )
	private LocalDateTime date;
	
	@Column(name = "days")
	private Integer days;
    
	@Nullable
	@JsonDeserialize( using = LocalDateTimeDeserializer.class )
	@JsonSerialize( using = LocalDateTimeSerializer.class )
	@Column(name = "returnDate", nullable=true,columnDefinition="TIMESTAMP")
	private LocalDateTime returnDate;

	public void update(BorrowDto borrowDto) {
		this.bookId = borrowDto.getBookId();
		this.personId = borrowDto.getPersonId();
		this.date = borrowDto.getDate();
		this.days = borrowDto.getDays();
		this.returnDate = borrowDto.getReturnDate();
	}

}
