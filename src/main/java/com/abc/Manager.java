package com.abc;

public class Manager {
	private Bank bank;
	
	public Manager(Bank bank) {
		this.bank = bank;
	}
	
	public String reportOfCustomers() {
		return bank.customerSummary();
	}
	
	public double reportOfTotalInterestPaid() {
		return bank.totalInterestPaid();
	}
}
