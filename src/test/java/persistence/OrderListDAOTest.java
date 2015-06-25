package persistence;

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
		order.setDate(Date.valueOf("2015-06-25"));
		
		Producer producer = new Producer();
		producer.setName("Producer");
		ProducerDAO producerDao = ProducerDAO.getInstance();
		producerDao.getConnection();
		producer = producerDao.insert(producer);
		producerDao.closeConnection();

		for (int i = 0; i < 10; i++) {
			Format format = new Format();
			format.setValue(250 + i);
			FormatDAO formatDao = FormatDAO.getInstance();
			formatDao.getConnection();
			format = formatDao.insert(format);
			formatDao.closeConnection();

			Product product = new Product();
			product.setName("Product" + i);
			product.setFormat(format);
			product.setProducer(producer);
			ProductDAO productDao = ProductDAO.getInstance();
			productDao.getConnection();
			product = productDao.insert(product);
			productDao.closeConnection();
			
			order.addProduct(product, i);
		}

		OrderListDAO dao = OrderListDAO.getInstance();
		Connection connection = dao.getConnection();
		assertTrue (connection != null);
		order = dao.insert(order);
		assertFalse(order.getId() == 0);
		dao.closeConnection();
	}
}
