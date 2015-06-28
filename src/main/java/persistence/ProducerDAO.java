package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import model.Producer;

public class ProducerDAO extends AbstractDAO {
	private static final ProducerDAO INSTANCE = new ProducerDAO();

	private ProducerDAO() {}

	public static ProducerDAO getInstance() {
		return INSTANCE;
	}

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

	public List<Producer> selectAll() {
		List<Producer> producers = new ArrayList<>();
		PreparedStatement selectAll = null;
		String sql = "SELECT * FROM producer";
		try {
			selectAll = connection.prepareStatement(sql);
			ResultSet result = selectAll.executeQuery();
			while (result.next()) {
				Producer producer = new Producer();
				producer.setId(result.getLong(1));
				producer.setName(result.getString(2));
				producer.setAddress(result.getString(3));
				producer.setPiva(result.getString(4));
				producer.setEmail(result.getString(5));
				producer.setTelephone(result.getString(6));
				producer.setIban(result.getString(7));
				producers.add(producer);
			}
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		return producers;
	}

	public Producer selectById(long id) {
		Producer producer = null;
		PreparedStatement select = null;
		String sql = "SELECT * FROM producer WHERE id=?";
		try {
			select = connection.prepareStatement(sql);
			select.setLong(1, id);
			ResultSet result = select.executeQuery();
			if (result.next()) {
				producer = new Producer();
				producer.setId(result.getLong(1));
				producer.setName(result.getString(2));
				producer.setAddress(result.getString(3));
				producer.setPiva(result.getString(4));
				producer.setEmail(result.getString(5));
				producer.setTelephone(result.getString(6));
				producer.setIban(result.getString(7));
			}
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		return producer;
	}

	public Producer update(Producer producer) {
		PreparedStatement update = null;
		String sql = "UPDATE producer set name=?, address=?, piva=?, email=?, telephone=?, iban=? where id=?";
		try {
			int i = 0;
			update = connection.prepareStatement(sql);
			update.setString(++i, producer.getName());
			update.setString(++i, producer.getAddress());
			update.setString(++i, producer.getPiva());
			update.setString(++i, producer.getEmail());
			update.setString(++i, producer.getTelephone());
			update.setString(++i, producer.getIban());
			update.setLong(++i,  producer.getId());
			update.executeUpdate();
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		return producer;
	}

	public boolean delete(long id) {
		boolean result = false;
		PreparedStatement delete = null;
		String sql = "DELETE FROM producer WHERE id=?";
		try {
			delete = connection.prepareStatement(sql);
			delete.setLong(1, id);
			result = delete.execute();
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		return result;
	}

	public Producer select(Producer producer) {
		PreparedStatement select = null;
		String sql = "SELECT * FROM producer WHERE id=?";
		try {
			select = connection.prepareStatement(sql);
			select.setLong(1, producer.getId());
			ResultSet result = select.executeQuery();
			if (result.next()) {
				producer.setName(result.getString(2));
				producer.setAddress(result.getString(3));
				producer.setPiva(result.getString(4));
				producer.setEmail(result.getString(5));
				producer.setTelephone(result.getString(6));
				producer.setIban(result.getString(7));
			}
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		return producer;
	}
}