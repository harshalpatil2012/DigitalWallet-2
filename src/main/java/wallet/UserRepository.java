package wallet;


import java.util.Arrays;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.stereotype.Repository;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
/**
 * 
 * @author Puneet Popli
 *
 */
@Repository
public class UserRepository
{


	private MongoTemplate mongoTemplate = mongoTemplate();
	
	
	public MongoTemplate mongoTemplate() 
	{

		try 
		{
	
			String mongoUri = "mongodb://puneetpopli:wallet2@ds049130.mongolab.com:49130/wallet"; //To connect using driver via URI
			MongoClientURI mongoLabUrl = new MongoClientURI(mongoUri);
			//To authenticate the user.
			MongoCredential mongoCredential = MongoCredential.createMongoCRCredential(mongoLabUrl.getUsername(), mongoLabUrl.getDatabase(), mongoLabUrl.getPassword());
			//To connect to the mongo server.
			MongoClient mongoClient = new MongoClient(new ServerAddress("ds049130.mongolab.com",49130), Arrays.asList(mongoCredential));
			MongoTemplate mongoTemplate = new MongoTemplate(mongoClient,mongoLabUrl.getDatabase());
			return mongoTemplate;
		}
		catch(Exception e)
		{
			return null;
		} 
	}

	String COLLECTION_NAME = "user";
    

	public List<User> findAll() 
	{
		return mongoTemplate.findAll(User.class,COLLECTION_NAME);
	}



	public User findById(String user_id) 
	{
		return mongoTemplate.findById(user_id, User.class,COLLECTION_NAME);
	}



	public void save(User user) 
	{
		mongoTemplate.save(user,COLLECTION_NAME);
	}

}

