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
	 * @param args
	 *            the arguments
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static void main(String[] args) throws IOException, InterruptedException {

		// Object Menu, start the menu.
		Menu menu = new Menu();// Instantiate Menu.
		menu.start();

		// Samplas URL docs
		// http://www.textfiles.com/etext/MODERN/zen10.txt
		// http://www.textfiles.com/etext/MODERN/hckr_hnd.txt

	}

}
