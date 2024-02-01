package com.unir.operator.service;

import com.unir.operator.model.pojo.Borrow;
import com.unir.operator.model.pojo.BorrowDto;
import com.unir.operator.model.request.CreateBorrowRequest;

import java.util.List;

public interface BorrowsService {

	Borrow turnIn(Long borrowId);

	List<Borrow> findBorrowsByPerson(Long personId);

	List<Borrow> findBorrowsPenaltiesByPerson(Long personId);
	
	Borrow getBorrow(Long borrowId);
	
	Boolean removeBorrow(Long borrowId);

	Borrow createBorrow(CreateBorrowRequest request);

	Borrow updateBorrow(Long borrowId, BorrowDto updateRequest);

}