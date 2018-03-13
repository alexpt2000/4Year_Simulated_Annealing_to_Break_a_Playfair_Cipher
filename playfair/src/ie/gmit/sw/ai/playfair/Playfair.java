package ie.gmit.sw.ai.playfair;

import java.util.List;


/**
 * The Class Playfair.
 * 
 * Ref. 
 * http://rosettacode.org/wiki/Playfair_cipher#Java
 * http://crypto.interactive-maths.com/playfair-cipher.html
 * http://www.sanfoundry.com/java-program-decode-message-encoded-using-playfair-cipher/
 * 
 *  * Adapted from:
 * https://github.com/ugljesas/playfair
 * 
 * 
 * @author Alexander Souza
 * 
 */
public class Playfair {

	private PlayfairKey key;

	/**
	 * Instantiates a new playfair.
	 *
	 * @param key the key
	 * 
	 * 
	 */
	public Playfair(PlayfairKey key) {
		this.key = key;
	}

	/**
	 * Instantiates a new playfair.
	 *
	 * @param key the key
	 */
	public Playfair(String key) {
		this.key = new PlayfairKey(key);
	}

	/**
	 * Encrypt.
	 *
	 * @param in the in
	 * @return the string
	 */
	public String encrypt(String in) {
		StringBuilder toReturn = new StringBuilder();
		List<String> digraphs = TextUtils.getDigraphs(in);
		for (String digraph : digraphs) {
			toReturn.append(key.encrypt(digraph));
		}
		return toReturn.toString();
	}

	/**
	 * Decrypt.
	 *
	 * @param in the in
	 * @return the string
	 */
	public String decrypt(String in) {
		StringBuilder toReturn = new StringBuilder();
		List<String> digraphs = TextUtils.getDigraphs(in);
		for (String digraph : digraphs) {
			toReturn.append(key.decrypt(digraph));
		}
		return toReturn.toString();
	}

	/**
	 * Sets the key.
	 *
	 * @param key the new key
	 */
	public void setKey(PlayfairKey key) {
		this.key = key;
	}
}
