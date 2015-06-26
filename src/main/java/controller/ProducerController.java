package controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import service.ProducerService;
import model.Producer;

@Path("/producer")
@Produces("application/json")
public class ProducerController {
	ProducerService producerService = ProducerService.getInstance();

	@GET public List<Producer> getProducers() {
		List<Producer> producers = producerService.selectAll();
		return producers;
	}

	@POST public Producer newProducer(Producer producer) {
		producerService.insert(producer);
		return producer;
	}
}
