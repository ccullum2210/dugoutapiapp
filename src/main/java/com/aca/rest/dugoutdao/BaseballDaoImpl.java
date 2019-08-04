package com.aca.rest.dugoutdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.aca.rest.dugoutmodel.Dugout;

public class BaseballDaoImpl implements BaseballDao {
	
	public BaseballDaoImpl() {
		System.out.println("MovieDaoImpl instantiated");
	}
	
	private static String sqlGetAllTeams = " SELECT `key`, Team, YearFounded, Player, " 
											+ " , CreateDate, UpdateDate, Wins, Losses " +	
											" FROM dugout ";
	
	private static String sqlInsertDugout = "INSERT INTO dugout (Team, YearFounded, Wins, Losses) " +
											" VALUES  (?, ?, ?, ?) ";	
	
	private static String sqlDeleteDugout = " DELETE FROM Dugout WHERE `key` = ? ";
	
	private static String sqlGetByKey = " SELECT `key`, Team, YearFounded, " 
											+ " CreateDate, UpdateDate, Wins, Losses " +	
											" FROM dugout " +
											" WHERE `key` = ? ";
	
	private static String sqlGetByTeam = " SELECT `key`, Team, YearFounded, " 
											+ " Player, CreateDate, UpdateDate " +	
											" FROM dugout " +
											" WHERE Team = ? ";
	
	private static String sqlGetByWins =  " SELECT `key`, Wins, Losses " +
											" FROM dugout " +
											" WHERE Wins = ? ";
	
//	private static String sqlGetByYearFounded = "SELECT `key`, Team, YearFounded, " 
//											+ " CreateDate, UpdateDate " +
//											" FROM dugout " +
//											" WHERE YearFounded = ? ";
											
	
	private static String sqlUpdateByKey = "UPDATE Dugout " 
											+ " SET	Team = ?, YearFounded = ?, " 
											+ " WHERE `key` = ?";
	
//	private static String sqlGetCount = "SELECT count(*) AS movie_count " +
//										"FROM movie ";
//	

	@Override
	public List<Dugout> getAllTeams() {
		List<Dugout> teams = new ArrayList<Dugout>();
		
		ResultSet result = null;
		Statement statement = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {			
			statement = conn.createStatement();			
			result = statement.executeQuery(sqlGetAllTeams);
			
			while(result.next()) {
				Dugout dugout = makeDugout(result);
					teams.add(dugout);
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
	public Dugout addTeam(Dugout newDugout) {
		int	rowCount = 0;
		PreparedStatement statement = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {				
			statement = conn.prepareStatement(sqlInsertDugout);
			statement.setString(1, newDugout.getTeam());
			statement.setInt(2, newDugout.getYearFounded());
			statement.setInt(3, newDugout.getWins());
			statement.setInt(4, newDugout.getLosses());
	
			rowCount = statement.executeUpdate();				
				
			newDugout.setKey(getLastKey(conn));		
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			try {				
				statement.close();
				conn.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}
		
		return newDugout;		
	}

		private Integer getLastKey(Connection conn) throws SQLException {
			Integer key = 0;
			Statement statement = conn.createStatement();			
			ResultSet result = statement.executeQuery(" SELECT LAST_INSERT_ID() ");
	
			while(result.next()) {
				key = result.getInt("LAST_INSERT_ID()");				
			}
			return key;
		
	}

	@Override
	public List<Dugout> deleteByKey(int key) {
		List<Dugout> teams = this.getByKey(key);
		int	rowCount = 0;
		PreparedStatement statement = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {				
			statement = conn.prepareStatement(sqlDeleteDugout);
			statement.setInt(1, key);
			rowCount = statement.executeUpdate();				
			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			try {				
				statement.close();
				conn.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
			
		}
		
		return teams;
	}
	
	@Override
	public List<Dugout> getByKey(int key) {
		List<Dugout> teams = new ArrayList<Dugout>();
		
		ResultSet result = null;
		PreparedStatement statement = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {				
			statement = conn.prepareStatement(sqlGetByKey);
			statement.setInt(1, key);
			
			result = statement.executeQuery();	
			
			while(result.next()) {
				teams.add(makeDugout(result));
				
			}			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			try {				
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
		dugout.setKey(result.getInt("key"));
		dugout.setTeam(result.getString("Team"));
		dugout.setYearFounded(result.getInt("YearFounded"));
		
		
		dugout.setCreateDT(result.getObject("CreateDate", LocalDateTime.class));
		dugout.setUpdateDT(result.getObject("UpdateDate", LocalDateTime.class));
		return dugout;
		
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
	public Dugout updateDugout(Dugout updateDugout) {
		int	rowCount = 0;
		PreparedStatement statement = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {				
			statement = conn.prepareStatement(sqlUpdateByKey);
			statement.setString(1, updateDugout.getTeam());
			statement.setInt(2, updateDugout.getYearFounded());
			statement.setInt(3, updateDugout.getWins());
			statement.setInt(4, updateDugout.getLosses());
			statement.setInt(5, updateDugout.getKey());
	
			rowCount = statement.executeUpdate();				
			System.out.println("rows updated: " + rowCount);
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			try {				
				statement.close();
				conn.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}
		
		return updateDugout;		
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
	public Dugout addDugout(Dugout newDugout) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void publish(String message, String subject) {
		// TODO Auto-generated method stub
		
	}
	

}
