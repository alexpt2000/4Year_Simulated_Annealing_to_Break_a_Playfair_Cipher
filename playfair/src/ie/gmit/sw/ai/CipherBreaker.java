package ie.gmit.sw.ai;

import java.io.IOException;

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
 * Ref. 
 * https://github.com/ugljesas/playfair
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

		if (parameter.length == 0) {
			// Object Menu, start the menu.
			Menu menu = new Menu();// Instantiate Menu.
			menu.start();

			// Samplas URL docs
			// http://www.textfiles.com/etext/MODERN/zen10.txt
			// http://www.textfiles.com/etext/MODERN/hckr_hnd.txt
		}
		
		else if (parameter[0].equals("-help")) {
			System.out.println("\nUsage:  CipherBreaker COMMAND \n"
					+ "\nOptions:"
					+ "\n	-dp		Decrypt file (Playfair cipher)"
					+ "\n	-d		Decrypt file"
					+ "\n	-e		Encrypt file");
			
			//System.out.println("\nRun 'CipherBreaker -help' for more information.");
			System.out.println("\nRun 'java -cp ./playfair.jar ie.gmit.sw.ai.CipherBreaker -dp filename' for Decrypt file (Playfair cipher).");
			System.out.println("Run 'java -cp ./playfair.jar ie.gmit.sw.ai.CipherBreaker -d filename key' for Decrypt file.");
			System.out.println("Run 'java -cp ./playfair.jar ie.gmit.sw.ai.CipherBreaker -e filename key' for Encrypt file.");
		}
		
		
			
		try {
			if (parameter[0].equals("-dp") && !parameter[1].isEmpty()) {
			System.out.println("Funcionou.: " + parameter[0] + " - " + parameter[1]);
			}
			
			else {
				System.out.println("\nRun 'java -cp ./playfair.jar ie.gmit.sw.ai.CipherBreaker -help' for more information.");
			}
			
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			System.out.println("\nRun 'java -cp ./playfair.jar ie.gmit.sw.ai.CipherBreaker -help' for more information.");
			//e.printStackTrace();
		}
		
		
		

		
		


	}

}
