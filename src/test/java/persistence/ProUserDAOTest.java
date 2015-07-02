package persistence;


import static org.junit.Assert.*;

import java.sql.Connection;

import model.ProUser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProUserDAOTest {

	@Before public void insertTest() {
		ProUser user = new ProUser();
		user.setId(1);
		user.setFirstname("Firstname");
		user.setLastname("Lastname");
		user.setAddress("address");
		user.setFiscalcode("fiscalcode");
		user.setPiva("piva");
		user.setTelephone("telephone");
		user.setEmail("email");

		ProUserDAO dao = ProUserDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue (connection != null);
		user = dao.insert(user);
		assertFalse(user.getId() == 0);
		dao.closeConnection();
	}

	@Test public void daoTest() {
		DAO dao = ProUserDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue(connection != null);
		dao.closeConnection();
	}

	@Test public void selectTest() {
		ProUserDAO dao = ProUserDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue(connection != null);
		ProUser client = dao.selectById(1);
		assertFalse(client == null);
		dao.closeConnection();
	}

	@Test public void updateTest() {
		ProUser user = new ProUser();
		user.setId(1);
		user.setFirstname("FirstnameUp");
		user.setLastname("LastnameUp");
		user.setAddress("AddressUp");
		user.setFiscalcode("FiscalcodeUp");
		user.setPiva("PivaUp");
		user.setTelephone("TelephoneUp");
		user.setEmail("EmailUp");

		ProUserDAO dao = ProUserDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue(connection != null);
		user = dao.update(user);
		assertEquals(user.getFirstname(), "FirstnameUp");
		dao.closeConnection();
	}

	@After public void deleteTest() {
		ProUserDAO dao = ProUserDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue(connection != null);
		dao.delete(1);
		ProUser client = dao.selectById(1);
		assertEquals(client, null);
		dao.closeConnection();
	}
}
