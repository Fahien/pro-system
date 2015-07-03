package persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.List;

import model.Producer;
import model.Product;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProductDAOTest {
	private Product product = new Product();
	private Producer producer = new Producer();

	@Before public void insertTest() {
		producer.setName("Producer");
		ProducerDAO producerDao = ProducerDAO.getInstance();
		producerDao.getConnection();
		producer = producerDao.insert(producer);
		producerDao.closeConnection();

		product.setName("Name");
		product.setDescription("Description");
		product.setImage("Url");
		product.setPrice(10.5f);
		product.setGain(1.0f);
		product.setProducer(producer);

		ProductDAO dao = ProductDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue(connection != null);
		product = dao.insert(product);
		assertFalse(product.getId() == 0);
		dao.closeConnection();
	}

	@Test public void daoTest() {
		DAO dao = ProductDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue(connection != null);
		dao.closeConnection();
	}

	@Test public void selectTest() {
		ProductDAO dao = ProductDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue(connection != null);
		Product selected = dao.selectById(product.getId());
		assertFalse(selected == null);
		assertFalse(selected.getProducer() == null);
		dao.closeConnection();
	}

	@Test public void selectAllTest() {
		ProductDAO dao = ProductDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue(connection != null);
		List<Product> products = dao.selectAll();
		assertFalse(products.size() == 0);
		dao.closeConnection();
	}
	
	@Test public void selectAllByNameTest() {
		ProductDAO dao = ProductDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue(connection != null);
		List<Product> products = dao.selectAllByName("Name");
		assertFalse(products.size() == 0);
		dao.closeConnection();
	}

	@Test public void updateTest() {
		product.setName("Updated");
		product.setDescription("Description");
		product.setImage("Url");
		product.setPrice(10.5f);
		product.setGain(1.0f);

		ProductDAO dao = ProductDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue(connection != null);
		product = dao.update(product);
		assertEquals(product.getName(), "Updated");
		dao.closeConnection();
	}

	@After public void deleteTest() {
		ProductDAO dao = ProductDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue(connection != null);
		dao.delete(product.getId());
		Product selected = dao.selectById(product.getId());
		assertEquals(selected, null);
		dao.closeConnection();
		
		ProducerDAO pdao = ProducerDAO.getInstance();
		connection = pdao.getConnection();
		assertTrue(connection != null);
		pdao.delete(producer.getId());
		Producer pselected = pdao.selectById(producer.getId());
		assertEquals(pselected, null);
		dao.closeConnection();
	}
}