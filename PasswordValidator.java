/* Author: Berk Sarioz
 * Eclipse Folder: StormPath
 * Date: 24 Jan 2014
 * 
 * 
 */
package password;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PasswordValidator {
	final static int MIN_LENGTH = 5;
	final static int MAX_LENGTH = 12;
	
	public static String validate (String password){
		
		if (validateLength(password)!=null){
			return validateLength(password);
		}
		
		// Check if there is any repeated sequence in the password
		Pattern duplicate = Pattern.compile("(..+)\\1");
		Matcher m = duplicate.matcher(password);
		while (m.find()){
			return "Password has repeated sequence: \'"+m.group()+"\'";
		}
		
		// List of patterns to enforce(at least once) in the password	 
		ArrayList<String> enforce = new ArrayList<String>(Arrays.asList
			  ("(?=.*[0-9])", "(?=.*[a-z])", "[0-9a-z]")); // numbers & letters	  
		
		String passwordLength = "{"+MIN_LENGTH+","+MAX_LENGTH+"}";	  
		enforce.add(passwordLength); //password length must be the last element	  	  
	  	  
		String enforceStr = "";	  
		for (int i = 0; i< enforce.size(); i++){		  
			enforceStr += enforce.get(i);	  
		}	  
	  
		if (password.matches(enforceStr)){		  
			return "Password looking great!";	  
		} else {
			return "Only numbers or lowercase and at least one of each.";		
		}	
    }
	
	public static String validateLength (String password){
		// We can possibly check for each condition with an if statement
		if (password.length() < MIN_LENGTH) {
			return "Password too short! Minimum "+MIN_LENGTH+" characters.";
		} 
		if (password.length() > MAX_LENGTH) {
			return "Password too long! Maximum "+MAX_LENGTH+" characters.";
		}
		return null;
	}
}