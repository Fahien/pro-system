package service;

import java.util.List;

import model.Producer;
import persistence.ProducerDAO;

public class ProducerService {
	private static final ProducerService INSTANCE = new ProducerService();

	private ProducerService() {}

	public static ProducerService getInstance() {
		return INSTANCE;
	}

	private ProducerDAO producerDao = ProducerDAO.getInstance();

	public List<Producer> selectAll() {
		producerDao.getConnection();
		List<Producer> producers = producerDao.selectAll();
		producerDao.closeConnection();
		return producers;
	}

	public Producer insert(Producer producer) {
		producerDao.getConnection();
		producerDao.insert(producer);
		producerDao.closeConnection();
		return producer;
	}

	public Producer selectById(long id) {
		producerDao.getConnection();
		Producer producer = producerDao.selectById(id);
		producerDao.closeConnection();
		return producer;
	}

	public Producer update(Producer producer) {
		producerDao.getConnection();
		producer = producerDao.update(producer);
		producerDao.closeConnection();
		return producer;
	}

	public boolean delete(long id) {
		producerDao.getConnection();
		boolean result = producerDao.delete(id);
		producerDao.closeConnection();
		return result;
	}
}
