package com.db.query;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface SQLResulSetMapping<T> {

	T apply(ResultSet rs) throws SQLException;
	
}
