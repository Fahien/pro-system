package persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.Date;

import model.Format;
import model.OrderList;
import model.Producer;
import model.Product;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OrderListDAOTest {
	private OrderList order;

	@Before public void insertTest() {
		order = new OrderList();
		order.setId(1);
		order.setDate(Date.valueOf("2015-06-25"));

		Producer producer = new Producer();
		producer.setName("Producer");
		ProducerDAO producerDao = ProducerDAO.getInstance();
		producerDao.getConnection();
		producer = producerDao.insert(producer);
		producerDao.closeConnection();

		for (int i = 0; i < 10; i++) {
			Format format = new Format();
			format.setValue(1000 + i * 10);
			FormatDAO formatDao = FormatDAO.getInstance();
			formatDao.getConnection();
			format = formatDao.insert(format);
			formatDao.closeConnection();

			Product product = new Product();
			product.setName("Product" + i);
			product.setFormat(format);
			product.setProducer(producer);
			product.setNumber(i);
			ProductDAO productDao = ProductDAO.getInstance();
			productDao.getConnection();
			product = productDao.insert(product);
			productDao.closeConnection();

			order.addProduct(product);
		}

		OrderListDAO dao = OrderListDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue (connection != null);
		order = dao.insert(order);
		assertFalse(order.getId() == 0);
		dao.closeConnection();
	}

	@Test public void updateTest() {
		order.setId(1);
		order.setDate(Date.valueOf("2012-12-31"));
		order.setDelivery(Date.valueOf("2013-1-1"));

		for (Product product : order.getProducts()) {
			product.setNumber(product.getNumber() + 10);
		}

		OrderListDAO dao = OrderListDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue(connection != null);
		order = dao.update(order);
		assertEquals(order.getDate(), Date.valueOf("2012-12-31"));
		assertEquals(order.getProducts().size(), 10);
		dao.closeConnection();
	}

	@After public void deleteTest() {
		order.setId(1);
		order.setDate(Date.valueOf("2015-06-25"));

		OrderListDAO dao = OrderListDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue(connection != null);
		assertFalse( dao.delete(1));
		dao.closeConnection();
	}
}
