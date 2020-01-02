package com.db.query;

import java.util.Map;

import com.db.DatabaseConnector;

public interface Query {

	String getSqlQuery();
	
	Map<Integer, ?> getParameters();
	
	DatabaseConnector getConnector();
}
