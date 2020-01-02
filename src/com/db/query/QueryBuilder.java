package com.db.query;

public interface QueryBuilder {
	
	QueryBuilder setParameters(Object param);
	
	Query build();
	
	<T> QueryExecution<T> executeQuery(SQLResulSetMapping<T> mapper);
	
	QueryExecution<Object[]> executeQuery();
	
	UpdateExecutorBuilder executeUpdate();

}
