package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import model.Product;

public class ProductDAO extends AbstractDAO {
	private static final Logger logger = Logger.getLogger(ProductDAO.class.getName());

	public Product insert(Product product) {
		PreparedStatement insertProduct = null;
		String sql = "INSERT INTO product (id, name, description, image, price) ";
		try {
			int i = 0;
			if (product.getId() == 0) {
				insertProduct = connection.prepareStatement(sql + "VALUES (nextval('productsequence'), ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			} else {
				insertProduct = connection.prepareStatement(sql + "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
				insertProduct.setLong(++i, product.getId());
			}
			insertProduct.setString(++i, product.getName());
			insertProduct.setString(++i, product.getDescription());
			insertProduct.setString(++i, product.getImage());
			insertProduct.setFloat(++i, product.getPrice());
			insertProduct.executeUpdate();
			ResultSet result = insertProduct.getGeneratedKeys();
			if (result.next()) {
				product.setId(result.getLong(1));
				logger.info("Product id: " + product.getId());
			}
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
				products.add(product);
			}
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		return products;
	}
}
