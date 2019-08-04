package com.aca.rest.dugoutdao;

import java.util.List;

import com.aca.rest.dugoutmodel.Dugout;

public interface BaseballDao {
	
	public List<Dugout> getTeams(String team); 
	public Dugout addTeam(Dugout newDugout);
	
	public Dugout addDugout(Dugout newDugout);
	public List<Dugout> getByKey(int key);
	public List<Dugout> deleteByKey(int key);
	
	public List<Dugout> getByWins(int maxWins);
	public List<Dugout> getByLosses(int losses);
	
	public Dugout addPlayer(Dugout addPlayer);
	public Dugout removePlayer(Dugout removePlayer);
	
	public Dugout updateDugout(Dugout updateDugout);
	public Dugout updatePlayer(Dugout updateDugout);
	public Dugout createDT(Dugout createDT);
	public Dugout updateDT(Dugout updateDT);
	
	public Dugout getByWins(); 
	public Dugout getByLoss();
	
	public void publish(String message, String subject);
	
	

}
