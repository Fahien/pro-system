package controller;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import service.OrderListService;
import model.OrderList;

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
		order = orderService.update(order);
		return order;
	}

	@Path("{id}")
	@DELETE public boolean delete(@PathParam("id") long id) {
		return orderService.delete(id);
	}
}
