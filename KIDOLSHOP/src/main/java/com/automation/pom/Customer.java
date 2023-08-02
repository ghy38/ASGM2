package com.automation.pom;

import java.util.Random;

public class Customer {
	private String username, password;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	// Create a valid username with 5-10 characters, includes number & normal character
	public static String GenerateValidUsername() {
		String lowerCaseChars = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";
		String allChars = lowerCaseChars + numbers;
		
		Random random = new Random();
		int length = random.nextInt(6) + 5;
		
		StringBuilder randomString = new StringBuilder();
		
		randomString.append(lowerCaseChars.charAt(random.nextInt(lowerCaseChars.length())));
		
		randomString.append(numbers.charAt(random.nextInt(numbers.length())));
		
		for(int i = 0; i < length - 2; i++) {
			randomString.append(allChars.charAt(random.nextInt(allChars.length())));
		}
		
		return randomString.toString();
	}
	
	// Create a valid password with 8 - 16 characters
	public static String GenerateValidPassword() {
		String Chars = "abcdefghijklmnopqrstuvwxyz0123456789";
		
		Random random = new Random();
		int length = random.nextInt(9) + 8;
		
		StringBuilder randomString = new StringBuilder();
		
		for(int i = 0; i < length; i++) {
			randomString.append(Chars.charAt(random.nextInt(Chars.length())));			
		}
		
		return randomString.toString();
	}
}









