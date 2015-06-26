package controller;

import java.util.logging.Logger;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class OrderIntegrationTest {
	private static final Logger logger = Logger.getLogger(OrderIntegrationTest.class.getName());
	private static String FORMAT_URL = "http://localhost:8080/rs/order";

	@Test public void testOrderList() throws Exception {
		Client client = Client.create();
		WebResource webResource = client.resource(FORMAT_URL);
		String response = webResource.get(String.class);

		assertThat(response, is(not("")));
		logger.info(response);
	}

	@Test public void testInsert() throws Exception {
		Client client = Client.create();
		WebResource webResource = client.resource(FORMAT_URL);
		String order = "{\"id\":0,\"date\":\"2015-06-26\"}";
		ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, order);

		assertThat(response.getStatus(), is(200));
		logger.info(response.getEntity(String.class));
	}
}
