package com.pahlsoft.testdatabuilder.dao.impl;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.pahlsoft.testdatabuilder.dao.AccountDao;

public class AccountDaoImpl implements AccountDao {
	
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
	public BigDecimal getAccountBalance(String accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int debitAccount(String accountNumber, BigDecimal amount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int creditAccount(String accountNumber, BigDecimal amount) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int createAccount(String accountNumber) {
		String server_sql = "INSERT INTO accounts.DDA_ACCOUNTS VALUES (default, ?, default)";
		System.out.println("Account Created: " + accountNumber);
		return getJdbcTemplate().update(server_sql, new Object[] {
				accountNumber});
	}


}
