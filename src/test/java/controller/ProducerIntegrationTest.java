package controller;

import java.util.logging.Logger;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ProducerIntegrationTest {
	private static final Logger logger = Logger.getLogger(ProducerIntegrationTest.class.getName());
	private static final String PRODUCER_URL = "http://localhost:8080/rs/producer";

	@Test public void testProducer() throws Exception {
		Client client = Client.create();
		WebResource webResource = client.resource(PRODUCER_URL);
		String response = webResource.get(String.class);

		assertThat(response, is(not("")));
		logger.info(response);
	}

	@Test public void testInsert() throws Exception {
		Client client = Client.create();
		WebResource webResource = client.resource(PRODUCER_URL);
		String producer = "{\"id\":0,\"name\":\"Producer\",\"address\":\"Address\",\"piva\":\"123456\",\"email\":\"anemail\",\"telephone\":\"0123456789\",\"iban\":\"aaa123\"}";
		ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, producer);

		assertThat(response.getStatus(), is(200));
		logger.info(response.getEntity(String.class));
	}
}
