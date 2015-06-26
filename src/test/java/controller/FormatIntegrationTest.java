package controller;

import java.util.logging.Logger;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class FormatIntegrationTest {
	private static final Logger logger = Logger.getLogger(FormatIntegrationTest.class.getName());
	private static String FORMAT_URL = "http://localhost:8080/rs/format";

	@Test public void testFormat() throws Exception {
		Client client = Client.create();
		WebResource webResource = client.resource(FORMAT_URL);
		String response = webResource.get(String.class);

		assertThat(response, is(not("")));
		logger.info(response);
	}

	@Test public void testInsert() throws Exception {
		Client client = Client.create();
		WebResource webResource = client.resource(FORMAT_URL);
		String format = "{\"id\":0,\"value\":\"200\"}";
		ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, format);

		assertThat(response.getStatus(), is(200));
		logger.info(response.getEntity(String.class));
	}
}
