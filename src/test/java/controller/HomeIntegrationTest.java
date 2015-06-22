package controller;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class HomeIntegrationTest {
	private static String HOME_URL = "http://localhost:8080/rs/";

	@Test public void testHello() throws Exception {
		Client client = Client.create();
		WebResource webResource = client.resource(HOME_URL);
		String response = webResource.get(String.class);

		assertThat(response, is("{\"id\":0,\"name\":\"Product\",\"description\":null,\"image\":null,\"price\":0.0,\"producer\":null}"));
	}
}
