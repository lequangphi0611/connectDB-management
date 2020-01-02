package com.db.query.impl;

import java.util.List;
import java.util.Optional;

import com.db.query.QueryExecution;
import com.db.query.QueryExecutorBuilder;
import com.db.query.impl.QueryExecutor.QueryResultType;

@SuppressWarnings("unchecked")
public class QueryExecutionImpl<T> implements QueryExecution<T> {
	
	private final QueryExecutorBuilder queryExecutorBuilder;
	
	public QueryExecutionImpl(QueryExecutorBuilder queryExecutorBuilder) {
		this.queryExecutorBuilder = queryExecutorBuilder;
	}

	@Override
	public List<T> getResultList() {
		this.queryExecutorBuilder.withQueryResultType(QueryResultType.MULTIPLE);
		return (List<T>) this.queryExecutorBuilder
				.build()
				.execute();
	}

	@Override
	public Optional<T> getSingleResult() {
		this.queryExecutorBuilder.withQueryResultType(QueryResultType.SINGLE);
		return (Optional<T>) this.queryExecutorBuilder
				.build()
				.execute();
	}

}
