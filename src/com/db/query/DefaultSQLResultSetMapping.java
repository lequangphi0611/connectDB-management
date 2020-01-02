package com.db.query;

import java.sql.ResultSet;
import java.sql.SQLException;

public enum DefaultSQLResultSetMapping implements SQLResulSetMapping<Object[]> {
	INSTANCE
	;

	@Override
	public Object[] apply(ResultSet rs) throws SQLException {
		int cols = rs.getMetaData().getColumnCount();
		Object[] result = new Object[cols];
		for(int i = 0; i < cols ; i++) {
			result[i] = rs.getObject(i);
		}
		return result;
	}

}
