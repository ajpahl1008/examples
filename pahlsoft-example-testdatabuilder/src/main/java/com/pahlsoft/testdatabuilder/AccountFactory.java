package com.pahlsoft.testdatabuilder;
 
import java.util.UUID;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pahlsoft.testdatabuilder.dao.AccountDao;
 
public class AccountFactory 
{
    public static void main( String[] args ) throws Exception
    {
    	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    	
    	AccountDao accountDao = (AccountDao) context.getBean("accountDao");
    	
    	for (int i = 0; i < 30000000; i++) {
    		String account = UUID.randomUUID().toString();
    		accountDao.createAccount(account);
    	}
    	

    }
}