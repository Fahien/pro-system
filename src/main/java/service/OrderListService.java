package service;

import java.util.List;

import model.Format;
import model.OrderList;
import model.Producer;
import model.Product;
import persistence.FormatDAO;
import persistence.OrderListDAO;
import persistence.ProducerDAO;
import persistence.ProductDAO;

public class OrderListService {
	private static final OrderListService INSTANCE = new OrderListService();

	private OrderListService() {}

	public static OrderListService getInstance() {
		return INSTANCE;
	}

	private OrderListDAO orderDao = OrderListDAO.getInstance();
	private ProductDAO productDao = ProductDAO.getInstance();
	private FormatDAO formatDao = FormatDAO.getInstance();
	private ProducerDAO producerDao = ProducerDAO.getInstance();

	public List<OrderList> selectAll() {
		orderDao.getConnection();
		List<OrderList> orders = orderDao.selectAll();
		orderDao.closeConnection();
		return orders;
	}

	public OrderList insert(OrderList order) {
		orderDao.getConnection();
		orderDao.insert(order);
		orderDao.closeConnection();
		return order;
	}

	public OrderList selectById(long id) {
		orderDao.getConnection();
		OrderList orderlist= orderDao.selectById(id);
		for (Product product : orderlist.getProducts()) {
			Format format = product.getFormat();
			formatDao.getConnection();
			formatDao.select(format);
			formatDao.closeConnection();
			Producer producer = product.getProducer();
			producerDao.getConnection();
			producerDao.select(producer);
			producerDao.closeConnection();
			productDao.getConnection();
			productDao.select(product);
			productDao.closeConnection();
		}
		orderDao.closeConnection();
		return orderlist;
	}

	public OrderList update(OrderList order) {
		orderDao.getConnection();
		order = orderDao.update(order);
		orderDao.closeConnection();
		return order;
	}

	public boolean delete(long id) {
		orderDao.getConnection();
		boolean result = orderDao.delete(id);
		orderDao.closeConnection();
		return result;
	}
}
