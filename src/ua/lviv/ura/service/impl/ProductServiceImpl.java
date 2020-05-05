package ua.lviv.ura.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import ua.lviv.ura.dao.ProductDao;
import ua.lviv.ura.dao.impl.ProductDaoImpl;
import ua.lviv.ura.domain.Product;
import ua.lviv.ura.service.ProductService;

public class ProductServiceImpl implements ProductService {
	
	private ProductDao productDao;
	private static ProductService psim;
	private static final Logger LOG = Logger.getLogger(ProductServiceImpl.class);
	
	private ProductServiceImpl(){
		try {
			productDao = new ProductDaoImpl();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			LOG.error(e);
		}
	}
	
	public static ProductService getProductService(){
		if(psim==null){
			psim = new ProductServiceImpl();
		}
		return psim;
	}

	@Override
	public Product create(Product t) {
		
		return productDao.create(t);
	}

	@Override
	public List<Product> readAll() {
		
		return productDao.readAll();
	}

	@Override
	public Product read(int id) {
		
		return productDao.read(id);
	}

	@Override
	public Product update(Product t) {
		
		return productDao.update(t);
	}

	@Override
	public void delete(int id) {
		
		productDao.delete(id);
	}
	
	@Override
	public Map<Integer, Product> readAllMap() {
		return  readAll().stream().collect(Collectors.toMap(Product::getId, Function.identity()));
	}
}
