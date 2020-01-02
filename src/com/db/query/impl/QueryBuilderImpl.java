package com.db.query.impl;

import java.util.HashMap;
import java.util.Map;

import com.db.DatabaseConnector;
import com.db.query.Query;
import com.db.query.QueryBuilder;
import com.db.query.QueryExecution;
import com.db.query.QueryExecutorBuilder;
import com.db.query.SQLResulSetMapping;
import com.db.query.UpdateExecutorBuilder;

public class QueryBuilderImpl implements QueryBuilder {
	
	private final String sqlQuery;
	
	private Map<Integer, Object> parameters = null;
	
	private int paramIndex = 0;
	
	private final DatabaseConnector connector;
	
	
	public QueryBuilderImpl(String sqlQuery, DatabaseConnector connector) {
		super();
		this.sqlQuery = sqlQuery;
		this.connector = connector;
	}

	@Override
	public QueryBuilder setParameters(Object param) {
		if(parameters == null) {
			this.parameters = new HashMap<Integer, Object>();
		}
		this.parameters.put(++paramIndex, param);
		return null;
	}
	
	@Override
	public Query build() {
		return new QueryImpl(connector, sqlQuery, parameters);
	}

	@Override
	public <T> QueryExecution<T> executeQuery(SQLResulSetMapping<T> mapper) {
		QueryExecutorBuilder builder = new QueryExecutorBuilderImpl(build())
				.withMapper(mapper);
		
		return new QueryExecutionImpl<T>(builder);
	}
	
	@Override
	public QueryExecution<Object[]> executeQuery() {
		QueryExecutorBuilder builder = new QueryExecutorBuilderImpl(build());
		return new QueryExecutionImpl<Object[]>(builder);
	}

	@Override
	public UpdateExecutorBuilder executeUpdate() {
		UpdateExecutorBuilder builder = new UpdateExecutorBuilderImpl(build());
		return builder;
	}


}
