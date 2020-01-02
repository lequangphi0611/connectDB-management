package com.db.query.impl;

import java.util.Objects;

import com.db.query.DefaultSQLResultSetMapping;
import com.db.query.Executor;
import com.db.query.Query;
import com.db.query.QueryExecutorBuilder;
import com.db.query.SQLResulSetMapping;
import com.db.query.impl.QueryExecutor.QueryResultType;

public class QueryExecutorBuilderImpl implements QueryExecutorBuilder {
	
	private final Query query;
	
	private SQLResulSetMapping<?> mapper = DefaultSQLResultSetMapping.INSTANCE;
	
	private QueryResultType resultType;
	
	public QueryExecutorBuilderImpl(Query query) {
		this.query = query;
	}
	
	@Override
	public QueryExecutorBuilder withQueryResultType(QueryResultType resultType) {
		this.resultType = resultType;
		return this;
	}

	@Override
	public <T> QueryExecutorBuilder withMapper(SQLResulSetMapping<T> mapper) {
		if(Objects.nonNull(mapper)) {
			this.mapper = mapper;
		}
		return this;
	}

	@Override
	public Executor build() {
		return new QueryExecutor(query, resultType, mapper);
	}

}
