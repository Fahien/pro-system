package controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import service.ProductService;
import model.Product;

@Path("/product")
@Produces("application/json")
public class ProductController {
	ProductService productService = ProductService.getInstance();

	@GET public List<Product> getProducts() {
		List<Product> products = productService.selectAll();
		return products;
	}

	@Path("{id}")
	@GET public Product getProductById(@PathParam("id") long id) {
		Product product = productService.selectById(id);
		return product;
	}

	@POST public Product newProduct(Product product) {
		productService.insert(product);
		return product;
	}
}
