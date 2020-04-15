package ua.lviv.ura.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Statement;

import ua.lviv.ura.dao.ProductDao;
import ua.lviv.ura.domain.Product;
import ua.lviv.ura.utils.ConnectionUtil;

public class ProductDaoImpl implements ProductDao {

	private static String READ_ALL = "select * from product";
	private static String CREATE = "insert into product (categori_id, name, description, price) values (?, ?, ?, ?)";
	private static String READ_BY_ID = "select * from product where id =?";
	private static String UPDATE_BY_ID = "update produkt set categori_id =?, name =?, description =?, price =? where id =?";
	private static String DELETE_BY_ID = "delete from product where id =?";
	
	private static final Logger LOG = Logger.getLogger(ProductDaoImpl.class);

	private Connection connection;
	private PreparedStatement preparedStatement;

	public ProductDaoImpl()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		connection = ConnectionUtil.openConnection();
	}

	@Override
	public Product create(Product product) {
		try {
			preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, product.getCategoriId());
			preparedStatement.setString(2, product.getName());
			preparedStatement.setString(3, product.getDescription());
			preparedStatement.setInt(4, product.getPrice());
			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			product.setId(rs.getInt(1));
		} catch (SQLException e) {
			LOG.error(e);
		}

		return product;
	}

	@Override
	public Product read(int id) {
		Product product = null;
		try {
			preparedStatement = connection.prepareStatement(READ_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			result.next();

			int productId = result.getInt("id");
			int categoriId = result.getInt("categori_id");
			String name = result.getString("name");
			String description = result.getString("description");
			int price = result.getInt("price");
			product = new Product(productId, categoriId, name, description, price);

		} catch (SQLException e) {
			LOG.error(e);
		}

		return product;
	}

	@Override
	public Product update(Product product) {
		try {
			preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
			preparedStatement.setInt(1, product.getCategoriId());
			preparedStatement.setString(2, product.getName());
			preparedStatement.setString(3, product.getDescription());
			preparedStatement.setInt(4, product.getPrice());
			preparedStatement.setInt(5, product.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e);
		}

		return product;
	}

	@Override
	public void delete(int id) {
		try {
			preparedStatement = connection.prepareStatement(DELETE_BY_ID);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e);
		}
	}

	@Override
	public List<Product> readAll() {
		List<Product> products = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(READ_ALL);
			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {

				int productId = result.getInt("id");
				int categoriId = result.getInt("categori_id");
				String name = result.getString("name");
				String description = result.getString("description");
				int price = result.getInt("price");
				products.add(new Product(productId, categoriId, name, description, price));

			}
		} catch (SQLException e) {
			LOG.error(e);
		}
		return products;
	}

}
