package com.phase1.virtualkey;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Virtual$key {
	
	static String DIRECTORY;
	File folder_name;

	public Virtual$key() {
		DIRECTORY = System.getProperty("user.dir");
		folder_name = new File(DIRECTORY + "/files");
		if (!folder_name.exists())
			folder_name.mkdirs();
		System.out.println("DIRECTORY : " + folder_name.getAbsolutePath());
	}

	private static final String WELCOME = "\n Welcome to the Training" +
			"\n Mayank Gupta \n";

	private static final String MAIN_MENU = "\n MAIN MENU - Select any of the following: \n" +
			"1 -> List files in directory\n" +
			"2 -> Add, Delete or Search\n" +
			"3 -> Exit Program";

	private static final String SECONDARY_MENU = " \n Select the option from below: \n" +
			" a -> File Add\n" +
			" b -> File Delete\n" +
			" c -> File Search\n" +
			" d -> Return to Menu";

// Primary Menu
void showPrimaryMenu() {
	System.out.println(MAIN_MENU);
	try {
		Scanner sc = new Scanner(System.in);
		int option = sc.nextInt();

		switch (option) {
		case 1: {
			showFiles();
			showPrimaryMenu();
		}

		case 2: {
			showSecondaryMenu();
		}
		case 3: {
			System.out.println("Thank You");
			System.exit(0);
		}
		default:
		showPrimaryMenu();
	}
}

	catch (Exception e) {
		System.out.println("Please enter the option");
		showPrimaryMenu();
	}
}

// Secondary Menu
void showSecondaryMenu() {
		System.out.println(SECONDARY_MENU);
		try {
			Scanner sc = new Scanner(System.in);
			char[] input = sc.nextLine().toLowerCase().trim().toCharArray();
			char option = input[0];
			
			switch (option) {
				case 'a': {
					System.out.print("To Add a file...Please Enter a File Name: ");
					String filename = sc.next().trim().toLowerCase();
					addFile(filename);
					break;
				}
			
				case 'b': {
					System.out.print("To Delete a file...Please Enter a FileName : ");
					String filename = sc.next().trim();
					deleteFile(filename);
					break;
				}
			
				case 'c': {
					System.out.print("To Search a file...Please Enter a FileName : ");
					String filename = sc.next().trim();
					searchFile(filename);
					break;
				}
			
				case 'd': {
					System.out.println("Return to Menu");
					showPrimaryMenu();
					break;
				}
			
				default:
					System.out.println("Please select a option from a, b, c,d");
				}
			
				showSecondaryMenu();
			}
		
			catch (Exception e) {
				System.out.println("Please select a option from a, b, c, d");
				showSecondaryMenu();
			}
		}

// Show Files
	void showFiles() {
		if (folder_name.list().length == 0)
			System.out.println("The folder you chosen is empty");
		else {
			String[] list = folder_name.list();
			System.out.println("The files in " + folder_name + " are :");
			Arrays.sort(list);
			for (String str : list) {
				System.out.println(str);
			}
		}
	}
	
// Add Files
	void addFile(String filename) throws IOException {
		File filepath = new File(folder_name + "/" + filename);
		String[] list = folder_name.list();
		for (String file : list) {
			if (filename.equalsIgnoreCase(file)) {
				System.out.println("File " + filename + " already exists at " + folder_name);
				return;
			}
		}
		
		filepath.createNewFile();
		System.out.println("File " + filename + " added to " + folder_name);
	}
	
// Delete Files
	void deleteFile(String filename) {
		File filepath = new File(folder_name + "/" + filename);
		String[] list = folder_name.list();
		for (String file : list) {
			if (filename.equals(file) && filepath.delete()) {
				System.out.println("File " + filename + " deleted from " +
						folder_name);
				return;
			}
		}
		System.out.println("Delete Operation failed. FILE NOT FOUND");
	}
	
// Search Files
	void searchFile(String filename) {
		String[] list = folder_name.list();
		for (String file : list) {
			if (filename.equals(file)) {
				System.out.println("FOUND : File " + filename + " exists at " + folder_name);
				return;
			}
		}
		System.out.println("File Not found (FNF)");
	}
	public static void main(String[] args) {
		System.out.println(WELCOME);
		Virtual$key menu = new Virtual$key();
		menu.showPrimaryMenu();
	}
}