package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import model.Client;

public class ClientDAO extends AbstractDAO {
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
}