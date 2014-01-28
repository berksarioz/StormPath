package password;

import java.util.Scanner;

public class Test {
	  public static void main(String[] args) {

		  // Scanner used to imitate user input interaction
		  Scanner scanner = new Scanner(System.in);
		  System.out.print("Enter a password:\t");
		  String password = scanner.nextLine();

//		  String password = ""; un-comment and set password here efficiently
		  
		  System.out.println(PasswordValidator.validate(password));
	  }
}


