package wallet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class BankAccount 

{
	
	String ba_id;
	String account_name;
	@NotEmpty (message="Routing Number cannot be left blank. Please enter a valid Routing Number!! ")
	@Size(min=9, max=9, message="Routing Number should be equal to 9 digits")
	String routing_number; //@NotNull: Routing number should not be left blank.
	@NotEmpty (message="Account number cannot be left blank. Please enter a valid Account number!!")
	String account_number; //@NotNull: Account Number should not be left blank.
	
	public String getBa_id() {
		return ba_id;
	}

	public void setBa_id(String ba_id) {
		this.ba_id = ba_id;
	}
	
	

	 public String uniqueBankId()
		{
			
			TimeZone timeZone = TimeZone.getTimeZone("UTC");
			DateFormat df = new SimpleDateFormat("yyMMddHHmmssa");
			df.setTimeZone(timeZone);
			String uId = "BA-"+df.format(new Date()).toString();
			return uId;
		}
	
	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getRouting_number() {
		return routing_number;
	}

	public void setRouting_number(String routing_number) {
		this.routing_number = routing_number;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	
	public void setBankAccount(BankAccount set)
	{
		this.ba_id = this.uniqueBankId();
		this.account_name = set.account_name;
		this.routing_number = set.routing_number;
		this.account_number = set.account_number;
		
	}
}
