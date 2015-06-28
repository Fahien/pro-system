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

import org.junit.Test;

public class OrderListDAOTest {
	@Test public void insertTest() {
		OrderList order = new OrderList();
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
			format.setValue(300 + i * 10);
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

	@Test public void selectTest() {
		OrderList order = new OrderList();
		order.setId(2);
		order.setDate(Date.valueOf("2015-06-28"));
		
		OrderListDAO dao = OrderListDAO.getInstance();
		dao.getConnection();
		order = dao.insert(order);
		order= dao.selectById(2);
		assertFalse(order == null);
		dao.closeConnection();
	}

	@Test public void updateTest() {
		OrderList order = new OrderList();
		order.setId(1);
		order.setDate(Date.valueOf("2012-12-31"));
		order.setDelivery(Date.valueOf("2013-1-1"));

		OrderListDAO dao = OrderListDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue(connection != null);
		order = dao.update(order);
		assertEquals(order.getDate(), Date.valueOf("2012-12-31"));
		dao.closeConnection();
	}

	@Test public void deleteTest() {
		OrderList order = new OrderList();
		order.setId(2);
		order.setDate(Date.valueOf("2015-06-25"));
		
		OrderListDAO dao = OrderListDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue (connection != null);
		order = dao.insert(order);
		assertFalse(order.getId() == 0);

		assertTrue(connection != null);
		dao.delete(2);
		order = dao.selectById(2);
		assertEquals(order, null);
		dao.closeConnection();
	}
}
