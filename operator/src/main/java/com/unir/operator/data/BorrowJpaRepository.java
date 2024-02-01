package com.unir.operator.data;

import com.unir.operator.model.pojo.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.List;

interface BorrowJpaRepository extends JpaRepository<Borrow, Long>, JpaSpecificationExecutor<Borrow> {

	List<Borrow> findByDate(LocalDateTime date);
}
