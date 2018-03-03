
package ie.gmit.sw.ai;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import ie.gmit.sw.ai.file.FileManager;
import ie.gmit.sw.ai.file.Parser;

/**
 * The Class Menu.
 */
// This class shows the menu options.
public class Menu {

	private static Scanner in = new Scanner(System.in);

	private static File pathToFile;

	private static String keyword, file;
	private static Parser parser = null;

	private static boolean keepRunningKey = true;
	private static boolean keepRunningEncDec = true;

	/** The user choice. */
	int userChoice;

	/**
	 * Start.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	public void start() throws IOException, InterruptedException {

		while (keepRunningKey) {

			keepRunningEncDec = true;

			showOptionsKey();

			while (keepRunningEncDec) {

				showOptionsEncDec();

				int selection = Integer.parseInt(in.next());

				if (selection == 1) {

					FileManager.ForceDecryptParsingFile(file);

					System.out.println("Break decrypt is Finish...");
					System.out.println("\nPress ENTER to return to Main Menu...");
					System.in.read();

					keepRunningEncDec = false;

				} else if (selection == 2) {

					System.out.println("\nEnter the keyword to Decrypt or EXIT leave.");
					keyword = in.next().toUpperCase();

					if (keyword.contains("EXIT")) {
						clearConsole();
						System.out.println("\nGood Bye..");
						System.exit(0);
					}

					FileManager.DecryptParsingFile(file, keyword);

					System.out.println("\nDecrypt is Finish...");
					System.out.println("\nPress ENTER to return to Main Menu...");
					System.in.read();

					keepRunningEncDec = false;

				} else if (selection == 3) {

					System.out.println("\nEnter the keyword to Encrypt or EXIT leave.");
					keyword = in.next().toUpperCase();

					if (keyword.contains("EXIT")) {
						clearConsole();
						System.out.println("\nGood Bye..");
						System.exit(0);
					}

					FileManager.EncryptParsingFile(file, keyword);

					System.out.println("\nEncrypt is Finish...");
					System.out.println("\nPress ENTER to return to Main Menu...");
					System.in.read();

					keepRunningEncDec = false;

				} else if (selection == 4) {
					keepRunningEncDec = false;
				} else {
					System.out.println("Invalid choice!");
				}

			}

		}

	}

	private void showOptionsKey() throws IOException, InterruptedException {
		clearConsole();
		showLogo();

		System.out.println("---------------------------------");
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

	private void showOptionsEncDec() {

		System.out.println("\n---------------------------------");
		System.out.println("Decrypt or Encrypt");
		System.out.println("---------------------------------");

		System.out.println("\n(1) Decrypt file (Break KEY).");
		System.out.println("(2) Decrypt file (With KEY).");
		System.out.println("(3) Encrypt file.");
		System.out.println("\n(4) Return to main menu.");
	}

	/**
	 * Clear console.
	 */
	public void clearConsole() {
		try {
			final String os = System.getProperty("os.name");

			if (os.contains("Windows")) {
				Runtime.getRuntime().exec("cls");
			} else {
				Runtime.getRuntime().exec("clear");
			}
		} catch (final Exception e) {

		}
	}

	/**
	 * Show logo.
	 */
	public void showLogo() {
		
		System.out.println("  _____   _______                 _____    __   __   _______  _______ ");
		System.out.println(" (_____) (_______)               (_____)  (__)_(__) (_______)(__ _ __)");
		System.out.println("(_)___(_)   (_)       ______    (_)  ___ (_) (_) (_)   (_)      (_)");   
		System.out.println("(_______)   (_)      (______)   (_) (___)(_) (_) (_)   (_)      (_) ");  
		System.out.println("(_)   (_) __(_)__               (_)___(_)(_)     (_) __(_)__    (_)");   
		System.out.println("(_)   (_)(_______)               (_____) (_)     (_)(_______)   (_)");   
		System.out.println("\nSimulated Annealing to Break a Playfair Cipher");
//		System.out.println(" by Alexander Souza - G00317835@gmit.ie");
		
	}

}
