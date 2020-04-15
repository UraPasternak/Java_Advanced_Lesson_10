package ua.lviv.ura.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Statement;

import ua.lviv.ura.dao.CategoriDao;
import ua.lviv.ura.domain.Categori;
import ua.lviv.ura.utils.ConnectionUtil;

public class CategoriDaoImpl implements CategoriDao {

	private static String READ_ALL = "select * from categori";
	private static String CREATE = "insert into categori (name) values (?)";
	private static String READ_BY_ID = "select * from categori where id =?";
	private static String UPDATE_BY_ID = "update categori set name =? where id =?";
	private static String DELETE_BY_ID = "delete from categori where id =?";
	
	private static final Logger LOG = Logger.getLogger(CategoriDaoImpl.class);
	
	private Connection connection;
	private PreparedStatement preparedStatement;
	
	public CategoriDaoImpl() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		connection = ConnectionUtil.openConnection();
	}
	
	@Override
	public Categori create(Categori categori) {
		try {
			preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, categori.getName());
			preparedStatement.executeUpdate();
			
			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			categori.setId(rs.getInt(1));
		} catch (SQLException e) {
			LOG.error(e);
		}
		
		return categori;
	}

	@Override
	public List<Categori> readAll() {
		List<Categori> categoris = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(READ_ALL);
			ResultSet result = preparedStatement.executeQuery();
			
			while(result.next()){
			
			int categoriId = result.getInt("id");
			String name = result.getString("name");
			categoris.add(new Categori(categoriId, name));
			
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
		return categoris;
	}
	
	@Override
	public Categori read(int id) {
		Categori categori = null;
		try {
			preparedStatement = connection.prepareStatement(READ_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			result.next();
			
			int categoriId = result.getInt("id");
			String name = result.getString("name");
			categori = new Categori(categoriId, name);
			
		} catch (SQLException e) {
			LOG.error(e);
		}
		
		return categori;
	}

	@Override
	public Categori update(Categori categori) {
		try {
			preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
			preparedStatement.setString(1, categori.getName());
			preparedStatement.setInt(2, categori.getId());
		} catch (SQLException e) {
			LOG.error(e);
		}
		return null;
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
