package com.db.impl;

import java.sql.Driver;

import com.db.DatabaseProperties;

public class DatabasePropertiesImpl implements DatabaseProperties {
	
	private final String url;
	
	private final String user;
	
	private final String password;
	
	private final Driver driver;
	
	private final String driverClassName;

	public DatabasePropertiesImpl(String url, String user, String password, Driver driver) {
		this.url = url;
		this.user = user;
		this.password = password;
		this.driver = driver;
		this.driverClassName = null;
	}
	
	public DatabasePropertiesImpl(String url, String user, String password, String driverClassName) {
		this.url = url;
		this.user = user;
		this.password = password;
		this.driverClassName = driverClassName;
		this.driver = null;
	}

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public String getUser() {
		return user;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getDriverClassName() {
		return driverClassName;
	}

	@Override
	public Driver getDriver() {
		return driver;
	}

	@Override
	public String toString() {
		return "DatabasePropertiesImpl [url=" + url + ", user=" + user + ", password=" + password + ", driver=" + driver
				+ ", driverClassName=" + driverClassName + "]";
	}

}
