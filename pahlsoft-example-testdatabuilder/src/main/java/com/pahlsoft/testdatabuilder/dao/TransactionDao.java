package com.pahlsoft.testdatabuilder.dao;

public interface TransactionDao {
	public int debitAccount(int accountNumber, String amount);
	public int creditAccount(int accountNumber, String amount);
}
