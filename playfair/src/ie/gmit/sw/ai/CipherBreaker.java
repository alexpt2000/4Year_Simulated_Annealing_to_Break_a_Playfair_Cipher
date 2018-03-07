package ie.gmit.sw.ai;

import java.io.IOException;

import ie.gmit.sw.ai.file.FileManager;

/**
 * The Class CipherBreaker.
 * 
 * Assuming that the cryptographic algorithm is known, a common approach for
 * breaking a cipher is to generate a large number of keys, decrypt a
 * cipher-text with each key and then examine the resultant plain-text. If the
 * text looks similar to English, then the chances are that the key is a good
 * one. The similarity of a given piece of text to English can be computed by
 * breaking the text into fixed-length substrings, called n-grams, and then
 * comparing each substring to an existing map of n-grams and their frequency.
 * This process does not guarantee that the outputted answer will be the correct
 * plain-text, but can give a good approximation that may well be the right
 * answer.
 * 
 * 
 * Ref. https://github.com/ugljesas/playfair
 * 
 * 
 * @author Alexander Souza
 */
public class CipherBreaker {

	/**
	 * The main method.
	 * 
	 * Ref.
	 * https://stackoverflow.com/questions/890966/what-is-string-args-parameter-in-main-method-java
	 *
	 * @param args
	 *            the arguments
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static void main(String[] parameter) throws IOException, InterruptedException {

		// Samples URL docs
		// http://www.textfiles.com/etext/MODERN/zen10.txt
		// http://www.textfiles.com/etext/MODERN/hckr_hnd.txt
		
		try {
			if (parameter.length == 0) {
				// Object Menu, start the menu.
				Menu menu = new Menu();// Instantiate Menu.
				menu.start();
			}

			/*
			 * Takes parameter:
			 *
			 * parameter[0] = COMMAND 
			 * parameter[1] = filename 
			 * parameter[2] = key
			 *
			 */
			else if (parameter[0].toUpperCase().equals("-HELP")) {
				System.out.println("\nUsage:  CipherBreaker COMMAND \n"
						+ "\nOptions:"
						+ "\n	-dp		Decrypt file/URL (Playfair cipher)"
						+ "\n	-d		Decrypt file/URL"
						+ "\n	-e		Encrypt file/URL");
				
				System.out.println("\nDecrypt file (Playfair cipher).");
				System.out.println("	java -cp ./playfair.jar ie.gmit.sw.ai.CipherBreaker -dp <FILENAME>");
				
				System.out.println("\nDecrypt file.");
				System.out.println("	java -cp ./playfair.jar ie.gmit.sw.ai.CipherBreaker -d <FILENAME> <KEY>");
				
				System.out.println("\nEncrypt file.");
				System.out.println("	java -cp ./playfair.jar ie.gmit.sw.ai.CipherBreaker -e <FILENAME> <KEY>");
			}

			// Decrypt with Playfair cipher
			else if (parameter[0].toUpperCase().equals("-DP")) {

				if (!FileManager.validateFile(parameter[1])) {
					System.out.println("\nFile/URL not found, try again!");
				} else {
					FileManager.ForceDecryptParsingFile(parameter[1]);
				}

			}

			// Decrypt the file with KEY
			else if (parameter[0].toUpperCase().equals("-D")) {

				if (!FileManager.validateFile(parameter[1])) {
					System.out.println("\nFile/URL not found, try again!");
				} else {
					FileManager.DecryptParsingFile(parameter[1], parameter[2]);
				}

			}

			// Encrypt the file with KEY
			else if (parameter[0].toUpperCase().equals("-E")) {
				if (!FileManager.validateFile(parameter[1])) {
					System.out.println("\nFile/URL not found, try again!");
				} else {
					FileManager.EncryptParsingFile(parameter[1], parameter[2]);
				}

			}

			else {
				System.out.println("\nRun 'java -cp ./playfair.jar ie.gmit.sw.ai.CipherBreaker -help' for more information.");
			}

		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			System.out.println("\nRun 'java -cp ./playfair.jar ie.gmit.sw.ai.CipherBreaker -help' for more information.");
		}

	}

}
