package com.pahlsoft.testdatabuilder.dao.impl;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.pahlsoft.testdatabuilder.dao.AccountDao;
import com.pahlsoft.testdatabuilder.dao.TransactionDao;

public class TransactionDaoImpl implements TransactionDao {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);	
	}
	
	@Override
	public int creditAccount(int accountNumber, String amount) {
		
		String server_sql = "INSERT INTO accounts.DDA_TRANSACTIONS VALUES (default, ?, ?, 0)";
		System.out.println("Account Credited: " + accountNumber + " Amount: " + amount);
		return getJdbcTemplate().update(server_sql, new Object[] {
				accountNumber, Double.parseDouble(amount)});
	}
	
	@Override
	public int debitAccount(int accountNumber, String amount) {
		String server_sql = "INSERT INTO accounts.DDA_TRANSACTIONS VALUES (default, ?, 0, ?)";
		System.out.println("Account Debited: " + accountNumber + " Amount: " + amount);
		return getJdbcTemplate().update(server_sql, new Object[] {
				accountNumber, Double.parseDouble(amount)});
	}
	
}
