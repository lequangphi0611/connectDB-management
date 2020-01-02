package com.db.query;

import com.db.query.impl.QueryExecutor.QueryResultType;

public interface QueryExecutorBuilder {
	
	QueryExecutorBuilder withQueryResultType(QueryResultType resultType);
	
	<T> QueryExecutorBuilder withMapper(SQLResulSetMapping<T> mapper);
	
	Executor build();
	
}
