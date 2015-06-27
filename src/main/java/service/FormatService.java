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

	public Format selectById(long id) {
		formatDao.getConnection();
		Format format = formatDao.selectById(id);
		formatDao.closeConnection();
		return format;
	}

	public Format update(Format format) {
		formatDao.getConnection();
		format = formatDao.update(format);
		formatDao.closeConnection();
		return format;
	}

	public boolean delete(long id) {
		formatDao.getConnection();
		boolean result = formatDao.delete(id);
		formatDao.closeConnection();
		return result;
	}

}
