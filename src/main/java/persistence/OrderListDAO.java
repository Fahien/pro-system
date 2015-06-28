package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import model.Format;
import model.OrderList;
import model.Producer;
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
			for (Product product : order.getProducts()) {
				insert = connection.prepareStatement(sql + "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
				int i = 0;
				insert.setLong(++i, order.getId());
				insert.setLong(++i, product.getId());
				insert.setLong(++i, product.getFormat().getId());
				insert.setLong(++i, product.getProducer().getId());
				insert.setLong(++i, product.getNumber());
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

	public OrderList selectById(long id) {
		OrderList orderlist = null;
		PreparedStatement select = null;
		String sql = "SELECT * FROM orderlist WHERE id=?";
		try {
			select = connection.prepareStatement(sql);
			select.setLong(1, id);
			ResultSet result = select.executeQuery();
			if (result.next()) {
				orderlist = new OrderList();
				orderlist.setId(result.getLong(1));
				orderlist.setDate(result.getDate(2));
				orderlist.setDelivery(result.getDate(3));

			}
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}

		sql = "SELECT * FROM orderlistcontainsproduct WHERE idOrder=?";
		try {
			select = connection.prepareStatement(sql);
			select.setLong(1, id);
			ResultSet result = select.executeQuery();
			while (result.next()) {
				Product product = new Product();
				product.setId(result.getLong(2));
				Format format = new Format();
				format.setId(result.getLong(3));
				product.setFormat(format);
				Producer producer = new Producer();
				producer.setId(result.getLong(4));
				product.setProducer(producer);
				product.setNumber(result.getInt(5));
				orderlist.addProduct(product);
			}
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		return orderlist;
	}

	public OrderList update(OrderList order) {
		PreparedStatement update = null;
		String sql = "UPDATE orderlist set date=?, delivery=?, where id=?";
		try {
			int i = 0;
			update = connection.prepareStatement(sql);
			update.setDate(++i, order.getDate());
			update.setDate(++i, order.getDelivery());
			update.executeUpdate();
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		return order;
	}

	public boolean delete(long id) {
		boolean result = false;
		PreparedStatement delete = null;
		String sql = "DELETE FROM orderlist WHERE id=?";
		try {
			delete = connection.prepareStatement(sql);
			delete.setLong(1, id);
			result = delete.execute();
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		return result;
	}
}
