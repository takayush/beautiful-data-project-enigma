package edu.csula.datascience.acquisition;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import twitter4j.Status;

/**
 * An example of Collector implementation using Twitter4j with MongoDB Java driver
 */
public class TwitterCollector implements Collector<Status, Status> {
    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> collection;
    public TwitterCollector() {
        // establish database connection to MongoDB
        mongoClient = new MongoClient();
        // select `EnigmaDatabase` as database
        database = mongoClient.getDatabase("EnigmaDatabase");

        // select collection by name `tweetStreams`
        collection = database.getCollection("tweetStreams");
    }
    // MUNGEE implementation
    @Override
    public Boolean mungee(String src) {
    	if(src.equals(null))
    	{
    		return true;
    	}
    	else        return false;
    }

   // Saving document in MongoDB
	@Override
	public void save(String username, String profilelocation, long tweetId, String post) {
		 Document document = new Document();
     	document.put("TweetId", tweetId);
			document.put("Username", username);
			document.put("ProfileLocation", profilelocation);
			document.put("Content", post);
		collection.insertOne(document);
		
	}
	
}
