package wallet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

public class WebLogin 
{
	

	String login_id;
	@NotEmpty (message="Please enter the valid URL!!")
	@URL
	String url;  //@NotNull: URL should not be left blank.
	@NotEmpty (message="Login field cannot be left blank. Please enter the valid Login!!")
	String login; //@NotNull: Login should not be left blank.
	@NotEmpty (message="Password cannot be left blank. Please enter the valid Password!!")
	String password; //@NotNull: Password should not be left blank.
	
	public String uniqueLoginId()
	{
		
		TimeZone timeZone = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyMMddHHmmssa");
		df.setTimeZone(timeZone);
		String uId = "L-"+df.format(new Date()).toString();
		return uId;
	}
	
	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setWebLogin(WebLogin set)
	{
		this.login_id=this.uniqueLoginId();
		this.url=set.url;
		this.login=set.login;
		this.password=set.password;
	}

}
