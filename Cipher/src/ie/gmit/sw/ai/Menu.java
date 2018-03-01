
package ie.gmit.sw.ai;

import java.io.*;
import java.util.*;

// This class shows the menu options.
public class Menu {

	Scanner in = new Scanner(System.in);

	File pathToFile;

	// Variables Global
	List<String> plainText;
	List<String> encryptText;

	private static String keyword, file;
	Parser parser = null;

	private static boolean keepRunningKey = true;
	private static boolean keepRunningEncDec = true;

	int userChoice;

	// The public void start calls the menu option methods.
	public void start() throws IOException {
		// The while statement will executes the block of
		// statements(ClearConsoleand showOptionsKey
		// while keepRunningEncDec condition is true.
		while (keepRunningKey) {

			keepRunningEncDec = true;

			clearConsole();

			showOptionsKey();
			// The while statement will executes the block of
			// statements(showOptionsEncDec
			// while keepRunningEncDec condition is true.
			while (keepRunningEncDec) {

				showOptionsEncDec();

				int selection = Integer.parseInt(in.next());
				// Using if statement to repeat instruction execution
				// if user select ==1 (True) the method will execute encrypt.
				if (selection == 1) {

					FileManager.EncriptParsingFile(file, keyword);

					System.out.println("\nEncript is Finish...");
					System.out.println("\nPress ENTER to return to Main Menu...");
					System.in.read();

					keepRunningEncDec = false;

				} else if (selection == 2) { // if user select ==2 the method will execute Decrypt.

					FileManager.DecriptParsingFile(file, keyword);

					System.out.println("\nDecrypt is Finish...");
					System.out.println("\nPress ENTER to return to Main Menu...");
					System.in.read();

					keepRunningEncDec = false;

				} else if (selection == 3) { // Quit // If user select 3 the method will execute Quit.
					keepRunningEncDec = false;
				} else { // Invalid input
					System.out.println("Invalid choice!");
				}

			}

		}

	}

	// private void call showOptionsKey
	private void showOptionsKey() throws IOException {
		System.out.println("\n#################################################");
		System.out.println("#      Porta Cipher - Keyword and File Name     #");
		System.out.println("#################################################");

		System.out.println("\nEnter the keyword or EXIT leave.");
		keyword = in.next().toUpperCase();
		// Checks if keyword is EXIT will clear console and will print good bye.
		if (keyword.contains("EXIT")) {
			clearConsole();
			System.out.println("\nGood Bye..");
			System.exit(0);
		}

		System.out.println("\n---------------------------------");
		System.out.println("    Files Availables");
		System.out.println("---------------------------------");

		String menu = FileManager.listFilesScreen();

		System.out.println(menu);

		System.out.println("---------------------------------\n");

		System.out.println("Enter the file name or URL.");
		file = in.next();
		
		keepRunningEncDec = FileManager.validateFile(file);
		
		if(!keepRunningEncDec) {
			System.out.println("\nFile/URL not found, try again!");
		}
		
	}

	// Private void show encrypt and decrypt options.
	private void showOptionsEncDec() {
		System.out.println("\n#################################################");
		System.out.println("#       Porta Cipher - Encript or Decript       #");
		System.out.println("#################################################");

		System.out.println("\n(1) Encript.");
		System.out.println("(2) Decript.");
		System.out.println("(3) Return to main menu.");

	}

	// method to clear console
	public void clearConsole() {
		try {
			final String os = System.getProperty("os.name");

			if (os.contains("Windows")) {
				Runtime.getRuntime().exec("cls");
			} else {
				Runtime.getRuntime().exec("clear");
			}
		} catch (final Exception e) {
			// Handle any exceptions.
		}
	}

}
