package com.aca.rest.dugoutcontroller;
	
	import java.util.ArrayList;
	import java.util.List;
	
	import javax.ws.rs.Consumes;
	import javax.ws.rs.DELETE;
	import javax.ws.rs.GET;
	import javax.ws.rs.POST;
	import javax.ws.rs.PUT;
	import javax.ws.rs.Path;
	import javax.ws.rs.PathParam;
	import javax.ws.rs.Produces;
	import javax.ws.rs.QueryParam;
	import javax.ws.rs.core.MediaType;
	import javax.ws.rs.core.Response;

	import com.aca.rest.dugoutmodel.Dugout;
	import com.aca.rest.dugoutmodel.EmailMessage;
	import com.aca.rest.dugoutmodel.Message;
	import com.aca.rest.dugoutmodel.TextSNS;
	import com.aca.rest.dugoutservice.BaseballService;

		
	@Path("/teams")
	public class DugoutAppController {
		
//		@Inject
//		private BaseballService service; 
		
		
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Dugout> getAllTeams() {
			BaseballService service = new BaseballService();
//			 List<Dugout> none = new ArrayList<Dugout>();
//			 return none;
			 
			return service.getAllTeams();
		}
		
//		@GET
//		@Path("/genre/{value}")
//		@Produces(MediaType.APPLICATION_JSON)
//		public List<Dugout> get(@PathParam("value") String ) {
//			return service.get();
//			
//		}
		
//		@GET
//		@Path("/{key}")
//		@Produces(MediaType.APPLICATION_JSON)
//		public List<Dugout> getByGenre(@PathParam("key") int key) {
//			return service.getByKey(key); 
//			
//		}	
//		
//		@DELETE   
//		@Path("/{key}")
//		@Produces(MediaType.APPLICATION_JSON)
//		public List<Dugout> deleteByKey(@PathParam("key") int key) {
//			return service.deleteByKey(key);
//			
//		}
//		
//		@POST   
//		@Produces(MediaType.APPLICATION_JSON)
//		public Dugout addTeam(Dugout newDugout) {
//			return service.addTeam(newDugout);
//			
//		}
//		
//		@PUT   
//		@Produces(MediaType.APPLICATION_JSON)
//		public Dugout updateDugout(Dugout updateDugout) {
//			return service.updateDugout(updateDugout);
//			
//		}
//		
//		@GET
//		@Path("/fancysearch")
//		@Produces(MediaType.APPLICATION_JSON)
//		public List<Dugout> getByFancySearch(
//				@QueryParam("english") String english,
//				@QueryParam("french") String french, 
//				@QueryParam("german") String german, 
//				@QueryParam("spanish") String spanish,
//				@QueryParam("media") String media,
//				@QueryParam("startdate") String startdate,
//				@QueryParam("enddate") String enddate) {
//			
//			System.out.println("english: " + ' ' + english);
//			System.out.println("french: " + ' ' + french);
//			System.out.println("german: " + ' ' + german);
//			System.out.println("spanish: " + ' ' + spanish);
//			System.out.println("media: " + ' ' + media);
//			System.out.println("startdate" + ' ' + startdate);
//			System.out.println("enddate" + ' ' + enddate);
//			
//			return getAllTeams(0);
//			
//		}
//		
//		@POST	
//		@Path("/email")
//		@Consumes({MediaType.APPLICATION_JSON})
//		@Produces({MediaType.APPLICATION_JSON})
//		public Response sendEmail(EmailMessage emailMessage) {		
//			BaseballService service = new BaseballService();
//			String result = service.sendEmail(emailMessage);
//			
//			Message message = new Message();
//			message.setMessage(result);
//			
//			return Response.status(200).entity(message).build();	
//		}
//		
//		@POST	
//		@Path("/text")
//		@Consumes({MediaType.APPLICATION_JSON})
//		@Produces({MediaType.APPLICATION_JSON})
//		public Response sendCustomerText(TextSNS textSNS) {
//			
//			BaseballService service = new BaseballService();
//			String result = service.sendText(textSNS);	
//			
//			Message message = new Message();
//			message.setMessage(result);
//				
//			return Response.status(200).entity(message).build();				
//		}
		
	}
	

