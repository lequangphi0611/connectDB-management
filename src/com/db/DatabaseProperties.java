package com.db;

import java.sql.Driver;

public interface DatabaseProperties {

	String getUrl();
	
	String getUser();
	
	String getPassword();
	
	String getDriverClassName();
	
	Driver getDriver();
	
}
