package com.aca.rest.dugoutservice;

import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.aca.rest.dugoutdao.AwsSnsPublish;
import com.aca.rest.dugoutdao.BaseballDao;
import com.aca.rest.dugoutdao.BaseballDaoImpl;
import com.aca.rest.dugoutdao.BaseballDaoMock;
import com.aca.rest.dugoutmodel.Dugout;
import com.aca.rest.dugoutmodel.EmailMessage;
import com.aca.rest.dugoutmodel.Error;
import com.aca.rest.dugoutmodel.TextSNS;


public class BaseballService {
	
	private BaseballDao dao = new BaseballDaoImpl();  
// 	private MovieDao dao = new MovieDaoMock();
	
	
	public List<Dugout> getAllTeams() {
		return dao.getAllTeams();
	}
	
//	public List<Dugout> getTeam(String team) {
//		validTeam(team);
//		return dao.getTeam(team); 
//		
//	}
//	
//	private boolean validTeam(String team) {
//		boolean valid = false;
//		if (genre.equalsIgnoreCase("action") ||
//			genre.equalsIgnoreCase("syfy") ||
//			genre.equalsIgnoreCase("comedy") ||
//			genre.equalsIgnoreCase("horror")) {
//			
//			valid = true;
//		} else {
//			
//			Error error = new Error(101, "invalid value for genre");
//			Response response = Response.status(400)
//					.entity(error)
//					.build();
//			throw new WebApplicationException(response);
//		}
//		
//		return valid;
//		
//	}
	
	public List<Dugout> deleteByKey(int key) {
		if (dao.getByKey(key).size() != 1) {
			
			Error error = new Error(100, "invalid delete by key request, team for key '" + key + "' does not exist"); 
			Response response = Response.status(400)
					.entity(error)
					.build();
			throw new WebApplicationException(response);
		}
		
		return dao.deleteByKey(key);
		
		
	}
	
	public List<Dugout> getByKey(int key) {
		return dao.getByKey(key);
		
		}
	
	public Dugout addTeam(Dugout newDugout) {
		validYearFounded(newDugout.getYearFounded());
		validTeam(newDugout.getTeam());
		Dugout dugout = dao.addTeam(newDugout);
		sendNewMovieNotification(dugout);
		
		return dugout;
	}
	
	private void sendNewMovieNotification(Dugout dugout) {
        String subject = "new dugout - " + dugout.getTeam();
        String message = "team: " + dugout.getTeam() + "\n";
        message = message + "yearFounded: " + dugout.getYearFounded();
        dao.publish(message, subject);
    }
	
	private boolean validYearFounded(int yearFounded) {
		boolean valid = false;
		int maxYear = LocalDateTime.now().getYear();
		if (yearFounded >= 1900 && yearFounded <= maxYear) {
			
			valid = true;
		} else {
			
			Error error = new Error(102, "invalid value for yearFounded");
			Response response = Response.status(400)
					.entity(error)
					.build();
			throw new WebApplicationException(response);
		}
		
		return valid;
	}
	
	
	private boolean validTeam(String team) {
		boolean valid = false;
		if (null != team & team.length() > 2 && team.length() <= 50) {
			
			valid = true;
		} else {
			
			Error error = new Error(103, "invalid value for team");
			Response response = Response.status(400)
					.entity(error)
					.build();
			throw new WebApplicationException(response);
		}
		
		return valid;
	}
	
	public Dugout updateDugout(Dugout updateDugout) {
		boolean isValidUpdate = false;
		if (updateDugout.getTeam() != null) {
			validTeam(updateDugout.getTeam());
			isValidUpdate = true;
		}
		if (updateDugout.getTeam() != null) {
			validTeam(updateDugout.getTeam());
			isValidUpdate = true;
		}
		if (updateDugout.getYearFounded() != null) {
			validYearFounded(updateDugout.getYearFounded());
			isValidUpdate = true;
		}
		
		if (isValidUpdate) {
			validKey(updateDugout.getKey());
		} else { 
			invalidUpdate();
		
		}
		
		return dao.updateDugout(updateDugout);
		
		

	}
	
	private boolean validKey(int key) {
		boolean valid = false;
		if (0 != key) {
			List<Dugout> teamsByKey = dao.getByKey(key);
			if (teamsByKey.size() == 1) {
				valid = true;
			}
			
		}
		if (!valid) {
			
			Error error = new Error(103, "invalid value for title");
			Response response = Response.status(400)
					.entity(error)
					.build();
			throw new WebApplicationException(response);
		}
		
		return valid;
	}
	
	private void invalidUpdate() {
		Error error = new Error(104, "invalid update, no values to update");
		Response response = Response.status(400)
				.entity(error)
				.build();
		throw new WebApplicationException(response);
	
	}
	
	public String sendEmail(EmailMessage emailMessage) {
		AwsSnsPublish service = new AwsSnsPublish();
		String messageId = service.sendEmail(emailMessage);		
		return messageId;
	}
	
	public String sendText(TextSNS textSNS) {
		AwsSnsPublish awsSnsService = new AwsSnsPublish();
		String messageId = awsSnsService.sendText(textSNS);
		return messageId;
	}

}
