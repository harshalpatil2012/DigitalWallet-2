package wallet;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * 
 * @author Puneet Popli
 *
 */

@RestController
@RequestMapping("/api/v1")
public class UserController 
{
	@Autowired
	private UserRepository userRepo;
	
	
	//--------Methods for USER-----------
	
	//Creating a User. Post method
	@RequestMapping(value="/users/", method=RequestMethod.POST) //Http Response Code: 201
	public ResponseEntity<User> users(@Valid @RequestBody User set)
	{
			User user_object = new User();
			user_object.setUser(set);
			userRepo.save(user_object);  //Saving user in the repository
			return new ResponseEntity<User>(user_object, HttpStatus.CREATED);
	}
	
	

	//Get Method
	@RequestMapping(value="/users/{user_id}", method=RequestMethod.GET)
	
	public ResponseEntity<User> getusers(@PathVariable String user_id)
	{ 
		
		User user_object = userRepo.findById(user_id);
		if(user_object != null)
		{
	
			return new ResponseEntity<User>(user_object, HttpStatus.OK);
		}
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND); 
	}	
	
	
	//Get all users
	@RequestMapping(value="/users", method=RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public List<User> getAllUsers()
	{
		return userRepo.findAll();
		
	}
	
	//Put method
	@RequestMapping(value="/users/{user_id}", method=RequestMethod.PUT)
	//@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<User> updateuser(@PathVariable String user_id,@Valid @RequestBody User set)
	{
		
		User user_object = userRepo.findById(user_id);
		
		if(user_object != null)
		{
		user_object.putUser(set, user_id, user_object.generatedDate);
		userRepo.save(user_object);
		return new ResponseEntity<User>(user_object, HttpStatus.CREATED);
		
		}
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);//The server successfully processed the request, 
																//but is not returning any content
		
	}	
	
	//-------------------Methods for CARD-----------------
	
	
		//Post Method
		@RequestMapping(value="/users/{user_id}/idcards", method=RequestMethod.POST)
		//@ResponseStatus(HttpStatus.CREATED)
		public ResponseEntity<Card> cards(@PathVariable String user_id, @Valid @RequestBody Card set)
		{
			
			
			User user_object = userRepo.findById(user_id);
			Card card_object = new Card();
			if(user_object != null)
			{
				card_object.setCard(set);
				user_object.setCardList(card_object);
				userRepo.save(user_object);
				return new ResponseEntity<Card>(card_object, HttpStatus.CREATED);
			}
			
			return new ResponseEntity<Card>(HttpStatus.NOT_FOUND);
		}
		
		
		//Get Method
		@RequestMapping(value="/users/{user_id}/idcards", method=RequestMethod.GET)
		public ArrayList<Card> getcard(@PathVariable String user_id)
		{
			
			
			User user_object = userRepo.findById(user_id);
			if(user_object != null)
			{
				user_object.getCardList();
				return user_object.getCardList();
			}
			
			return null;
		}
		
		//Delete Method
		@RequestMapping(value="/users/{user_id}/idcards/{card_id}", method=RequestMethod.DELETE)
		//@ResponseStatus(HttpStatus.NO_CONTENT)
		public ResponseEntity<Card> deleteCard(@PathVariable String user_id, @Valid @PathVariable String card_id)
		{
			
			User user_object = userRepo.findById(user_id);
			if(null != user_object)
			{
				System.out.println("In first IF ");
				if(user_object.deleteIdCard(card_id))
				{
				
					userRepo.save(user_object);
					System.out.println("In second IF ");
					return new ResponseEntity<Card>(HttpStatus.NO_CONTENT);
						
				}
			}
			System.out.println("Outside If ");
			return new ResponseEntity<Card>(HttpStatus.NOT_FOUND);
		}
		
	
	
			
	
		
		//-------------------Methods for WEBLOGIN-----------------
		
		//Post Method
		@RequestMapping(value="/users/{user_id}/weblogins", method=RequestMethod.POST)
		@ResponseStatus(HttpStatus.CREATED)
		public ResponseEntity<WebLogin> weblogin(@PathVariable String user_id, @Valid @RequestBody WebLogin set)
		{
			
			
			User user_object = userRepo.findById(user_id);
			WebLogin web_login_object = new WebLogin();
			
			
			if(user_object != null)
			{
				web_login_object.setWebLogin(set);
				user_object.setWebList(web_login_object);
				userRepo.save(user_object);
				return new ResponseEntity<WebLogin>(web_login_object, HttpStatus.CREATED);
			}
			
			return new ResponseEntity<WebLogin>(web_login_object, HttpStatus.NOT_FOUND);
		}
		
		
		
		//Get Method
			@RequestMapping(value="/users/{user_id}/weblogins", method=RequestMethod.GET)
			public ArrayList<WebLogin> getweb(@PathVariable String user_id)
			{
				User user_object = userRepo.findById(user_id);
				if(user_object !=null)
				{
					user_object.getWebList();
					return user_object.getWebList();
				}
				
				return null;
			}
			
			//Delete Method
			@RequestMapping(value="/users/{user_id}/weblogins/{login_id}", method=RequestMethod.DELETE)
			//@ResponseStatus(HttpStatus.NO_CONTENT)
			public ResponseEntity<WebLogin> deleteWebLogin(@PathVariable String user_id, @Valid @PathVariable String login_id)
			{
				User user_object = userRepo.findById(user_id);
				if(user_object != null)
				{
					System.out.println("In first IF ");
					if(user_object.deleteWebLoginId(login_id))
					{
						userRepo.save(user_object);
						System.out.println("In second IF ");
						return new ResponseEntity<WebLogin>(HttpStatus.NO_CONTENT);
						
					}
				}
				System.out.println("Outside If ");
				return new ResponseEntity<WebLogin>(HttpStatus.NOT_FOUND);
				
			}
			
			//-------------------Methods for BANK ACCOUNT-----------------
			
			//Post Method
			@RequestMapping(value="/users/{user_id}/bankaccounts", method=RequestMethod.POST)
			@ResponseStatus(HttpStatus.CREATED)
			public ResponseEntity<BankAccount> ba(@PathVariable String user_id, @Valid @RequestBody BankAccount set)
			{
				BankAccount ba_object = new BankAccount();
				User user_object = userRepo.findById(user_id);
				if(user_object != null)
				{	if(ba_object.getAccount_name() == null || ba_object.getAccount_name() == "")
					{
						if(routingNoValidation(set))
						{   System.out.println("1");
							ba_object.setBankAccount(set);
							user_object.setBaList(ba_object);
							userRepo.save(user_object);
							return new ResponseEntity<BankAccount>(ba_object, HttpStatus.CREATED);
						}
					}
					else if(ba_object.getAccount_name()!=null)
					{
						System.out.println("in Else-If");
						ba_object.setBankAccount(set);
						user_object.setBaList(ba_object);
						userRepo.save(user_object);
						return new ResponseEntity<BankAccount>(ba_object, HttpStatus.CREATED);
					}
					
				}
				
				return new ResponseEntity<BankAccount>(ba_object, HttpStatus.NOT_FOUND);
}
			
			
			
			//Get Method
				@RequestMapping(value="/users/{user_id}/bankaccounts", method=RequestMethod.GET)
				public ArrayList<BankAccount> getba(@PathVariable String user_id)
				{
					
					User user_object = userRepo.findById(user_id);
					if(user_object != null)
					{
						user_object.getBaList();
						return user_object.getBaList();
					}
					
					
					return null;
				}
				
				//Delete Method
				@RequestMapping(value="/users/{user_id}/bankaccounts/{ba_id}", method=RequestMethod.DELETE)
				public ResponseEntity<BankAccount> deleteBa(@PathVariable String user_id, @Valid @PathVariable String ba_id)
				{
					
					User user_object = userRepo.findById(user_id);
					if(user_object != null)
					{
						System.out.println("In first IF ");
						if(user_object.deleteBankAccount(ba_id))
						{
							userRepo.save(user_object);
							System.out.println("In second IF ");
							return new ResponseEntity<BankAccount>(HttpStatus.NO_CONTENT);
						
						}
					}
					System.out.println("Outside If ");
					return new ResponseEntity<BankAccount>(HttpStatus.NOT_FOUND);
				}
		
				public boolean routingNoValidation(BankAccount bankDetail)
				{
					RestTemplate restTemplate = new RestTemplate();
					String url = "http://www.routingnumbers.info/api/data.json?rn=121000358";
					ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
					HttpStatus statusCode = entity.getStatusCode();
					System.out.println(entity.getBody());
					System.out.println(statusCode.toString());
					if(statusCode.toString().equals("200"))
					{
						JacksonJsonParser jsonParser = new JacksonJsonParser();
						Map<String,Object> resbody = jsonParser.parseMap(entity.getBody());
						System.out.println(resbody);
						bankDetail.setAccount_name(resbody.get("customer_name").toString());
						return true;
					}
					return false;
				}
				
				
				public String computeEntityTag(User user)
				{
					return user.getUpdatedDate();
				}

				@ExceptionHandler({MethodArgumentNotValidException.class, ServletRequestBindingException.class})
				@ResponseStatus(HttpStatus.BAD_REQUEST)
				public ModelMap handleException(MethodArgumentNotValidException error)
				{
					List<FieldError> errors = error.getBindingResult().getFieldErrors();
					ModelMap errorMap = new ModelMap();
					
					for(FieldError e : errors)
					{
						errorMap.addAttribute(e.getField(), e.getDefaultMessage());
					}
					return errorMap;
				}
				
}
