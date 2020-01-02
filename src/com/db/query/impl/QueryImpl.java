package com.db.query.impl;

import java.util.Map;

import com.db.DatabaseConnector;
import com.db.query.Query;

public class QueryImpl implements Query {
	
	private final String sqlQuery;
	
	private final Map<Integer, ?> parameters;
	
	private final DatabaseConnector connector;

	public QueryImpl(DatabaseConnector connector, String sqlQuery, Map<Integer, ?> parameters) {
		super();
		this.sqlQuery = sqlQuery;
		this.parameters = parameters;
		this.connector = connector;
	}

	@Override
	public String getSqlQuery() {
		return sqlQuery;
	}

	@Override
	public Map<Integer, ?> getParameters() {
		return parameters;
	}

	@Override
	public DatabaseConnector getConnector() {
		return connector;
	}

	@Override
	public String toString() {
		return "QueryImpl [sqlQuery=" + sqlQuery + ", parameters=" + parameters + ", connector=" + connector + "]";
	}
	
	

}
