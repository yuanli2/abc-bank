package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(Account.CHECKING));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.CHECKING);
        checkingAccount.setAccountExistedDays(365);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);
        assertEquals(Helper.calcInterest(100, 0.001, 365), bank.totalInterestPaid(), DOUBLE_DELTA); 
    }

    @Test
    public void savings_account() {
        Bank bank = new Bank();
        Account savingAccount = new Account(Account.SAVINGS);
        savingAccount.setAccountExistedDays(365);
        Customer bill = new Customer("Bill").openAccount(savingAccount);
        bank.addCustomer(bill);

        savingAccount.deposit(1500.0);
        assertEquals((1 + Helper.calcInterest(1500-1000, 0.002, 365)), bank.totalInterestPaid(), DOUBLE_DELTA);
    
        savingAccount.withdraw(1000.0);
        assertEquals(Helper.calcInterest(500, 0.001, 365), bank.totalInterestPaid(), DOUBLE_DELTA);       
    }

    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        Account maxAccount = new Account(Account.MAXI_SAVINGS);
        maxAccount.setAccountExistedDays(365);
        Customer bill = new Customer("Bill").openAccount(maxAccount);
        bank.addCustomer(bill);

        maxAccount.deposit(200.0);
        assertEquals(Helper.calcInterest(200, 0.05, 365), bank.totalInterestPaid(), DOUBLE_DELTA);
        
        maxAccount.withdraw(100.0);
        assertEquals(Helper.calcInterest(100, 0.001, 365), bank.totalInterestPaid(), DOUBLE_DELTA);
    }

}
