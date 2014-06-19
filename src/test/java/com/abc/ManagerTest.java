package com.abc;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ManagerTest {
	private static final double DOUBLE_DELTA = 1e-15;
	
    @Test
    public void testReportOfCustomers(){
    	Bank bank = new Bank();
    	Customer adam = new Customer("Adam").openAccount(new Account(Account.CHECKING));
        Customer oscar = new Customer("Oscar").openAccount(new Account(Account.SAVINGS));
        oscar.openAccount(new Account(Account.CHECKING));
        bank.addCustomer(adam);
        bank.addCustomer(oscar);
        Manager manager = new Manager(bank);

        assertEquals("Customer Summary\n" +
                " - Adam (1 account)\n" +
                " - Oscar (2 accounts)", manager.reportOfCustomers());
    }
    
    @Test
    public void testReportOfTotalInterestPaid(){
    	Bank bank = new Bank();
    	Account account1 = new Account(Account.CHECKING);
    	Account account2 = new Account(Account.SAVINGS);
    	Account account3 = new Account(Account.MAXI_SAVINGS);
    	
    	account1.deposit(100);
    	account1.setAccountExistedDays(365);
    	account2.deposit(100);
    	account2.setAccountExistedDays(365);
    	account3.deposit(100);
    	account3.setAccountExistedDays(365);
    	
    	Customer adam = new Customer("Adam").openAccount(account1);
        Customer oscar = new Customer("Oscar").openAccount(account2);
        oscar.openAccount(account3);
        bank.addCustomer(adam);
        bank.addCustomer(oscar);
        Manager manager = new Manager(bank);

        double total = Helper.calcInterest(100, 0.001, 365) + Helper.calcInterest(100, 0.001, 365) + Helper.calcInterest(100, 0.05, 365); 

        assertEquals(total, manager.reportOfTotalInterestPaid(), DOUBLE_DELTA);
    }
}
