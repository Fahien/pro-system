package controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
}
