package com.example.OnlineSeatBook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
public class OnlineSeatBookApplication {

	private static final Logger logger = LoggerFactory.getLogger(OnlineSeatBookApplication.class);

	public static void main(String[] args) {
		createDatabaseIfNotExist("onlineseatbooking", "jdbc:postgresql://dpg-cpnulr5ds78s73b6ug6g-a.oregon-postgres.render.com/onlineseatbooking", "onlineseatbooking_user", "MRAKfh5a7RJHRoOWwhLm4mif5sysASIk");
		SpringApplication.run(OnlineSeatBookApplication.class, args);
	}

	private static void createDatabaseIfNotExist(String dbName, String url, String username, String password) {
		Connection connection = null;
		Statement statement = null;
		try {
			logger.debug("Creating database if not exist...");
			connection = DriverManager.getConnection(url, username, password);
			statement = connection.createStatement();
			statement.executeQuery("SELECT count(*) FROM pg_database WHERE datname = '" + dbName + "'");
			ResultSet resultSet = statement.getResultSet();
			resultSet.next();
			int count = resultSet.getInt(1);

			if (count <= 0) {
				statement.executeUpdate("CREATE DATABASE \"" + dbName + "\"");
				logger.debug("Database created.");
			} else {
				logger.debug("Database already exists.");
			}
		} catch (SQLException e) {
			logger.error("Error while creating the database", e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
					logger.debug("Closed Statement.");
				}
				if (connection != null) {
					connection.close();
					logger.debug("Closed Connection.");
				}
			} catch (SQLException e) {
				logger.error("Error while closing resources", e);
			}
		}
	}
}
