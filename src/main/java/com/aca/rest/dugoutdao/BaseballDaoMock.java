package com.aca.rest.dugoutdao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.aca.rest.dugoutmodel.Dugout;

public class BaseballDaoMock implements BaseballDao{
	
	public BaseballDaoMock() {
		System.out.println("BaseballDaoMock instantiated");
	}
	
	private static List<Dugout> teams = new ArrayList<Dugout>();
	
	static {
		LocalDateTime now = LocalDateTime.now();
		teams.add(new Dugout("Astros", 2009, "player 1", 1, now, now, 0, 1));
		teams.add(new Dugout("Brewers", 2005, "player 2", 2, now, now, 1, 2));
		teams.add(new Dugout("Red Sox", 1989, "player 3", 3, now, now, 2, 3));
		teams.add(new Dugout("Yankees", 1985, "player 4", 4, now, now, 3, 4));
		
	}
	
	public List<Dugout> getByKey(int key) {
		List<Dugout> foundTeams = new ArrayList<Dugout>();
		
		for (Dugout dugout: BaseballDaoMock.teams) {
			if (dugout.getKey() == key) {
				foundTeams.add(dugout);
			}
		}
		return foundTeams;
	}

	@Override
	public Dugout addTeam(Dugout newDugout) {
		newDugout.setCreateDT(LocalDateTime.now());
		newDugout.setUpdateDT(newDugout.getCreateDT());
		BaseballDaoMock.teams.add(newDugout);
		return newDugout;
	}
	
	private int getNewKey() {
		int maxKey = 0;
		for (Dugout dugout : BaseballDaoMock.teams) {
			if (dugout.getKey() > maxKey) {
				maxKey = dugout.getKey();
			}
		}
		
		return maxKey + 1;
	}
	
		
	@Override
	public Dugout updateDugout(Dugout updateDugout) {
		Dugout myDugout = null;
		for (Dugout dugout : BaseballDaoMock.teams) {
			if (dugout.getKey() == updateDugout.getKey()) {
				
				if (updateDugout.getTeam() != null) {
					dugout.setTeam(updateDugout.getTeam());
				}
				
				if (updateDugout.getYearFounded() != null) {
					dugout.setYearFounded(updateDugout.getYearFounded());
				}
				
				myDugout = dugout;
				break;
			}
		}
		return myDugout;
	}


	@Override
	public List<Dugout> deleteByKey(int key) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Dugout> getByWins(int wins) {
		// TODO Auto-generated method stub
		return null;
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
	public Dugout createDT(Dugout createDT) {
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

	@Override
	public List<Dugout> getTeams(String team) {
		// TODO Auto-generated method stub
		return null;
	}	

}
