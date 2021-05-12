package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public void loadAllCountries(Map<Integer,Country> Map) {

		String sql = "SELECT ccode, StateAbb, StateNme FROM country ORDER BY StateAbb";
		
		//List<Country> result = new ArrayList<Country>();
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				Map.put(rs.getInt("ccode"), new Country(rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme")));
				
			}
			
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
		
	}

	public List<Border> getCountryPairs(int anno, Map<Integer,Country> Map) {

		String sql = "SELECT state1no, state2no "
				+ "FROM contiguity "
				+ "WHERE year <= ? AND conttype = 1";
		
		List<Border> result = new ArrayList<>();
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, anno);

			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				result.add(new Border(Map.get(rs.getInt("state1no")), Map.get(rs.getInt("state2no"))));
				
			}
			
			conn.close();
			
			return result;
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
		
	}
	
}
