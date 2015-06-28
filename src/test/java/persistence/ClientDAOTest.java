package persistence;


import static org.junit.Assert.*;

import java.sql.Connection;

import model.Client;
import org.junit.Test;

public class ClientDAOTest {

	@Test public void daoTest() {
		DAO dao = ClientDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue(connection != null);
		dao.closeConnection();
	}

	@Test public void insertTest() {
		Client client = new Client();
		client.setId(1);
		client.setFirstname("clientFirstname");
		client.setLastname("clientLastname");
		client.setAddress("address");
		client.setFiscalcode("fiscalcode");
		client.setPiva("piva");
		client.setTelephone("telephone");
		client.setEmail("email");

		ClientDAO dao = ClientDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue (connection != null);
		client = dao.insert(client);
		assertFalse(client.getId() == 0);
		dao.closeConnection();
	}

	@Test public void selectTest() {
		insertTest();
		ClientDAO dao = ClientDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue(connection != null);
		Client client = dao.selectById(1);
		assertFalse(client == null);
		dao.closeConnection();
	}

	@Test public void updateTest() {
		Client client = new Client();
		client.setId(1);
		client.setFirstname("FirstnameUp");
		client.setLastname("LastnameUp");
		client.setAddress("AddressUp");
		client.setFiscalcode("FiscalcodeUp");
		client.setPiva("PivaUp");
		client.setTelephone("TelephoneUp");
		client.setEmail("EmailUp");

		ClientDAO dao = ClientDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue(connection != null);
		client = dao.update(client);
		assertEquals(client.getFirstname(), "FirstnameUp");
		dao.closeConnection();
	}

	@Test public void deleteTest() {
		ClientDAO dao = ClientDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue(connection != null);
		dao.delete(1);
		Client client = dao.selectById(1);
		assertEquals(client, null);
		dao.closeConnection();
	}
}
