package com.db.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

import com.db.DatabaseConnector;
import com.db.DatabaseProperties;
import com.db.query.QueryBuilder;
import com.db.query.impl.QueryBuilderImpl;

public class DatabaseConnectorImpl implements DatabaseConnector {

	private boolean register = false;

	private final DatabaseProperties dbProperties;

	public DatabaseConnectorImpl(DatabaseProperties dbProperties) {
		Objects.requireNonNull(dbProperties, "Database Properties");
		this.dbProperties = dbProperties;
	}

	@Override
	public void register() throws SQLException {
		if(Objects.nonNull(dbProperties.getDriverClassName())) 
			registerByDriverClassName();
		
		else if(Objects.nonNull(dbProperties.getDriver()))
			registerByDriver();
		
		else 
			throw new NullPointerException("SQL Driver");
	}

	@Override
	public Connection createConnection() throws SQLException {
		if (!isRegister()) {
			register();
		}

		return DriverManager.getConnection(
				dbProperties.getUrl(), 
				dbProperties.getUser(),
				dbProperties.getPassword()
		);
	}
	
	@Override
	public QueryBuilder createQueryBuilder(String sql) {
		return new QueryBuilderImpl(sql, this);
	}
	
	private void registerByDriverClassName() {
		
		try {
			Class.forName(dbProperties.getDriverClassName());
			this.register = true;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void registerByDriver() throws SQLException {
		DriverManager.registerDriver(dbProperties.getDriver());
		this.register = true;
	}
	
	public boolean isRegister() {
		return register;
	}

	@Override
	public String toString() {
		return "DatabaseConnectorImpl [register=" + register + ", dbProperties=" + dbProperties + "]";
	}

}
