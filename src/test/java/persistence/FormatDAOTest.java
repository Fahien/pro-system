package persistence;

import static org.junit.Assert.*;

import java.sql.Connection;

import model.Format;

import org.junit.Test;

public class FormatDAOTest {

	@Test public void daoTest() {
		DAO dao = FormatDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue(connection != null);
		dao.closeConnection();
	}

	@Test public void insertTest() {
		Format format = new Format();
		format.setValue(250);

		FormatDAO dao = FormatDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue (connection != null);
		format = dao.insert(format);
		assertFalse(format.getId() == 0);
		dao.closeConnection();
	}
}
