package model;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class OrderList {

	private long id;

	private Date date;

	private Map<Product, Integer> products = new HashMap<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Map<Product, Integer> getProducts() {
		return products;
	}

	public void setProducts(Map<Product, Integer> product) {
		this.products = product;
	}

	public void addProduct(Product product, int number) {
		products.put(product, number);
	}
}
