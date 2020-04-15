package ua.lviv.ura.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Statement;

import ua.lviv.ura.dao.KorzinaDao;
import ua.lviv.ura.domain.Korzina;
import ua.lviv.ura.utils.ConnectionUtil;

public class KorzinaDaoImpl implements KorzinaDao {

	private static String READ_ALL = "select * from korzina";
	private static String CREATE = "insert into korzina (user_id, product_id, purchase_date) values (?, ?, ?)";
	private static String READ_BY_ID = "select * from korzina where id =?";
	private static String DELETE_BY_ID = "delete from korzina where id =?";
	
	private static final Logger LOG = Logger.getLogger(KorzinaDaoImpl.class);
	
	private Connection connection;
	private PreparedStatement preparedStatement;
	
	public KorzinaDaoImpl() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		connection = ConnectionUtil.openConnection();
	}
	
	@Override
	public Korzina create(Korzina korzina) {
		try {
			preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, korzina.getUserId());
			preparedStatement.setInt(2, korzina.getProductId());
			preparedStatement.setDate(3, new Date(korzina.getPurchaseDate().getTime()));
			preparedStatement.executeUpdate();
			
			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			korzina.setId(rs.getInt(1));
			
		} catch (SQLException e) {
			LOG.error(e);
		}
		
		return korzina;
	}

	@Override
	public List<Korzina> readAll() {
		List<Korzina> korzinas = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(READ_ALL);
			ResultSet result = preparedStatement.executeQuery();
			
			while(result.next()){
			
			int id = result.getInt("id");
			int userId = result.getInt("user_id");
			int productId = result.getInt("product_id");
			java.util.Date purchaseDate = result.getDate("purchase_date");
			korzinas.add(new Korzina(id, userId, productId, purchaseDate));
			
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
		return korzinas;
	}
	
	@Override
	public Korzina read(int id) {
		Korzina korzina = null;
		try {
			preparedStatement = connection.prepareStatement(READ_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			result.next();
			
			int korzinaId = result.getInt("id");
			int userId = result.getInt("user_id");
			int productId = result.getInt("product_id");
			java.util.Date purchaseDate = result.getDate("purchase_date");
			korzina = new Korzina(korzinaId, userId, productId, purchaseDate);
			
		} catch (SQLException e) {
			LOG.error(e);
		}
		
		return korzina;
	}

	@Override
	public Korzina update(Korzina korzina) {
		throw new IllegalStateException("Ви не можете редагувати корзину");
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

}
