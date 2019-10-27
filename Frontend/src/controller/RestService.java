package controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.TwitterEntity;
import service.TwitterEJB;

@Path("/Rest")
@ApplicationPath("/resources")
@ApplicationScoped
public class RestService extends Application{

	//http://localhost:8080/Frontend/resources/Rest/tweets
	@EJB
	TwitterEJB twitterservice;

	@GET
	@Path("/tweets")
	@Produces(MediaType.APPLICATION_JSON)
	public Iterable<TwitterEntity> getAllTweets(){
		return twitterservice.findAll();
	}
	
}
