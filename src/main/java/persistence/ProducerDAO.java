package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import model.Producer;

public class ProducerDAO extends AbstractDAO {
	private static final Logger logger = Logger.getLogger(ProducerDAO.class.getName());

	public Producer insert(Producer producer) {
		PreparedStatement insertProducer = null;
		String sql = "INSERT INTO producer(id , name , address , piva , email , telephone , iban ) ";
		try {
			int i = 0;
			if (producer.getId() == 0) {
				insertProducer = connection.prepareStatement(sql + "VALUES (nextval('producersequence'), ? , ? , ? , ? , ? , ? )", Statement.RETURN_GENERATED_KEYS);
			} else {
				insertProducer = connection.prepareStatement(sql + "VALUES (?, ? , ? , ? , ? , ? , ? )", Statement.RETURN_GENERATED_KEYS);
				insertProducer.setLong(++i, producer.getId());
			}
			insertProducer.setString(++i,producer.getName());
			insertProducer.setString(++i,producer.getAddress());
			insertProducer.setString(++i,producer.getPiva());
			insertProducer.setString(++i,producer.getEmail());
			insertProducer.setString(++i,producer.getTelephone());
			insertProducer.setString(++i,producer.getIban());
			insertProducer.executeUpdate();
			if (producer.getId() == 0) {
				ResultSet result = insertProducer.getGeneratedKeys();
				if (result.next()) {
					producer.setId(result.getLong(1));
					logger.info("Producer id: " + producer.getId());
				}
			}
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		return producer;
	}
}