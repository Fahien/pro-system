package controller;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import service.ProductService;
import model.Product;

@Path("/product")
@Produces("application/json")
public class ProductController {
	ProductService productService = ProductService.getInstance();

	@GET public List<Product> selectAll() {
		List<Product> products = productService.selectAll();
		return products;
	}

	@Path("/name/{name}")
	@GET public List<Product> selectAllByName(@PathParam("name") String name) {
		List<Product> products = productService.selectAllByName(name);
		return products;
	}

	@Path("{id}")
	@GET public Product selectById(@PathParam("id") long id) {
		Product product = productService.selectById(id);
		return product;
	}

	@POST public Product insert(Product product) {
		product = productService.insert(product);
		return product;
	}

	@PUT public Product update(Product product) {
		product = productService.update(product);
		return product;
	}

	@Path("{id}")
	@DELETE public boolean delete(@PathParam("id") long id) {
		return productService.delete(id);
	}
}
