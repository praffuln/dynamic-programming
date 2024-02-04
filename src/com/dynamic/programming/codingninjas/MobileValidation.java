package com.dynamic.programming.codingninjas;

import java.util.regex.Pattern;

class NonDigitCharacterException extends Exception{
	NonDigitCharacterException(){
		super("Mobile should only contain digital characters.");
	}
}

class IncorrectLengthException extends Exception{
	IncorrectLengthException(){
		super("The length of mobile should be 10.");
	}
}

class IncorrectPrefixException extends Exception{
	IncorrectPrefixException(){
		super("Mobile should begin with \"04\".");
	}
}

public class MobileValidation {
	public static void validate(String mobile) throws IncorrectLengthException, NonDigitCharacterException, IncorrectPrefixException {
		//START
		//...Your code goes here...
		//FINISH
		if(mobile ==null || mobile.isEmpty() || mobile.length() < 10)
		    throw new IncorrectLengthException();
		
		if(!mobile.startsWith("04")) 
			 throw new IncorrectPrefixException();
		
		if(!Pattern.compile("[0-9]*").matcher(mobile).matches()) 
			 throw new NonDigitCharacterException();
		
	}
	public static void main(String[] args) {
		String[] mobileNumbers = {"1234567a", "123456789a", "1234567890"};
		for(String mobile: mobileNumbers) {
			try {
				validate(mobile);
			}catch(Exception e) {
				System.err.println(e.getMessage());
			}
		}

	}

}
