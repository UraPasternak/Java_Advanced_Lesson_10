package ua.lviv.ura.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import ua.lviv.ura.dao.CategoriDao;
import ua.lviv.ura.dao.impl.CategoriDaoImpl;
import ua.lviv.ura.domain.Categori;
import ua.lviv.ura.service.CategoriService;

public class CategoriServiceImpl implements CategoriService {

	private CategoriDao categoriDao;
	private static CategoriService csim;
	private static final Logger LOG = Logger.getLogger(CategoriServiceImpl.class);
	
	private CategoriServiceImpl(){
		try {
			categoriDao = new CategoriDaoImpl();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			LOG.error(e);
		}
	}
	
	public static CategoriService getCategoriService(){
		if(csim==null){
			csim = new CategoriServiceImpl();
		}
		return csim;
	}

	@Override
	public Categori create(Categori t) {
		
		return categoriDao.create(t);
	}

	@Override
	public List<Categori> readAll() {
		
		return categoriDao.readAll();
	}

	@Override
	public Categori read(int id) {
		
		return categoriDao.read(id);
	}

	@Override
	public Categori update(Categori t) {
		
		return categoriDao.update(t);
	}

	@Override
	public void delete(int id) {
		
		categoriDao.delete(id);
	}
}
