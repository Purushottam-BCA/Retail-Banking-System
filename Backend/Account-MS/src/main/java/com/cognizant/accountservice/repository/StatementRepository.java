package com.cognizant.accountservice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cognizant.accountservice.model.Statement;

@Repository
public interface StatementRepository extends JpaRepository<Statement, Long> {
	@Query(nativeQuery = true, value = "SELECT * from STATEMENT s WHERE (s.source_Id = :accountId or s.target_Id = :accountId) and (date between :startDate and :endDate) order by date desc ")
	List<Statement> findStatementByAccountId(@Param(value = "accountId") long accountId,Date startDate,Date endDate);
}
