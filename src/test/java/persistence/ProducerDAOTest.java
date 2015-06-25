package persistence;

import static org.junit.Assert.*;

import java.sql.Connection;

import model.Producer;

import org.junit.Test;

public class ProducerDAOTest {

	@Test public void daoTest() {
		DAO dao = ProducerDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue(connection != null);
		dao.closeConnection();
	}

	@Test public void insertTest() {
		Producer producer = new Producer();
		producer.setName("producer");
		producer.setAddress("address");
		producer.setPiva("piva");
		producer.setEmail("email");
		producer.setTelephone("telephone");
		producer.setIban("iban");

		ProducerDAO dao = ProducerDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue (connection != null);
		producer = dao.insert(producer);
		assertFalse(producer.getId() == 0);
		dao.closeConnection();
	}
}
