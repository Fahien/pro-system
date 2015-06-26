package model;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import util.ProductKeyDeserializer;

public class OrderList {

	private long id;

	private Date date;

	private Date delivery;

	@JsonDeserialize(keyUsing = ProductKeyDeserializer.class)
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

	public Date getDelivery() {
		return delivery;
	}

	public void setDelivery(Date delivery) {
		this.delivery = delivery;
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
