package com.coderscampus.assignment3;

import java.io.FileNotFoundException;
import java.io.IOException;

public class UserLoginApp {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		
		UserService userService = new UserService();
		userService.initialLogin();

	}

}
