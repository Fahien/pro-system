package util;

import java.io.IOException;

import model.Product;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.KeyDeserializer;

public class ProductKeyDeserializer extends KeyDeserializer {
	@Override public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		Product product = new Product();
		product.setId(Long.valueOf(key));
		return product;
	}
}