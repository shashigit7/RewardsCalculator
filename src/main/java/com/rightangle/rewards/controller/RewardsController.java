package com.rightangle.rewards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rightangle.rewards.dao.CustomerRepository;
import com.rightangle.rewards.dao.TransactionsRepository;
import com.rightangle.rewards.model.Customer;
import com.rightangle.rewards.model.CustomerTransactions;
import com.rightangle.rewards.model.Transactions;

@RestController
public class RewardsController {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private TransactionsRepository transactionsRepository;

	@PostMapping("/saveCustomerDetails")
	public String saveCustomers(@RequestBody Customer customer) {
		customerRepository.save(customer);
		return "Customer Information saved successfully.";
	}

	@PostMapping("/saveTransactions")
	public String saveTransactions(@RequestBody Transactions transactions) {
		transactionsRepository.save(transactions);
		return "Transactions saved successfully.";
	}

	@GetMapping("/getAllCustomers")
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	@GetMapping("/getThreeMonthsTransactions/{customerId}")
	public String getThreeMonthsTranactions(@PathVariable String customerId) {
		List<Object[]> transactions = customerRepository.transactionsOfLastThreeMonths(Integer.parseInt(customerId));
		int rewardPointsObtained = 0;
		int dollarsSpent = 0;
		Object[] transaction = transactions.get(0);
		CustomerTransactions custTrans = new CustomerTransactions((Integer) transaction[0], (String) transaction[1],
				(Double) transaction[2]);
		dollarsSpent = (int) Math.round(custTrans.getTotalAmount());

		rewardPointsObtained = calculatePoints(dollarsSpent);

		return "Total dollars spent by customer for the past 3 months: " + dollarsSpent
				+ "\r\nPoints Earned by customer in last 3 months: " + rewardPointsObtained;
	}

	private int calculatePoints(int dollarsSpent) {
		int rewardPointsObtained;
		if (dollarsSpent >= 100) {
			rewardPointsObtained = ((dollarsSpent - 100) * 2) + (1 * 50);

		} else if (dollarsSpent > 50 && dollarsSpent < 100) {
			rewardPointsObtained = (dollarsSpent - 50) * 1;
		} else {
			rewardPointsObtained = 0;
		}
		return rewardPointsObtained;
	}

	@GetMapping("/getMonthlyTransactions/{customerId}")
	public String getMonthlyTranactions(@PathVariable String customerId) {
		List<Object[]> transactions = customerRepository.monthlyTransactions(Integer.parseInt(customerId));
		StringBuilder completeString = new StringBuilder();
		StringBuilder monthlyTransactions = new StringBuilder();
		completeString.append("Monthly Transactions of ");
		String customerName="";
		int totalDollarsSpent=0;
		for (Object[] monthlyTransaction : transactions) {
			customerName=(String)monthlyTransaction[0];
			totalDollarsSpent = totalDollarsSpent+(int) Math.round((Double)monthlyTransaction[2]);
			monthlyTransactions
			.append(monthlyTransaction[1])
			.append("th Month").append("        ")
			.append(monthlyTransaction[2])
			.append("\r\n");
		}
		return completeString.append(customerName)
				.append("\r\n")
				.append("Month        ")
				.append("   Dollars Spent")
				.append("\r\n")
				.append(monthlyTransactions.toString())
				.append("Total dollars spent: "+totalDollarsSpent)
				.append("\r\n")
				.append("Total points accumulated: "+calculatePoints(totalDollarsSpent))
				.toString();
	}

}
