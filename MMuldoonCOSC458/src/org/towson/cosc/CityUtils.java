package org.towson.cosc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CityUtils {

	public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
		String hostName = "localhost";
		String dbName = "world";
		String userName = "root";
		String password = "matt";
		return getMySQLConnection(hostName, dbName, userName, password);
	}

	public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password)
			throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.jdbc.Driver");

		String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?allowMultiQueries=true";

		Connection conn = DriverManager.getConnection(connectionURL, userName, password);
		
		return conn;
	}

	public static List<City> queryAllCities(Connection con) throws SQLException {
		String sql = "Select ID, Name, CountryCode, District, Population from City ";

		PreparedStatement pstm = con.prepareStatement(sql);

		ResultSet rs = pstm.executeQuery();
		List<City> list = new ArrayList<City>();
		while (rs.next()) {

			String id = rs.getString("ID");
			String name = rs.getString("Name");
			String countryCode = rs.getString("CountryCode");
			String district = rs.getString("District");
			int population = rs.getInt("Population");
			
			City city = new City();
			city.setId(id);
			city.setName(name);
			city.setCountryCode(countryCode);
			city.setDistrict(district);
			city.setPopulation(population);
			
			list.add(city);
		}
		return list;
	}


	public static List<City> queryCities(Connection con, String country ) throws SQLException {
		String sql = "Select ID, Name, CountryCode, District, Population from City where CountryCode = '" + country + "'";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery( sql );
		
		List<City> list = new ArrayList<City>();
		while (rs.next()) {

			String id = rs.getString("ID");
			String name = rs.getString("Name");
			String countryCode = rs.getString("CountryCode");
			String district = rs.getString("District");
			int population = rs.getInt("Population");
			
			City city = new City();
			city.setId(id);
			city.setName(name);
			city.setCountryCode(countryCode);
			city.setDistrict(district);
			city.setPopulation(population);
			
			list.add(city);
		}
		return list;
	}

	public static void main(String[] args) {

		try {
			Connection con = getMySQLConnection();
			
			List<City> cities = queryAllCities(con);
			
			List<City> countryCities = queryCities(con, "USA" );
			
			con.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub

	}

}
