package com.coderscampus.assignment3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserService {
	public  Integer userAttempts = 0;
	public  String username = null;
	public  String password = null;
	public  String name = null;
	public  String[] userArray = null;
	public  Boolean userAuth = null;

	public void login() {
		if (userAttempts < 5) {
			try (Scanner scan = new Scanner(System.in)) {
				if (userAttempts == 0) {
					System.out.println("Enter Username: ");
				} else {
					System.out.println("You have attempts " + (5 - userAttempts) + " remaining!");
					System.out.println("Enter Username: ");
				}

				username = scan.nextLine().toLowerCase();
				System.out.println("Enter Password: ");
				password = scan.nextLine();
				getUsers();
				scan.close();
			}
		} else {
			failedAttempts();
		}

	}

	private void getUsers() {
		String line = null;
		User user = null;
		try (BufferedReader fileReader = new BufferedReader(new FileReader("data.txt"))) {

			while ((line = fileReader.readLine()) != null) {
				userArray = parseText(line);
				
				user = createUser(userArray[0].toString(), userArray[1].toString(), userArray[2].toString());

				String theUsername = user.getUsername();
				String thePassword = user.getPassword();

				if (username.equals(theUsername.toLowerCase()) && password.equals(thePassword)) {
					userAuth = true;
					break;
				}
			}
			fileReader.close();
			if (userAuth != null) {
				String theName = user.getName();
				name = Arrays.stream(theName.split("\\s+")).map(t -> t.substring(0, 1).toUpperCase() + t.substring(1))
						.collect(Collectors.joining(" "));
				System.out.println("Welcome: " + name);
			} else {
				userAttempts = userAttempts + 1;
				System.out.println("Invalid login, please try again:");
				login();
			}

		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

	public String[] parseText(String fileInput) {
		String[] userArray = fileInput.split(",");

		return userArray;
	}

	public User createUser(String username, String password, String name) {
		User user = new User(username, password, name);

		return user;

	}

	private void failedAttempts() {

		System.out.println("You have too many failed login attempts: Your account is locked!");
	}

}
