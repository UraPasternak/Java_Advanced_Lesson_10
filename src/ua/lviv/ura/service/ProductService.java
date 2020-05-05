package ua.lviv.ura.service;

import java.util.Map;

import ua.lviv.ura.domain.Product;
import ua.lviv.ura.shared.AbstractCRUD;

public interface ProductService extends AbstractCRUD <Product> {
	public Map<Integer, Product> readAllMap();
}
