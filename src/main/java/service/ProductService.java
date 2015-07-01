package service;

import java.util.List;

import model.Product;
import persistence.ProductDAO;

public class ProductService {
	private static final ProductService INSTANCE = new ProductService();

	private ProductService() {}

	public static ProductService getInstance() {
		return INSTANCE;
	}

	private ProductDAO productDao = ProductDAO.getInstance();

	public List<Product> selectAll() {
		productDao.getConnection();
		List<Product> products = productDao.selectAll();
		productDao.closeConnection();
		return products;
	}

	public Product insert(Product product) {
		productDao.getConnection();
		productDao.insert(product);
		productDao.closeConnection();
		return product;
	}

	public Product selectById(long id) {
		productDao.getConnection();
		Product product = productDao.selectById(id);
		productDao.closeConnection();
		return product;
	}

	public Product update(Product product) {
		productDao.getConnection();
		product = productDao.update(product);
		productDao.closeConnection();
		return product;
	}

	public boolean delete(long id) {
		productDao.getConnection();
		boolean result = productDao.delete(id);
		productDao.closeConnection();
		return result;
	}

	public List<Product> selectAllByName(String name) {
		productDao.getConnection();
		List<Product> products = productDao.selectAllByName(name);
		productDao.closeConnection();
		return products;
	}
}
