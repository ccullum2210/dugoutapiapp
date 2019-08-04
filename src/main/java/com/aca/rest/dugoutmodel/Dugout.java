package com.aca.rest.dugoutmodel;

import java.time.LocalDateTime;

public class Dugout {
	
	private String team;
	//private String addTeam;
	//private String removeTeam;
	private Integer yearFounded;
	private String player;
	//private String addPlayer;
	//private String updatePlayer;
	//private String removePlayer;
	private int key;
	public LocalDateTime createDT;
	public LocalDateTime updateDT;
	
	public int wins;
	public int losses;
	
	public Dugout() {};
	
	public Dugout(String team,  Integer yearFounded, String player, int key, LocalDateTime createDT, LocalDateTime updateDT,
			int wins, int losses) {
		this.team = team;
		this.yearFounded = yearFounded;
		this.player = player;
		this.wins = wins;
		this.losses = losses;
//		this.addPlayer = addPlayer;
//		this.removePlayer = removePlayer;
		this.key = key;
		this.createDT = createDT;
		this.updateDT = updateDT;  
	}
	
	public String getTeam() {
		return team;
	}
	
	public void setTeams(String team) {
		this.team = team;
	}
	
	public int getWins() {
		return wins;
	}
	
	public void setWins(int Wins) {
		this.wins = wins;
	}
	
	public int getLosses() {
		return losses;
	}
	
	public void setLosses(int losses) {
		this.losses = losses;
	}
	
	public Integer getYearFounded() {
		return yearFounded;
	}
	
	public void setYearFounded(Integer yearFounded) {
		this.yearFounded = yearFounded;
	}

	

//	public String getAddPlayer() {
//		return addPlayer;
//	}
//
//	public String getRemovePlayer() {
//		return removePlayer;
//	}
//
//	public void setAddPlayer(String addPlayer) {
//		this.addPlayer = addPlayer;
//	}
//
//	public void setRemovePlayer(String removePlayer) {
//		this.removePlayer = removePlayer;
//	}
	public String getPlayer() {
		return player;
	}
	
	public void setplayer(String player) {
		this.player = player;
	}
	
	public void setKey(int key) {
		this.key = key;
	}
	
	public int getKey() {
		return key;
	}
	
	public void setCreateDT(LocalDateTime createDT) {
		this.updateDT = updateDT;
	}
	
	public LocalDateTime getCreateDT() {
		return createDT;
	}

	public void setUpdateDT(LocalDateTime updateDT) {
		this.updateDT = updateDT;
	}
	
	public LocalDateTime getUpdateDT() {
		return updateDT;
	}
	
	public String toString() {
		return "wins: " + this.wins;
	}
		
}
	

