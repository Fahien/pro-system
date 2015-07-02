package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import model.ProUser;

public class ProUserDAO extends AbstractDAO {
	private static final ProUserDAO INSTANCE = new ProUserDAO();

	private ProUserDAO() {}

	public static ProUserDAO getInstance() {
		return INSTANCE;
	}

	private static final Logger logger = Logger.getLogger(ProUserDAO.class.getName());

	public ProUser insert(ProUser user) {
		PreparedStatement insert = null;
		String sql = "INSERT INTO prouser(id , firstname , lastname, address , fiscalcode , piva , telephone , email ) ";
		try {
			int i = 0;
			if (user.getId() == 0) {
				insert = connection.prepareStatement(sql + "VALUES (nextval('prousersequence'), ? , ? , ? , ? , ? , ? , ? )", Statement.RETURN_GENERATED_KEYS);
			} else {
				insert = connection.prepareStatement(sql + "VALUES (?, ? , ? , ? , ? , ? , ? , ?)", Statement.RETURN_GENERATED_KEYS);
				insert.setLong(++i, user.getId());
			}
			insert.setString(++i,user.getFirstname());
			insert.setString(++i,user.getLastname());
			insert.setString(++i,user.getAddress());
			insert.setString(++i,user.getFiscalcode());
			insert.setString(++i,user.getPiva());
			insert.setString(++i,user.getTelephone());
			insert.setString(++i,user.getEmail());

			insert.executeUpdate();
			if (user.getId() == 0) {
				ResultSet result = insert.getGeneratedKeys();
				if (result.next()) {
					user.setId(result.getLong(1));
				}
			}
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		return user;
	}

	public ProUser selectById(long id) {
		ProUser user = null;
		PreparedStatement select = null;
		String sql = "SELECT * FROM prouser WHERE id=?";
		try {
			select = connection.prepareStatement(sql);
			select.setLong(1, id);
			ResultSet result = select.executeQuery();
			if (result.next()) {
				user = new ProUser();
				user.setId(result.getLong(1));
				user.setFirstname(result.getString(2));
				user.setLastname(result.getString(3));
				user.setAddress(result.getString(4));
				user.setFiscalcode(result.getString(5));
				user.setPiva(result.getString(6));
				user.setTelephone(result.getString(7));
				user.setEmail(result.getString(8));
			}
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		return user;
	}

	public ProUser update(ProUser user) {
		PreparedStatement update = null;
		String sql = "UPDATE prouser SET firstname=?, lastname=?, address=?, fiscalcode=?, piva=?, telephone=?, email=? where id=?";
		try {
			int i = 0;
			update = connection.prepareStatement(sql);
			update.setString(++i, user.getFirstname());
			update.setString(++i, user.getLastname());
			update.setString(++i, user.getAddress());
			update.setString(++i, user.getFiscalcode());
			update.setString(++i, user.getPiva());
			update.setString(++i, user.getTelephone());
			update.setString(++i, user.getEmail());
			update.setLong(++i, user.getId());
			update.executeUpdate();
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		return user;
	}

	public boolean delete(long id) {
		boolean result = false;
		PreparedStatement delete = null;
		String sql = "DELETE FROM prouser WHERE id=?";
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