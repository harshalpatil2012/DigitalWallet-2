package wallet;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.hibernate.validator.constraints.NotEmpty;


public class Card 
{

	 //Id Card Resources
	 
	 String card_id;
	 @NotEmpty (message="Card Name field cannot be empty. Please enter valid card name!!")
	 String card_name; //@NotNull: Card Name should not be left blank.
	 @NotEmpty (message="Card Number cannot be empty. Pleasse enter a vlaid card number!!")
	 String card_number;  //@NotNull: Card Number should not be left blank.
	 String expiration_date;  //Optional Field

	 public String uniqueCardId()
		{
		 	TimeZone timeZone = TimeZone.getTimeZone("UTC");
			DateFormat df = new SimpleDateFormat("yyMMddHHmmssa");
			df.setTimeZone(timeZone);
			String uId = "C-"+df.format(new Date()).toString();
			return uId;
		}
	 
	//Get CardId
	// @JsonIgnore
	public String getCard_id() {
		return card_id;
	}


	//Set CardId
	public void setCard_id(String card_id) {

		this.card_id=card_id;
		
	}


	//Get CardName
	public String getCard_name() {
		return card_name;
	}

	
	//Set CardName
	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}


	//Get CardNumber
	public String getCard_number() {
		return card_number;
	}


	//Set CardNumber
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}


	//Get ExpirationDate
	public String getExpiration_date() {
		return expiration_date;
	}


	//Set ExpirationDate
	public void setExpiration_date(String expiration_date) {
		this.expiration_date = expiration_date;
	}
	
	public void setCard(Card set)
	{
		this.card_id=this.uniqueCardId();
		this.card_name=set.card_name;
		this.card_number=set.card_number;
		this.expiration_date=set.expiration_date;
	}
	
}
