package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import model.OrderList;
import model.Product;

public class OrderListDAO extends AbstractDAO {
	private static final OrderListDAO INSTANCE = new OrderListDAO();

	private OrderListDAO() {}

	public static OrderListDAO getInstance() {
		return INSTANCE;
	}

	private static final Logger logger = Logger.getLogger(OrderListDAO.class.getName());

	public OrderList insert(OrderList order) {
		PreparedStatement insert = null;
		String sql = "INSERT INTO orderlist(id, date, delivery)";
		try {
			int i = 0;
			if (order.getId() == 0) {
				insert = connection.prepareStatement(sql + "VALUES (nextval('ordersequence'), ?, ?)", Statement.RETURN_GENERATED_KEYS);
			} else {
				insert = connection.prepareStatement(sql + "VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
				insert.setLong(++i, order.getId());
			}
			insert.setDate(++i,order.getDate());
			insert.setDate(++i,order.getDelivery());
			insert.executeUpdate();
			if (order.getId() == 0) {
				ResultSet result = insert.getGeneratedKeys();
				if (result.next()) {
					order.setId(result.getLong(1));
					logger.info("Order id: " + order.getId());
				}
			}
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}

		sql = "INSERT INTO orderlistcontainsproduct(idOrder, idProduct, idFormat, idProducer, number)";
		try {
			for (Map.Entry<Product, Integer> entry : order.getProducts().entrySet()) {
				insert = connection.prepareStatement(sql + "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
				int i = 0;
				insert.setLong(++i, order.getId());
				insert.setLong(++i, entry.getKey().getId());
				insert.setLong(++i, entry.getKey().getFormat().getId());
				insert.setLong(++i, entry.getKey().getProducer().getId());
				insert.setLong(++i, entry.getValue());
				insert.executeUpdate();
			}
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		return order;
	}

	public List<OrderList> selectAll() {
		List<OrderList> orders = new ArrayList<>();
		PreparedStatement selectAll = null;
		String sql = "SELECT * FROM orderlist";
		try {
			selectAll = connection.prepareStatement(sql);
			ResultSet result = selectAll.executeQuery();
			while (result.next()) {
				OrderList order = new OrderList();
				order.setId(result.getLong(1));
				order.setDate(result.getDate(2));
				order.setDelivery(result.getDate(3));
				orders.add(order);
			}
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		return orders;
	}
}
