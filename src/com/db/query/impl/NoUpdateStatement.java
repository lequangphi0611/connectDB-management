package com.db.query.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.db.query.UpdateStatement;

public enum NoUpdateStatement implements UpdateStatement<Object> {
	INSTANCE;

	@Override
	public void accept(Object object, ResultSet rs) throws SQLException {
	}

}
