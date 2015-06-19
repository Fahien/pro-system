package persistence;

import java.sql.Connection;

public interface DAO {
	Connection getConnection();
	void closeConnection();
}
