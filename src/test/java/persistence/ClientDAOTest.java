package persistence;


import static org.junit.Assert.*;

import java.sql.Connection;

import model.Client;

import org.junit.Test;

public class ClientDAOTest {

	@Test public void daoTest() {
		DAO dao = new ClientDAO();
		Connection connection = dao.getConnection();
		assertTrue(connection != null);
		dao.closeConnection();
	}

	@Test public void insertTest() {
		Client client = new Client();
		client.setFirstname("clientFirstname");
		client.setLastname("clientLastname");
		client.setAddress("address");
		client.setFiscalcode("fiscalcode");
		client.setPiva("piva");
		client.setTelephone("telephone");
		client.setEmail("email");


		ClientDAO dao = new ClientDAO();
		Connection connection = dao.getConnection();
		assertTrue (connection != null);
		client = dao.insert(client);
		assertFalse(client.getId() == 0);
		dao.closeConnection();
	}
}
