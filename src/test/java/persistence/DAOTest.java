package persistence;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;

import org.junit.Test;

public class DAOTest {

	private class TestDAO extends AbstractDAO {}

	@Test public void daoTest() {
		DAO dao = new TestDAO();
		Connection connection = dao.getConnection();
		assertTrue(connection != null);
		dao.closeConnection();
	}
}
