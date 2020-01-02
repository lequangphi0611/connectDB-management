package com.db.query.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.db.query.Executor;
import com.db.query.Query;

public abstract class AbstractExecutor implements Executor {
 
	protected final Query query;
	
	public AbstractExecutor(Query query) {
		this.query = query;
	}
	
	protected void prepareParameters(PreparedStatement stmt) {
		query.getParameters()
			.forEach((k,v) -> {
				try {
					stmt.setObject(k, v);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});
	}

}
