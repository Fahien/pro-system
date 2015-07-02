package controller;

import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ProductIntegrationTest {
	private static final Logger logger = Logger.getLogger(ProductIntegrationTest.class.getName());
	private static final String PRODUCER_URL = "http://localhost:8080/rs/producer";
	private static final String PRODUCT_URL = "http://localhost:8080/rs/product";

	@Before public void insert() throws Exception {
		Client client = Client.create();
		WebResource webResource = client.resource(PRODUCER_URL);
		String producer = "{\"id\":1,\"name\":\"Producer\",\"address\":\"Address\",\"piva\":\"123456\",\"email\":\"anemail\",\"telephone\":\"0123456789\",\"iban\":\"aaa123\"}";
		ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, producer);

		assertThat(response.getStatus(), is(200));
		logger.info(response.getEntity(String.class));
		
		webResource = client.resource(PRODUCT_URL);
		String product = "{\"id\":1,\"name\":\"ProductUpdated\",\"description\":\"Description\",\"image\":null,\"price\":0.0,\"format\":null,\"producer\":{\"id\":1,\"name\":null,\"address\":null,\"piva\":null,\"email\":null,\"telephone\":null,\"iban\":null},\"gain\":0.0,\"number\":0}";
		response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, product);

		assertThat(response.getStatus(), is(200));
		logger.info(response.getEntity(String.class));
	}

	@Test public void testProducts() throws Exception {
		Client client = Client.create();
		WebResource webResource = client.resource(PRODUCT_URL);
		String response = webResource.accept("application/json").type("application/json").get(String.class);

		assertThat(response, is(not("")));
		logger.info(response);
	}

	@Test public void testProduct() throws Exception {
		Client client = Client.create();
		WebResource webResource = client.resource(PRODUCT_URL + "/1");
		String resp = webResource.get(String.class);

		assertThat(resp, is(not("")));
		logger.info(resp);
	}

	@Test public void testProductName() throws Exception {
		Client client = Client.create();
		WebResource webResource = client.resource(PRODUCT_URL + "/name/Product");
		String resp = webResource.get(String.class);

		assertThat(resp, is(not("")));
		logger.info(resp);
	}

	@Test public void testUpdate() throws Exception {
		Client client = Client.create();
		WebResource webResource = client.resource(PRODUCT_URL);
		String product = "{\"id\":1,\"name\":\"ProductUpdated\",\"description\":\"Description\",\"image\":null,\"price\":0.0,\"gain\":0.0,\"producer\":{\"id\":1}}";
		ClientResponse response = webResource.accept("application/json").type("application/json").put(ClientResponse.class, product);

		assertThat(response.getStatus(), is(200));
		logger.info(response.getEntity(String.class));
	}

	@After public void testDelete() throws Exception {
		Client client = Client.create();
		WebResource webResource = client.resource(PRODUCT_URL + "/1");
		webResource.delete();
	}
}
