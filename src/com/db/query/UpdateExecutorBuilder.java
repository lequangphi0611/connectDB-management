package com.db.query;

public interface UpdateExecutorBuilder {

	<T> Executor insert(T argument, UpdateStatement<T> updateStatement);
	
	<T> Executor update(T argument, UpdateStatement<T> updateStatement);
	
	Executor delete();
	
}
