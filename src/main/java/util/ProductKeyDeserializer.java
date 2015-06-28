package util;

import java.io.IOException;
import java.util.logging.Logger;

import model.Product;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.KeyDeserializer;

public class ProductKeyDeserializer extends KeyDeserializer {
	private static final Logger logger = Logger.getLogger(ProductKeyDeserializer.class.getName());
	@Override public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		Product product = new Product();
		logger.info(key);
		product.setId(Long.valueOf(key));
		return product;
	}
}