package service;

import java.util.List;

import model.Product;
import persistence.ProductDAO;

public class ProductService {
	private ProductDAO productDao = new ProductDAO();

	public List<Product> selectAll() {
		productDao.getConnection();
		List<Product> products = productDao.selectAll();
		productDao.closeConnection();
		return products;
	}
}