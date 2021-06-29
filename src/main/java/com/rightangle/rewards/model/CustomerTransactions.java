package com.rightangle.rewards.model;

public class CustomerTransactions {
	private Integer customerId;
	private String customerName;
	private Double totalAmount;

	public CustomerTransactions(Integer customerId, String customerName, Double totalAmount) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.totalAmount = totalAmount;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

}
