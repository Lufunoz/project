package service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.TwitterEntity;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.*;


@Stateless
@LocalBean
@ApplicationScoped
public class TwitterEJB {

	@PersistenceContext
	private EntityManager em;

	public TwitterEJB() {
	}

	public void sendTweet(TwitterEntity twitterEntity) {
		//post on twitter
		String OAuthConsumerKey = "";
		String OAuthConsumerSecret = "";
		String OAuthAccessToken ="";
		String OAuthAccessTokenSecret = "";

	       ConfigurationBuilder cb = new ConfigurationBuilder();
	       cb.setDebugEnabled(true)
	           .setOAuthConsumerKey(OAuthConsumerKey)
	           .setOAuthConsumerSecret(OAuthConsumerSecret)
	           .setOAuthAccessToken(OAuthAccessToken)
	           .setOAuthAccessTokenSecret(OAuthAccessTokenSecret);

	       try 
	       {
	          TwitterFactory factory = new TwitterFactory(cb.build());
	          Twitter twitter = factory.getInstance();
	          System.out.println(twitter.getScreenName());
	          Status status = twitter.updateStatus(twitterEntity.getTweetBody());
	          
	          System.out.println("Successfully updated the status to [" + status.getText() + "].");
	           }catch (Exception te) {
	              te.printStackTrace();
	              System.exit(-1);
	           }
		
		//post to DB
		if(twitterEntity.getTweetBody().isEmpty()) {
			System.out.println("nothing to post");
		}else {
			System.out.println("Message posted = " + twitterEntity.getTweetBody());
			em.persist(twitterEntity);
			System.out.println("=======================Tweet saved to DB=============");
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<TwitterEntity> findAll(){
		System.out.println("===============");
		return em.createQuery("SELECT tweetID,tweetBody, timeStamp FROM tweets").getResultList();

	}
	
	public List<String> getNames(){
		List<String> userTweets = new ArrayList<String>();
		userTweets.add("Heroism");
		userTweets.add("Heroism ngwako");
		userTweets.add("Heroism Vho ngwako weee");
		userTweets.add("Mathekga H");
		for (int i=0; i< userTweets.size();i++) {
			System.out.println(userTweets.get(i));
		}
		System.out.println("get names function");
		return userTweets;
	}
}
