package com.rightangle.rewards.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rightangle.rewards.model.Transactions;

public interface TransactionsRepository extends JpaRepository<Transactions, Integer>{

}
