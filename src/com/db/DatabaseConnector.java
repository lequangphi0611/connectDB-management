package com.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.db.query.QueryBuilder;

public interface DatabaseConnector {

	void register() throws SQLException;
	
	Connection createConnection() throws SQLException;
	
	QueryBuilder createQueryBuilder(String sql);
	
}
