package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import model.Client;

public class ClientDAO extends AbstractDAO {
	private static final ClientDAO INSTANCE = new ClientDAO();

	private ClientDAO() {}

	public static ClientDAO getInstance() {
		return INSTANCE;
	}

	private static final Logger logger = Logger.getLogger(ClientDAO.class.getName());

	public Client insert(Client client) {
		PreparedStatement insertClient = null;
		String sql = "INSERT INTO client(id , firstname , lastname, address , fiscalcode , piva , telephone , email ) ";
		try {
			int i = 0;
			if (client.getId() == 0) {
				insertClient = connection.prepareStatement(sql + "VALUES (nextval('clientsequence'), ? , ? , ? , ? , ? , ? , ? )", Statement.RETURN_GENERATED_KEYS);
			} else {
				insertClient = connection.prepareStatement(sql + "VALUES (?, ? , ? , ? , ? , ? , ? , ?)", Statement.RETURN_GENERATED_KEYS);
				insertClient.setLong(++i, client.getId());
			}
			insertClient.setString(++i,client.getFirstname());
			insertClient.setString(++i,client.getLastname());
			insertClient.setString(++i,client.getAddress());
			insertClient.setString(++i,client.getFiscalcode());
			insertClient.setString(++i,client.getPiva());
			insertClient.setString(++i,client.getTelephone());
			insertClient.setString(++i,client.getEmail());

			insertClient.executeUpdate();
			if (client.getId() == 0) {
				ResultSet result = insertClient.getGeneratedKeys();
				if (result.next()) {
					client.setId(result.getLong(1));
					logger.info("Client id: " + client.getId());
				}
			}
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		return client;
	}

	public Client selectById(long id) {
		Client client = null;
		PreparedStatement select = null;
		String sql = "SELECT * FROM client WHERE id=?";
		try {
			select = connection.prepareStatement(sql);
			select.setLong(1, id);
			ResultSet result = select.executeQuery();
			if (result.next()) {
				client = new Client();
				client.setId(result.getLong(1));
				client.setFirstname(result.getString(2));
				client.setLastname(result.getString(3));
				client.setAddress(result.getString(4));
				client.setFiscalcode(result.getString(5));
				client.setPiva(result.getString(6));
				client.setTelephone(result.getString(7));
				client.setEmail(result.getString(8));
			}
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		return client;
	}

	public Client update(Client client) {
		PreparedStatement update = null;
		String sql = "UPDATE product set firstname=?, lastname=?, address=?, fiscalcode=?, piva=?, telephone=?, email=? where id=?";
		try {
			int i = 0;
			update = connection.prepareStatement(sql);
			update.setString(++i, client.getFirstname());
			update.setString(++i, client.getLastname());
			update.setString(++i, client.getAddress());
			update.setString(++i, client.getFiscalcode());
			update.setString(++i, client.getPiva());
			update.setString(++i, client.getTelephone());
			update.setString(++i, client.getEmail());
			update.setLong(++i, client.getId());
			update.executeUpdate();
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		return client;
	}

	public boolean delete(long id) {
		boolean result = false;
		PreparedStatement delete = null;
		String sql = "DELETE FROM client WHERE id=?";
		try {
			delete = connection.prepareStatement(sql);
			delete.setLong(1, id);
			result = delete.execute();
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		return result;
	}
}