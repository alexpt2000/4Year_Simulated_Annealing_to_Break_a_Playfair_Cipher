package ie.gmit.sw.ai.playfair;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class TextUtils.
 * 
 * Ref.
 * https://github.com/ugljesas/playfair/blob/master/playfair/src/utils/TextUtils.java
 * 
 *  * Adapted from:
 * https://github.com/ugljesas/playfair
 * 
 * @author Alexander Souza
 */
public class TextUtils {

	/**
	 * Prepare key.
	 *
	 * @param key the key
	 * @return the string
	 */
	public static String prepareKey(String key) {
		StringBuilder sb = new StringBuilder();
		key = key.toUpperCase();
		for (char c : key.toCharArray()) {
			if (Character.isUpperCase(c)) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * Prepare text.
	 *
	 * @param s the s
	 * @return the string
	 */
	public static String prepareText(String s) {
		StringBuilder sb = new StringBuilder();
		String ss = s.toUpperCase();
		char previous = ' ';
		boolean pair = false;
		for (char c : ss.toCharArray()) {
			if (Character.isUpperCase(c)) {
				if (c == PlayfairConstants.EQUAL_CHAR2)
					c = PlayfairConstants.EQUAL_CHAR1;
				if (c == previous && pair)
					sb.append(PlayfairConstants.INSERT_BETWEEN_SAME);
				else
					pair = !pair;
				sb.append(c);
				previous = c;
			}
		}
		if (sb.length() % 2 != 0)
			sb.append(PlayfairConstants.INSERT_BETWEEN_SAME);
		return sb.toString();
	}

	/**
	 * Gets the digraphs.
	 *
	 * @param s the s
	 * @return the digraphs
	 */
	public static List<String> getDigraphs(String s) {
		List<String> toReturn = new ArrayList<String>();
		String q = prepareText(s);
		for (int i = 0; i < q.length(); i += 2)
			toReturn.add(q.substring(i, i + 2));

		return toReturn;
	}

	/**
	 * Prints the pairs.
	 *
	 * @param s the s
	 */
	public static void printPairs(String s) {
		if (s.length() % 2 != 0)
			throw new IllegalStateException("Length of string needs to be even");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i += 2)
			sb.append(s.substring(i, i + 2) + " ");
		// System.out.println(sb.toString());
	}
}
