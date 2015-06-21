package persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import org.junit.Test;

public class PropertiesTest {
	private static final Logger logger = Logger.getLogger(PropertiesTest.class.getName());

	@Test public void loadProperties() {
		Properties properties = new Properties();
		FileInputStream in;
		try {
			in = new FileInputStream("src/main/resources/db.properties");
			try {
				properties.load(in);
				logger.info(properties.getProperty("db.url"));
			} catch (IOException e) {
				logger.warning(e.getMessage());
			}
			try {
				in.close();
			} catch (IOException e) {
				logger.warning(e.getMessage());
			}
		} catch (FileNotFoundException e) {
			logger.warning(e.getMessage());
		}
	}
}
