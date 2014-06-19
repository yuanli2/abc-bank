package com.abc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Calendar;
import java.util.Date;

public class Account {

	public static final int CHECKING = 0;
	public static final int SAVINGS = 1;
	public static final int MAXI_SAVINGS = 2;
	
	public static final double DEFAULT_RATE = 0.001;

	private final int accountType;
	public List<Transaction> transactions;

	private int accountExistedDays;
	private Calendar lastWithdrawDate;

	public Account(int accountType) {
		this.accountType = accountType;
		this.transactions = Collections
				.synchronizedList(new ArrayList<Transaction>());
	}

	public synchronized void deposit(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		} else {
			transactions.add(new Transaction(amount));
		}
	}

	public synchronized void withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		} else {
			transactions.add(new Transaction(-amount));
			this.lastWithdrawDate = Calendar.getInstance();
		}
	}

	public double interestEarned(int accountExistedDays) {
		double amount = sumTransactions();
		switch (accountType) {
		case SAVINGS:
			if (amount <= 1000)
				return Helper.calcInterest(amount, DEFAULT_RATE, accountExistedDays);
			else
				return 1 + Helper.calcInterest((amount - 1000), 0.002,
						accountExistedDays);
			// case SUPER_SAVINGS:
			// if (amount <= 4000)
			// return 20;
		case MAXI_SAVINGS:
			if (this.lastWithdrawDate != null
					&& (Helper.calcDayDiff(this.lastWithdrawDate,
							Calendar.getInstance()) < 10)) {
				return Helper.calcInterest(amount, DEFAULT_RATE, accountExistedDays);
			} else {
				return Helper.calcInterest((amount), 0.05, accountExistedDays);
			}
		default:
			return Helper.calcInterest(amount, DEFAULT_RATE, accountExistedDays);
		}
	}

	public double sumTransactions() {
		return checkIfTransactionsExist(true);
	}

	private double checkIfTransactionsExist(boolean checkAll) {
		double amount = 0.0;
		for (Transaction t : transactions)
			amount += t.amount;
		return amount;
	}

	public int getAccountType() {
		return accountType;
	}

	public int getAccountExistedDays() {
		return accountExistedDays;
	}

	public void setAccountExistedDays(int accountExistedDays) {
		this.accountExistedDays = accountExistedDays;
	}

	public Calendar getLastWithdrawDate() {
		return lastWithdrawDate;
	}

	public void setLastWithdrawDate(Calendar lastWithdrawDate) {
		this.lastWithdrawDate = lastWithdrawDate;
	}

}
