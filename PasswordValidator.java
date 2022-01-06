/* Author: Berk Sarioz
 * Eclipse Folder: StormPath
 * Date: 24 Jan 2014
 * 
 * PART 1: Check for password length (duplicate to make code easier to extend)
 * PART 2: Check if there is any rejected pattern in the password
 * PART 3: Check if the password has desired traits
 * PART 4: Password is good enough
 * PART 5: A8:0C:B1:F4:1F:9F:5C:9A:90
 * PART 6: C2:F0:98:1M:A4:4R:4Y:Z7:1X
 * PART 7 MSG: C9:F2:56:9M:A5:1R:8Y:Z8:8X
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
		
		String passwordLength = "{"+MIN_LENGTH+","+MAX_LENGTH+"}";	  
		enforce.add(passwordLength); //password length must be the last element
		  	  
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
