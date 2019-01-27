package com.pahlsoft.testdatabuilder;
 
import java.util.Random;
import java.util.UUID;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pahlsoft.testdatabuilder.dao.AccountDao;
import com.pahlsoft.testdatabuilder.dao.TransactionDao;
import com.pahlsoft.testdatabuilder.util.RandomDollarAmountUtil;
 
public class TransactionFactory 
{
    public static void main( String[] args ) throws Exception
    {
    	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    	
    	TransactionDao transactionDao = (TransactionDao) context.getBean("transactionDao");
    	
    	int accountNumber = 0;
    	boolean debit;
    	RandomDollarAmountUtil rd = new RandomDollarAmountUtil();
		for (int i = 0; i < 49998 ; i++) { 
    		// Randomly grab one of the accounts
			accountNumber = new Random().nextInt(200000);
		    // Decide Debit or Credit
			debit = new Random().nextBoolean();
			if (debit) {
				transactionDao.debitAccount(accountNumber,  rd.getRandomDollarAmount());
			} else {
				transactionDao.creditAccount(accountNumber,  rd.getRandomDollarAmount());
			}
			
    		
    	}

    }
 
}
    