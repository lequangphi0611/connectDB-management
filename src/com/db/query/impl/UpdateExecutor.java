package com.db.query.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import com.db.query.Query;
import com.db.query.UpdateStatement;

public class UpdateExecutor extends AbstractExecutor {

	public static enum UpdateQueryType {
		INSERT, UPDATE, DELETE
	}

	private UpdateStatement<Object> updateStatement = NoUpdateStatement.INSTANCE;

	private final Object argument;

	private final UpdateQueryType updateType;

	public UpdateExecutor(Query query, Object argument, UpdateQueryType updateType, UpdateStatement<Object> updateStatement) {
		super(query);
		
		this.argument = argument;
		this.updateType = updateType;
		if(Objects.nonNull(updateStatement)) {
			this.updateStatement = updateStatement;
		}
	}

	@Override
	public Object execute() {
		try (Connection conn = query.getConnector().createConnection();
				PreparedStatement prepared = conn.prepareStatement(query.getSqlQuery(), ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);) {
			prepareParameters(prepared);
			try (ResultSet rs = prepared.executeQuery()) {
				if (updateType == UpdateQueryType.DELETE) {
					return executeDelete(rs);
				}

				return executeUpdate(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private Object executeUpdate(ResultSet rs) throws SQLException {
		boolean isInsertType = updateType == UpdateQueryType.INSERT;
		if(isInsertType) 
			rs.moveToInsertRow();
		 else 
			rs.first();

		updateStatement.accept(argument, rs);
		if(isInsertType) 
			rs.insertRow();
		else 
			rs.updateRow();
			
		return argument;
	}

	private Boolean executeDelete(ResultSet rs) throws SQLException {
		rs.first();
		rs.deleteRow();
		return true;
	}

	@Override
	public String toString() {
		return "UpdateExecutor [updateStatement=" + updateStatement + ", argument=" + argument + ", updateType="
				+ updateType + ", query=" + query + "]";
	}

}
