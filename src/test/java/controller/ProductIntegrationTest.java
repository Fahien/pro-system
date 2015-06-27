package controller;

import java.util.logging.Logger;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ProductIntegrationTest {
	private static final Logger logger = Logger.getLogger(ProductIntegrationTest.class.getName());
	private static String PRODUCT_URL = "http://localhost:8080/rs/product";

	@Test public void testProducts() throws Exception {
		Client client = Client.create();
		WebResource webResource = client.resource(PRODUCT_URL);
		String response = webResource.get(String.class);

		assertThat(response, is(not("")));
		logger.info(response);
	}

	@Test public void testProduct() throws Exception {
		Client client = Client.create();
		WebResource webResource = client.resource(PRODUCT_URL + "/1");
		String response = webResource.get(String.class);

		assertThat(response, is(not("")));
		logger.info(response);
	}

	@Test public void testInsert() throws Exception {
		Client client = Client.create();
		WebResource webResource = client.resource(PRODUCT_URL);
		String product = "{\"id\":0,\"name\":\"Product\",\"description\":\"Description\",\"image\":null,\"price\":0.0,\"gain\":0.0,\"producer\":null}";
		ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, product);

		assertThat(response.getStatus(), is(200));
		logger.info(response.getEntity(String.class));
	}
}
