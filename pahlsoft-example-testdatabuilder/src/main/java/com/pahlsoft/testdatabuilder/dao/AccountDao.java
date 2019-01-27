package com.pahlsoft.testdatabuilder.dao;

import java.math.BigDecimal;

public interface AccountDao {
	public BigDecimal getAccountBalance(String accountNumber);
	public int debitAccount(String accountNumber, BigDecimal amount);
	public int creditAccount(String accountNumber, BigDecimal amount);
	public int createAccount(String accountNumber );
}
