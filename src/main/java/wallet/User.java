package wallet;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User 
{
	 String id;
	 
	 @NotEmpty (message = "Name field cannot be empty. Please enter a name")
	 String name; 
	 @Email (message= "Enter valid email id")
	 @NotEmpty (message = "Email field cannot be empty. Not a well-formed email address. Please enter the email id!!")  
	 String email;  //@NotNull: Email should not be left blank.
	 				//@Email: email address should be in the correct format. For e.g: abc@xyz.com
	 @NotNull (message = "Password Field cannot be empty. Please enter the password")
	 String password;
	 String generatedDate;
	 String updatedDate;
	
	
	ArrayList<Card> cardList = new ArrayList<Card>(); //Array List for storing cards of a particular user.
	ArrayList<WebLogin> webList = new ArrayList<WebLogin>(); //Array List for storing web login of a particular user.
	ArrayList<BankAccount> baList = new ArrayList<BankAccount>(); //Array List for storing web login of a particular user.
	

	
		//Get WebLogin
		@JsonIgnore
		public ArrayList<WebLogin> getWebList() {
			return webList;
		}

		
		//Set WebLogin
		public void setWebList(WebLogin webList) {
			this.webList.add(webList);
		}

		//Get Bank Account
		@JsonIgnore
		public ArrayList<BankAccount> getBaList() {
			return baList;
		}


		//Set Bank Account
		public void setBaList(BankAccount baList) {
			this.baList.add(baList);
		}


		@JsonIgnore
		public ArrayList<Card> getCardList() {
			return cardList;
		}



		public void setCardList(Card cardList) {
			this.cardList.add(cardList);
		}


	//Get UserId
	public String getId() {
		return id;
	}

	//Get UserName
	public String getName() {
		return name;
	}



	//Set UserName
	public void setName(String name) {
		this.name = name;
	}



	//Get UserEmail
	@NotNull
	public String getEmail() {
		return email;
	}



	//Set UserEmail
	public void setEmail(String email) {
		this.email = email;
	}

	//Get UserPassword
	public String getPassword() {
		return password;
	}

	//Set UserPassword
	public void setPassword(String password) {
		this.password = password;
	}

	//Get Generated Date
	public String getGeneratedDate() {
		return generatedDate;
	}



	//Set Generated Date
	public void setGeneratedDate(String generatedDate) {
		this.generatedDate = generatedDate;
	}



	//Get Updated Date
	public String getUpdatedDate() {
		return updatedDate;
	}


	//Set Updated Date
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}


	public void setUser(User set)
	{
		this.id = this.uniqueUserId();
		this.name=set.name;
		this.email=set.email;
		this.password=set.password;
		this.generatedDate=date();
		this.updatedDate=date();
	}

	
	public void putUser(User set, String id, String str)
	{
		this.id = id;
		this.name=set.name;
		this.email=set.email;
		this.password=set.password;
		this.generatedDate=str;
		this.updatedDate=date();
	}
	
	public String uniqueUserId()
	{
	 	TimeZone timeZone = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("mmassS");
		df.setTimeZone(timeZone);
		String str1= "100"+""+df.format(new Date()).toString();
		String str2 = "U-";
		String uId=str2+str1;
		return uId;
	}
	
	
	public String date()
	{
		TimeZone timeZone = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		df.setTimeZone(timeZone);
		return(df.format(new Date()));
	}
	
	
	
	public boolean deleteIdCard(String card_id)
	{
		for(Card c : cardList)
		{
			if(c.getCard_id().equals(card_id))
			{
				cardList.remove(c);
				this.updatedDate=date();
				return true;
			}
		}
		
		return false;
		
	}
	
	public boolean deleteWebLoginId(String login_id)
	{
		for(WebLogin w : webList)
		{
			if(w.getLogin_id().equals(login_id))
			{
				webList.remove(w);
				this.updatedDate=date();
				return true;
			}
		}
		return false;
	}
	
	public boolean deleteBankAccount(String ba_id)
	{
		for(BankAccount b : baList)
		{
			if(b.getBa_id().equals(ba_id))
			{
				baList.remove(b);
				this.updatedDate=date();
				return true;
			}
		}
		
		return false;
		
	}
	
}		





