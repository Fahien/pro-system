package controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import service.ProductService;
import model.Product;

@Path("/product")
@Produces("application/json")
public class ProductController {
	ProductService productService = new ProductService();

	@GET public List<Product> getProducts() {
		List<Product> products = productService.selectAll();
		return products;
	}
}
