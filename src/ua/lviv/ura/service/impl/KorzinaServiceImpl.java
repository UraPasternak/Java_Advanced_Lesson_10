package ua.lviv.ura.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import ua.lviv.ura.dao.KorzinaDao;
import ua.lviv.ura.dao.impl.KorzinaDaoImpl;
import ua.lviv.ura.domain.Korzina;
import ua.lviv.ura.service.KorzinaService;

public class KorzinaServiceImpl implements KorzinaService {

	private KorzinaDao korzinaDao;
	private static KorzinaService ksim;
	private static final Logger LOG = Logger.getLogger(KorzinaServiceImpl.class);
	
	private KorzinaServiceImpl(){
		try {
			korzinaDao = new KorzinaDaoImpl();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			LOG.error(e);
		}
	}
	
	public static KorzinaService getKorzinaService(){
		if(ksim==null){
			ksim = new KorzinaServiceImpl();
		}
		return ksim;
	}

	@Override
	public Korzina create(Korzina t) {
		
		return korzinaDao.create(t);
	}

	@Override
	public List<Korzina> readAll() {
		
		return korzinaDao.readAll();
	}

	@Override
	public Korzina read(int id) {
		
		return korzinaDao.read(id);
	}

	@Override
	public Korzina update(Korzina t) {
		
		return korzinaDao.update(t);
	}

	@Override
	public void delete(int id) {
		
		korzinaDao.delete(id);
	}
}
