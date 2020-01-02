package com.db.query.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.db.query.DefaultSQLResultSetMapping;
import com.db.query.Query;
import com.db.query.SQLResulSetMapping;

public class QueryExecutor extends AbstractExecutor {
	
	public static enum QueryResultType {
		MULTIPLE, SINGLE;
	}
	
	private SQLResulSetMapping<?> mapper = DefaultSQLResultSetMapping.INSTANCE;
	
	private final QueryResultType resultType;

	public QueryExecutor(Query query, QueryResultType resultType, SQLResulSetMapping<?> mapper) {
		super(query);
		this.resultType = resultType;
		if(Objects.nonNull(mapper)) {
			this.mapper = mapper;
		}
	}

	@Override
	public Object execute() {
		try(
			Connection conn = query.getConnector().createConnection();
			PreparedStatement stmt = conn.prepareStatement(query.getSqlQuery());
		) {
			prepareParameters(stmt);
			try(ResultSet rs = stmt.executeQuery()) {
				return createResult(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getEmptyResult();
	}
	
	private Object createResult(ResultSet rs) throws SQLException {
		return isMultipleResultType()
				? createResultList(rs)
				: createSingleResult(rs);
	}
	
	private Object createResultList(ResultSet rs) throws SQLException {
		List<Object> list = new ArrayList<>();
		while(rs.next()) {
			list.add(mapper.apply(rs));
		}
		return list;
	}
	
	private Object createSingleResult(ResultSet rs) throws SQLException {
		return Optional.of(mapper.apply(rs));
	}
	
	private Object getEmptyResult() {
		return isMultipleResultType()
				? Collections.emptyList()
				: Optional.empty();
	}
	
	public boolean isMultipleResultType() {
		return resultType == QueryResultType.MULTIPLE;
	}

}
