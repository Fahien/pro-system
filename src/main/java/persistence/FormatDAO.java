package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import model.Format;

public class FormatDAO extends AbstractDAO {
	private static final FormatDAO INSTANCE = new FormatDAO();

	private FormatDAO() {}

	public static FormatDAO getInstance() {
		return INSTANCE;
	}

	private static final Logger logger = Logger.getLogger(FormatDAO.class.getName());

	public Format insert(Format format) {
		PreparedStatement insertFormat = null;
		String sql = "INSERT INTO format(id , value) ";
		try {
			int i = 0;
			if (format.getId() == 0) {
				insertFormat = connection.prepareStatement(sql + "VALUES (nextval('formatsequence'), ?)", Statement.RETURN_GENERATED_KEYS);
			} else {
				insertFormat = connection.prepareStatement(sql + "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
				insertFormat.setLong(++i, format.getId());
			}
			insertFormat.setInt(++i,format.getValue());
			insertFormat.executeUpdate();
			if (format.getId() == 0) {
				ResultSet result = insertFormat.getGeneratedKeys();
				if (result.next()) {
					format.setId(result.getLong(1));
					logger.info("Format id: " + format.getId());
				}
			}
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		return format;
	}

	public List<Format> selectAll() {
		List<Format> formats = new ArrayList<>();
		PreparedStatement selectAll = null;
		String sql = "SELECT * FROM format";
		try {
			selectAll = connection.prepareStatement(sql);
			ResultSet result = selectAll.executeQuery();
			while (result.next()) {
				Format format = new Format();
				format.setId(result.getLong(1));
				format.setValue(result.getInt(2));
				formats.add(format);
			}
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		return formats;
	}
}
