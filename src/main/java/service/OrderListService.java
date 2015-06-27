package service;

import java.util.List;

import model.OrderList;
import persistence.OrderListDAO;

public class OrderListService {
	private static final OrderListService INSTANCE = new OrderListService();

	private OrderListService() {}

	public static OrderListService getInstance() {
		return INSTANCE;
	}

	private OrderListDAO orderDao = OrderListDAO.getInstance();

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
}