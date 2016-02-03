package de.tr.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.apache.log4j.Logger;

/**
 * The Startup class. Its task is to create the database TrailerRental with
 * MySQL if it does not exist.
 * 
 * @author
 */
public class Startup {
	private static Logger log = Logger.getLogger(Startup.class);
	private String driverClass;
	private String user;
	private String password;
	private String defaultDBurl;
	private String newDB;

	/**
	 * initDB method generates the user specified database for the application.
	 * Spring tries to execute the bean with id="init".
	 * 
	 * @throws Exception
	 *             if database already exists.
	 */
	public void initDB() {
		try {
			log.info("Try to create database " + newDB);
			Class.forName(driverClass);
			Connection db = DriverManager.getConnection(defaultDBurl, user,
					password);
			Statement st = db.createStatement();
			String command = "CREATE DATABASE " + newDB;
			log.info(command);
			st.execute(command);									  // execute the command to create new database
			st.close();
			db.close();
			log.info("Creating database " + newDB + " successful!");
		} catch (Exception e) {
			log.info("Create database " + newDB
					+ " failed - it probably already exists.");       //if database already exists
		}
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setDefaultDBurl(String defaultDBurl) {
		this.defaultDBurl = defaultDBurl;
	}

	public void setNewDB(String newDB) {
		this.newDB = newDB;
	}
}
