package com.db.query;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface UpdateStatement<T> {

	void accept(T object, ResultSet rs) throws SQLException;
	
}
