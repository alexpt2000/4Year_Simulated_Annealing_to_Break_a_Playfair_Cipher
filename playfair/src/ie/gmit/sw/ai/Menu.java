
package ie.gmit.sw.ai;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import ie.gmit.sw.ai.file.FileManager;
import ie.gmit.sw.ai.file.Parser;

// This class shows the menu options.
public class Menu {

	Scanner in = new Scanner(System.in);

	File pathToFile;

	// Variables Global
	// private List<String> plainText;
	// private List<String> encryptText;

	private static String keyword, file;
	Parser parser = null;

	private static boolean keepRunningKey = true;
	private static boolean keepRunningEncDec = true;

	int userChoice;

	// The public void start calls the menu option methods.
	public void start() throws IOException, InterruptedException {
		// The while statement will executes the block of
		// statements(ClearConsoleand showOptionsKey
		// while keepRunningEncDec condition is true.
		while (keepRunningKey) {

			keepRunningEncDec = true;

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

					FileManager.ForceDecriptParsingFile(file);

					System.out.println("\nBreak decrypt is Finish...");
					System.out.println("\nPress ENTER to return to Main Menu...");
					System.in.read();

					keepRunningEncDec = false;

				} else if (selection == 2) {

					System.out.println("\nEnter the keyword  to Encript or EXIT leave.");
					keyword = in.next().toUpperCase();
					// Checks if keyword is EXIT will clear console and will print good bye.
					if (keyword.contains("EXIT")) {
						clearConsole();
						System.out.println("\nGood Bye..");
						System.exit(0);
					}

					FileManager.EncriptParsingFile(file, keyword);

					System.out.println("\nEncript is Finish...");
					System.out.println("\nPress ENTER to return to Main Menu...");
					System.in.read();

					keepRunningEncDec = false;

				} else if (selection == 3) { // if user select ==2 the method will execute Decrypt.

					System.out.println("\nEnter the keyword  to Decript or EXIT leave.");
					keyword = in.next().toUpperCase();
					// Checks if keyword is EXIT will clear console and will print good bye.
					if (keyword.contains("EXIT")) {
						clearConsole();
						System.out.println("\nGood Bye..");
						System.exit(0);
					}

					FileManager.DecriptParsingFile(file, keyword);

					System.out.println("\nDecrypt is Finish...");
					System.out.println("\nPress ENTER to return to Main Menu...");
					System.in.read();

					keepRunningEncDec = false;

				} else if (selection == 4) { // Quit // If user select 3 the method will execute Quit.
					keepRunningEncDec = false;
				} else { // Invalid input
					System.out.println("Invalid choice!");
				}

			}

		}

	}

	// private void call showOptionsKey
	private void showOptionsKey() throws IOException, InterruptedException {
		clearConsole();
		showLogo();

		System.out.println("\n---------------------------------");
		System.out.println("    Files Availables");
		System.out.println("---------------------------------");

		String menu = FileManager.listFilesScreen();

		System.out.println(menu);

		System.out.println("---------------------------------\n");

		System.out.println("Enter the file name or URL (EXIT leave).");
		file = in.next();

		if (file.contains("EXIT")) {
			clearConsole();
			System.out.println("\nGood Bye..");
			TimeUnit.SECONDS.sleep(2);
			System.exit(0);
		}

		keepRunningEncDec = FileManager.validateFile(file);

		if (!keepRunningEncDec) {
			System.out.println("\nFile/URL not found, try again!");
			TimeUnit.SECONDS.sleep(2);
		}

	}

	// Private void show encrypt and decrypt options.
	private void showOptionsEncDec() {

		System.out.println("\n---------------------------------");
		System.out.println("Decript or Encript");
		System.out.println("---------------------------------");

		System.out.println("\n(1) Decript file (Break KEY).");
		System.out.println("(2) Decript file (with KEY).");
		System.out.println("(3) Encript file.");
		System.out.println("(4) Return to main menu.");
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

	public void showLogo() {
		System.out.println("\n ---------------------------------------------------");
		System.out.println("|   Simulated Annealing to Break a Playfair Cipher  |");
		System.out.println(" ---------------------------------------------------");

	}

}
