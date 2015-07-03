package controller;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import exception.BadRequestException;
import service.OrderListService;
import model.OrderList;
import model.Product;

@Path("/order")
@Produces("application/json")
public class OrderController {
	OrderListService orderService = OrderListService.getInstance();

	@GET public List<OrderList> getOrders() {
		List<OrderList> orders = orderService.selectAll();
		return orders;
	}

	@POST public OrderList newOrderList(OrderList order) {
		orderService.insert(order);
		return order;
	}

	@Path("{id}")
	@GET public OrderList selectById(@PathParam("id") long id) {
		OrderList orderlist = orderService.selectById(id);
		return orderlist;
	}

	@PUT public OrderList update(OrderList order) {
		if (order == null || order.getProducts() != null) {
			for (Product product : order.getProducts()) {
				if (product.getFormat() == null || product.getProducer() == null) {
					throw new BadRequestException("Invalid product list");
				}
			}
		}
		order = orderService.update(order);
		return order;
	}

	@Path("{id}")
	@DELETE public boolean delete(@PathParam("id") long id) {
		return orderService.delete(id);
	}
}
