
package com.example.poc1.controllers;

import org.springframework.beans.factory.annotation.Autowired;

public class Database {

	private String dburl;
	
	public String getDburl() {
		return dburl;
	}

	public void setDburl(String dburl) {
		this.dburl = dburl;
	}
}