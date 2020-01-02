package com.db.query.impl;

import com.db.query.Executor;
import com.db.query.Query;
import com.db.query.UpdateExecutorBuilder;
import com.db.query.UpdateStatement;
import com.db.query.impl.UpdateExecutor.UpdateQueryType;

@SuppressWarnings("unchecked")
public class UpdateExecutorBuilderImpl implements UpdateExecutorBuilder {
	
	private final Query query;
	
	private UpdateStatement<?> updateStatement;

	private Object argument;

	private UpdateQueryType updateType;

	public UpdateExecutorBuilderImpl(Query query) {
		super();
		this.query = query;
	}
	
	
	public UpdateExecutorBuilder withArgument(Object argument) {
		this.argument = argument;
		return this;
	}

	
	public UpdateExecutorBuilder withUpdateStatement(UpdateStatement<?> updateStatement) {
		this.updateStatement = updateStatement;
		return this;
	}

	public UpdateExecutorBuilder withUpdateType(UpdateQueryType updateType) {
		this.updateType = updateType;
		return this;
	}
	
	private Executor build() {
		return new UpdateExecutor(query, (Object)argument, updateType, (UpdateStatement<Object>)updateStatement);
	}

	@Override
	public <T> Executor insert(T argument, UpdateStatement<T> updateStatement) {
		withUpdateType(UpdateQueryType.INSERT);
		withArgument(argument);
		withUpdateStatement(updateStatement);
		return build();
	}

	@Override
	public <T> Executor update(T argument, UpdateStatement<T> updateStatement) {
		withUpdateType(UpdateQueryType.UPDATE);
		withArgument(argument);
		withUpdateStatement(updateStatement);
		return build();
	}

	@Override
	public Executor delete() {
		withUpdateType(UpdateQueryType.DELETE);
		return build();
	}

}
