package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import model.Producer;
import model.Product;

public class ProductDAO extends AbstractDAO {
	private static final ProductDAO INSTANCE = new ProductDAO();

	private ProductDAO() {}

	public static ProductDAO getInstance() {
		return INSTANCE;
	}

	private static final Logger logger = Logger.getLogger(ProductDAO.class.getName());

	public Product insert(Product product) {
		PreparedStatement insert = null;
		String sql = "INSERT INTO product (id, name, description, image, price, gain, producer) ";
		try {
			int i = 0;
			if (product.getId() == 0) {
				insert = connection.prepareStatement(sql + "VALUES (nextval('productsequence'), ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			} else {
				insert = connection.prepareStatement(sql + "VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
				insert.setLong(++i, product.getId());
			}
			insert.setString(++i, product.getName());
			insert.setString(++i, product.getDescription());
			insert.setString(++i, product.getImage());
			insert.setFloat(++i, product.getPrice());
			insert.setFloat(++i, product.getGain());
			insert.setLong(++i, product.getProducer().getId());
			insert.executeUpdate();
			ResultSet result = insert.getGeneratedKeys();
			if (result.next()) {
				product.setId(result.getLong(1));
			}
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		return product;
	}

	public Product update(Product product) {
		PreparedStatement update = null;
		String sql = "UPDATE product set name=?, description=?, image=?, price=?, gain=? where id=?";
		try {
			int i = 0;
			update = connection.prepareStatement(sql);
			update.setString(++i, product.getName());
			update.setString(++i, product.getDescription());
			update.setString(++i, product.getImage());
			update.setFloat(++i, product.getPrice());
			update.setFloat(++i, product.getGain());
			update.setLong(++i, product.getId());
			update.executeUpdate();
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		return product;
	}

	public List<Product> selectAll() {
		List<Product> products = new ArrayList<>();
		PreparedStatement selectAll = null;
		String sql = "SELECT * FROM product";
		try {
			selectAll = connection.prepareStatement(sql);
			ResultSet result = selectAll.executeQuery();
			while (result.next()) {
				Product product = new Product();
				product.setId(result.getLong(1));
				product.setName(result.getString(2));
				product.setDescription(result.getString(3));
				product.setImage(result.getString(4));
				product.setPrice(result.getFloat(5));
				product.setGain(result.getFloat(6));
				products.add(product);
			}
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		return products;
	}

	public Product selectById(long id) {
		Product product = null;
		PreparedStatement select = null;
		String sql = "SELECT * FROM product JOIN producer ON product.producer=producer.id AND product.id=?";
		try {
			select = connection.prepareStatement(sql);
			select.setLong(1, id);
			ResultSet result = select.executeQuery();
			if (result.next()) {
				product = new Product();
				product.setId(result.getLong(1));
				product.setName(result.getString(2));
				product.setDescription(result.getString(3));
				product.setImage(result.getString(4));
				product.setPrice(result.getFloat(5));
				product.setGain(result.getFloat(6));
				Producer producer = new Producer();
				producer.setId(result.getLong(9));
				producer.setName(result.getString(10));
				product.setProducer(producer);
			}
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		return product;
	}

	public boolean delete(long id) {
		boolean result = false;
		PreparedStatement delete = null;
		String sql = "DELETE FROM product WHERE id=?";
		try {
			delete = connection.prepareStatement(sql);
			delete.setLong(1, id);
			result = delete.execute();
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		return result;
	}

	public Product select(Product product) {
		PreparedStatement select = null;
		String sql = "SELECT * FROM product WHERE id=?";
		try {
			select = connection.prepareStatement(sql);
			select.setLong(1, product.getId());
			ResultSet result = select.executeQuery();
			if (result.next()) {
				product.setName(result.getString(2));
				product.setDescription(result.getString(3));
				product.setImage(result.getString(4));
				product.setPrice(result.getFloat(5));
				product.setGain(result.getFloat(6));
			}
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		return product;
	}

	public List<Product> selectAllByName(String name) {
		List<Product> products = new ArrayList<>();
		PreparedStatement selectAll = null;
		String sql = "SELECT * FROM product WHERE UPPER(name) LIKE UPPER(?)";
		try {
			selectAll = connection.prepareStatement(sql);
			selectAll.setString(1, "%" + name + "%");
			ResultSet result = selectAll.executeQuery();
			while (result.next()) {
				Product product = new Product();
				product.setId(result.getLong(1));
				product.setName(result.getString(2));
				product.setDescription(result.getString(3));
				product.setImage(result.getString(4));
				product.setPrice(result.getFloat(5));
				product.setGain(result.getFloat(6));
				Producer producer = new Producer();
				producer.setId(result.getLong(7));
				product.setProducer(producer);
				products.add(product);
			}
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		return products;
	}
}
