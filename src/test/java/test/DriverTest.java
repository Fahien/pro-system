package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

import org.junit.Test;

public class DriverTest {
	private static final Logger logger = Logger.getLogger(DriverTest.class.getName());
	private static final int C_NUM = 1;

	@Test public void testDriver() {
		Connection[] connection = new Connection[C_NUM];
		try {
			Class.forName("org.postgresql.Driver");
			for (int i = 0; i < C_NUM; i++) {
				if(connection[i] == null) {
					Properties db = getDbProperties();
					String dbUrl = db.getProperty("db.url") + "?user=" + db.getProperty("db.username") + "&password=" + db.getProperty("db.password");
					connection[i] = DriverManager.getConnection(dbUrl);
					logger.info("Connection opened " + i);
				}
			}
		} catch (ClassNotFoundException e) {
			logger.warning(e.getMessage());
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		try {
			for (int i = 0; i < C_NUM; i++) {
				if (connection[i] != null) {
					connection[i].close();
					logger.info("Connection closed" + i);
				}
			}
		} catch (Exception e) { 
			logger.warning(e.getMessage());
		}
	}

	private Properties getDbProperties() {
		Properties db = new Properties();
		try {
			FileInputStream in = new FileInputStream("src/test/resources/db.properties");
			try {
				db.load(in);
			} catch (IOException e) {
				logger.warning(e.getMessage());
			}
			try {
				in.close();
			} catch (IOException e) {
				logger.warning(e.getMessage());
			}
		} catch (FileNotFoundException e) {
			logger.warning(e.getMessage());
		}
		return db;
	}
}
