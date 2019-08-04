package com.aca.rest.dugoutdao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.aca.rest.dugoutmodel.Dugout;

public class BaseballDaoDemo implements BaseballDao {
	
	public BaseballDaoDemo() {
		System.out.println( "BaseballDao Demo... JDBC Statement Demo" );
	}
	
	private static String sqlGetAllTeams = " SELECT Team, YearFounded, CreateDate, UpdateDate "
											+ " FROM Dugout ";
	
	private static String sqlGetByWins = "SELECT Team, YearFounded, CreateDate, UpdateDate "
										+ "FROM Dugout ";
	
	@Override 
	public List<Dugout> getAllTeams() {
			List<Dugout> teams = new ArrayList<Dugout>(); 
			
			ResultSet result = null;
			Statement statement = null;
			
			Connection conn = MariaDbUtil.getConnection();

			try {			
				statement = conn.createStatement();			
				result = statement.executeQuery(sqlGetAllTeams);
				
			} catch (SQLException e) {			
				e.printStackTrace();
			} finally {
				try {				
					result.close();
					statement.close();
					conn.close();
				} catch (SQLException e) {				
					e.printStackTrace();
				}
			}
			
			return teams;
			
			}
	
	private Dugout makeDugout(ResultSet result) throws SQLException {
		Dugout dugout = new Dugout();
		dugout.setTeam(result.getString("Team"));
		dugout.setYearFounded(result.getInt("YearFounded"));
		
		dugout.setUpdateDT(result.getObject("UpdateDate", LocalDateTime.class));
		return dugout;
	}
		

	
	
	
	
	
//	@Override
//	public void publish(String message, String subject) {
//		// TODO Auto-generated method stub
//		
//	}


	@Override
	public Dugout addTeam(Dugout newDugout) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Dugout> getByWins(int maxWins) {
		List<Dugout> teams = new ArrayList<Dugout>();
		
		ResultSet result = null;
		Statement statement = null;
		
		Connection conn = MariaDbUtil.getConnection();

		try {			
			statement = conn.createStatement();			
			result = statement.executeQuery(sqlGetByWins);
			
			while(result.next()) {
				if (result.getInt("wins") <= maxWins) {
					teams.add(makeDugout(result));
				}
				
			}	
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			try {				
				result.close();
				statement.close();
				conn.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}
		
		return teams;
	
	}

	@Override
	public List<Dugout> getByLosses(int losses) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dugout addPlayer(Dugout addPlayer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dugout removePlayer(Dugout removePlayer) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public Dugout updatePlayer(Dugout updateDugout) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Dugout createDT(Dugout createDT) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dugout updateDT(Dugout updateDT) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dugout getByWins() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dugout getByLoss() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Dugout> getByKey(int key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dugout updateDugout(Dugout updateDugout) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dugout> deleteByKey(int key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dugout addDugout(Dugout newDugout) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void publish(String message, String subject) {
		// TODO Auto-generated method stub
		
	}

}	


