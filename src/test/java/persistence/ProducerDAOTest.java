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
		producer.setId(1);
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

	@Test public void selectTest() {
		insertTest();
		ProducerDAO dao = ProducerDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue(connection != null);
		Producer producer = dao.selectById(1);
		assertFalse(producer == null);
		dao.closeConnection();
	}

	@Test public void updateTest() {
		Producer producer = new Producer();
		producer.setId(1);
		producer.setName("Updated");
		producer.setAddress("AddressUp");
		producer.setPiva("PivaUp");
		producer.setEmail("EmailUp");
		producer.setTelephone("TelephoneUp");
		producer.setIban("IbanUp");

		ProducerDAO dao = ProducerDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue(connection != null);
		producer = dao.update(producer);
		assertEquals(producer.getName(), "Updated");
		dao.closeConnection();
	}

	@Test public void deleteTest() {
		ProducerDAO dao = ProducerDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue(connection != null);
		dao.delete(1);
		Producer producer = dao.selectById(1);
		assertEquals(producer, null);
		dao.closeConnection();
	}
}
