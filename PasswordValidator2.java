/* Author: Berk Sarioz
 * Eclipse Folder: StormPath
 * Date: 24 Jan 2014
 * 
 * PART 1: Check for Password Length
 * PART 2: Check if there is any rejected pattern in the password
 * PART 3: Check if the password has desired traits
 * PART 4: Password is good enough
 */
package password;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PasswordValidator2 {
	final static int MIN_LENGTH = 5;
	final static int MAX_LENGTH = 12;


	public static String validate (String password){
		
/////////////////////////////////// PART 1 \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\		
		if (password.length() < MIN_LENGTH) {
			return "Password too short! Minimum "+MIN_LENGTH+" characters.";
		} 
		if (password.length() > MAX_LENGTH) {
			return "Password too long! Maximum "+MAX_LENGTH+" characters.";
		}
		
/////////////////////////////////// PART 2 \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\		
		Pattern duplicate = Pattern.compile("(..+)\\1"); //rejected patterns
		Matcher m = duplicate.matcher(password);
		while (m.find()){
			return "Password has repeated sequence: \'"+m.group()+"\'";
		}
		
/////////////////////////////////// PART 3 \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\		
		ArrayList<String> enforce = new ArrayList<String>(Arrays.asList
			  ("(?=.*[0-9])", "(?=.*[a-z])", "[0-9a-z]")); // numbers & letters	  
		  	  
		String enforceStr = "";	  
		for (int i = 0; i< enforce.size(); i++){		  
			enforceStr += enforce.get(i);	  
		}	  
	  
		if (!password.matches(enforceStr)){	
			return "Only numbers or lowercase and at least one of each.";		
		}
		
/////////////////////////////////// PART 4 \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\		
		return "Password looking great!";	  
    }
}