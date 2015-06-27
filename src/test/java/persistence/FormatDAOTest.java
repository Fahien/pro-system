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
		format.setId(1);
		format.setValue(250);

		FormatDAO dao = FormatDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue (connection != null);
		format = dao.insert(format);
		assertFalse(format.getId() == 0);
		dao.closeConnection();
	}

	@Test public void selectTest() {
		insertTest();
		FormatDAO dao = FormatDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue(connection != null);
		Format format = dao.selectById(1);
		assertFalse(format == null);
		dao.closeConnection();
	}

	@Test public void updateTest() {
		Format format = new Format();
		format.setId(1);
		format.setValue(300);

		FormatDAO dao = FormatDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue(connection != null);
		format = dao.update(format);
		assertEquals(format.getValue(), 300);
		dao.closeConnection();
	}

	@Test public void deleteTest() {
		FormatDAO dao = FormatDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue(connection != null);
		dao.delete(1);
		Format format = dao.selectById(1);
		assertEquals(format, null);
		dao.closeConnection();
	}
}
