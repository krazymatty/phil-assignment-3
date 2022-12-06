package com.coderscampus.assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileService {
	public User[] readUsersFromFile() {
		String line = null;
		User user = null;
		String[] userArray = null;
		User[] users = new User[4];
		int userCtr = 0;

		try (BufferedReader fileReader = new BufferedReader(new FileReader("data.txt"))) {
			while ((line = fileReader.readLine()) != null) {
				userArray = line.split(",");
				String usernameArray = userArray[0].toString();
				String passwordArray = userArray[1].toString();
				String nameArray = userArray[2].toString();
				
				user = new User(usernameArray, passwordArray, nameArray);
				users[userCtr] = user;
				userCtr++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
}
