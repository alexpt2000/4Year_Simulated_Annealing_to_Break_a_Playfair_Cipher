
package ie.gmit.sw.ai.cipher;

/*
 * This public class state the functionality of the PortaCipher. 
 * The following tableau is used by porta cipher to encrypt and 
 * decrypt a character and can be extended to accommodate extra symbols.
 * It uses a two dimensional array of char. A two-dimensional array is really
 * nothing more than an array of arrays. 
 * 
 */
public class PortaCipher {

	private static final char[][] tableau = {
			{ 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
					'I', 'J', 'K', 'L', 'M' },
			{ 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'N', 'M', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
					'H', 'I', 'J', 'K', 'L' },
			{ 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'N', 'O', 'L', 'M', 'A', 'B', 'C', 'D', 'E', 'F',
					'G', 'H', 'I', 'J', 'K' },
			{ 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'N', 'O', 'P', 'K', 'L', 'M', 'A', 'B', 'C', 'D', 'E',
					'F', 'G', 'H', 'I', 'J' },
			{ 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'N', 'O', 'P', 'Q', 'J', 'K', 'L', 'M', 'A', 'B', 'C', 'D',
					'E', 'F', 'G', 'H', 'I' },
			{ 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'N', 'O', 'P', 'Q', 'R', 'I', 'J', 'K', 'L', 'M', 'A', 'B', 'C',
					'D', 'E', 'F', 'G', 'H' },
			{ 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'N', 'O', 'P', 'Q', 'R', 'S', 'H', 'I', 'J', 'K', 'L', 'M', 'A', 'B',
					'C', 'D', 'E', 'F', 'G' },
			{ 'U', 'V', 'W', 'X', 'Y', 'Z', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'A',
					'B', 'C', 'D', 'E', 'F' },
			{ 'V', 'W', 'X', 'Y', 'Z', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
					'A', 'B', 'C', 'D', 'E' },
			{ 'W', 'X', 'Y', 'Z', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
					'M', 'A', 'B', 'C', 'D' },
			{ 'X', 'Y', 'Z', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
					'L', 'M', 'A', 'B', 'C' },
			{ 'Y', 'Z', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
					'K', 'L', 'M', 'A', 'B' },
			{ 'Z', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
					'J', 'K', 'L', 'M', 'A' } };
	
	

	/*
	 * This method uses the key word to encrypt plain text.
	 */
	public String encrypt(String keyword, String plainText) {

		StringBuilder temp = new StringBuilder(plainText.length());
		int indexKey;
		int indexPlain;

		// The for statement provides a compact way to iterate over a range of
		// values.

		for (int i = 0; i < plainText.length(); i++) {
			// The initialization expression (int i = 0; i < plainText.length();
			// i++) initializes the loop; it's executed once, as the loop begins
			indexKey = (keyword.charAt(i % keyword.length()) - 65) >> 1;
			// The position of the key characters in the tableau are in the same
			// position of the alphabet characters divided by 2.
			indexPlain = plainText.charAt(i) - 65;
			// Store the character found on the intersection of row with key.
			temp.append(PortaCipher.tableau[indexKey][indexPlain]);

		}
		// Returns the encrypted text line
		return temp.toString();
	}

	public String decrypt(String keyword, String decryptText) {

		StringBuilder temp = new StringBuilder(decryptText.length());
		int indexKey;
		int indexPlain;

		// The for statement provides a compact way to iterate over a range of
		// values.

		for (int i = 0; i < decryptText.length(); i++) {
			// The initialization expression (int i = 0; i < plainText.length();
			// i++) initializes the loop; it's executed once, as the loop begins
			indexKey = (keyword.charAt(i % keyword.length()) - 65) >> 1;
			// The position of the key characters in the tableau are in the same
			// position of the alphabet characters divided by 2.
			indexPlain = decryptText.charAt(i) - 65;
			// Store the character found on the intersection of row with key.
			temp.append(PortaCipher.tableau[indexKey][indexPlain]);

		}
		// Returns the encrypted text line
		return temp.toString();
	}

}