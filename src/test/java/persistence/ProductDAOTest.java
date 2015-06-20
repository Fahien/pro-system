package persistence;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;

import model.Product;

import org.junit.Test;

public class ProductDAOTest {

	@Test public void daoTest() {
		DAO dao = new ProductDAO();
		Connection connection = dao.getConnection();
		assertTrue(connection != null);
		dao.closeConnection();
	}

	@Test public void insertTest() {
		Product product = new Product();
		product.setName("Name");
		product.setDescription("Description");
		product.setImage("Url");
		product.setPrice(10.5f);
		
		ProductDAO dao = new ProductDAO();
		Connection connection = dao.getConnection();
		assertTrue(connection != null);
		product = dao.insert(product);
		assertFalse(product.getId() == 0);
		dao.closeConnection();
	}

}