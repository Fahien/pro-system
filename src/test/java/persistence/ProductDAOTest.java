package persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.List;

import model.Producer;
import model.Product;

import org.junit.Test;

public class ProductDAOTest {

	@Test public void daoTest() {
		DAO dao = ProductDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue(connection != null);
		dao.closeConnection();
	}

	@Test public void selectTest() {
		insertTest();
		ProductDAO dao = ProductDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue(connection != null);
		Product product = dao.selectById(1);
		assertFalse(product == null);
		dao.closeConnection();
	}

	public void insertTest() {
		Producer producer = new Producer();
		producer.setName("Producer");
		ProducerDAO producerDao = ProducerDAO.getInstance();
		producerDao.getConnection();
		producer = producerDao.insert(producer);
		producerDao.closeConnection();
		
		Product product = new Product();
		product.setId(1);
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
		List<Product> products = dao.selectAllByName("Updated");
		assertFalse(products.size() == 0);
		dao.closeConnection();
	}

	@Test public void updateTest() {
		Product product = new Product();
		product.setId(1);
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

	@Test public void deleteTest() {
		ProductDAO dao = ProductDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue(connection != null);
		dao.delete(1);
		Product product = dao.selectById(1);
		assertEquals(product, null);
		dao.closeConnection();
	}
}