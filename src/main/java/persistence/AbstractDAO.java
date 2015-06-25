package persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public abstract class AbstractDAO implements DAO {
	private static final Logger logger = Logger.getLogger(AbstractDAO.class.getName());
	private static final String DB_PROPERTIES = "/db.properties";
	private String dbUrl = getDbUrl();

	protected Connection connection;

	private Properties getDbProperties() {
		Properties db = new Properties();
		try {
			FileInputStream in = new FileInputStream(AbstractDAO.class.getResource(DB_PROPERTIES).getPath());
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

	private String getDbUrl() {
		Properties db = getDbProperties();
		return db.getProperty("db.url") + "?user=" + db.getProperty("db.username") + "&password=" + db.getProperty("db.password");
	}

	@Override public Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(dbUrl);
			logger.info("Connection opened");
		} catch (ClassNotFoundException e) {
			logger.warning(e.getMessage());
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		return connection;
	}

	@Override public void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
				logger.info("Connection closed");
			}
		} catch (Exception e) { 
			logger.warning(e.getMessage());
		}
	}
}
