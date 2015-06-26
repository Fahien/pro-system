package service;

import java.util.List;

import model.Format;
import persistence.FormatDAO;

public class FormatService {
	private static final FormatService INSTANCE = new FormatService();

	private FormatService() {}

	public static FormatService getInstance() {
		return INSTANCE;
	}

	private FormatDAO formatDao = FormatDAO.getInstance();

	public List<Format> selectAll() {
		formatDao.getConnection();
		List<Format> formats = formatDao.selectAll();
		formatDao.closeConnection();
		return formats;
	}

	public Format insert(Format format) {
		formatDao.getConnection();
		formatDao.insert(format);
		formatDao.closeConnection();
		return format;
	}
}
