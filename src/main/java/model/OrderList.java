package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OrderList {

	private long id;

	private Date date;

	private Date delivery;

	private List<Product> products = new ArrayList<>();

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

	public Date getDelivery() {
		return delivery;
	}

	public void setDelivery(Date delivery) {
		this.delivery = delivery;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> product) {
		this.products = product;
	}

	public void addProduct(Product product) {
		products.add(product);
	}
}
