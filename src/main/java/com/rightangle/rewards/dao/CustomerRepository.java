package com.rightangle.rewards.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rightangle.rewards.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	@Query(value = "select c.customer_id as customerId,c.customer_name as customerName, sum(t.transaction_amount) as totalAmount from customer c, transactions t\r\n"
			+ "where c.customer_id=? and c.customer_id=t.customer_id and t.transaction_date >= now()-interval 3 month group by c.customer_id,c.customer_name", nativeQuery = true)
	List<Object[]> transactionsOfLastThreeMonths(Integer customerId);
	
	
	@Query(value = "select c.customer_name as CustomerName, month(t.transaction_date) as MontlyTransactions, sum(t.transaction_amount) as totalAmount from customer c, transactions t\r\n"
			+ "where c.customer_id=? and c.customer_id=t.customer_id \r\n"
			+ "group by month(t.transaction_date)\r\n"
			+ "order by month(t.transaction_date)", nativeQuery = true)
	List<Object[]> monthlyTransactions(Integer customerId);

}
