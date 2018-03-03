
package ie.gmit.sw.ai.file;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * The Class Parser.
 */
//This class parse text and uses BufferedReader to read what it is inside the file.
public class Parser {

	private BufferedReader reader;

	private Parser(BufferedReader reader) {
		this.reader = reader;
	}

	// This method tries bring back text from the source and store this text
	/**
	 * Parses the.
	 *
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	// into List<String>
	public String parse() throws IOException, InterruptedException {

		if (reader == null) {// If reader is null will create a message Empty
								// file
			new IllegalArgumentException("Empty file");
		}

		StringBuffer stringBuffer = new StringBuffer();
		String line = null;

		while ((line = reader.readLine()) != null) {

			stringBuffer.append(line.toUpperCase().replaceAll("[^A-Za-z0-9 ]", ""));
		}

		return new String(stringBuffer);
	}

	/**
	 * Gets the parser file.
	 *
	 * @param source the source
	 * @return the parser file
	 */
	// public static parser call back File from source.
	public static Parser getParserFile(File source) {

		if (!source.exists()) {
			throw new IllegalArgumentException("File not found!");
		}
		BufferedReader reader = null;

		try {
			reader = Files.newBufferedReader(Paths.get(source.getAbsolutePath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Return a parse object.
		return new Parser(reader);
	}

	/**
	 * Gets the parser URL.
	 *
	 * @param source the source
	 * @return the parser URL
	 */
	// public static parser call back a URLParser.
	public static Parser getParserURL(URL source) {

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new InputStreamReader(source.openStream(), StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new Parser(reader);
	}

	/**
	 * Convert stream to string.
	 *
	 * @param is the is
	 * @return the string
	 */
	public static String convertStreamToString(InputStream is) {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}

	private static Map<String, Double> getFrequencies(String path) throws FileNotFoundException {
		Map<String, Double> frequencies = new HashMap<String, Double>();
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(path));
		String allText = convertStreamToString(in);
		Map<String, Long> count = new HashMap<String, Long>();
		long totalCnt = 0;
		for (String current : allText.split("\n")) {
			String[] split = current.split(" ");
			long cnt = Long.parseLong(split[1]);
			count.put(split[0], cnt);
			totalCnt += cnt;
		}
		for (Map.Entry<String, Long> entry : count.entrySet()) {
			frequencies.put(entry.getKey(), (entry.getValue() + 0.0) / totalCnt);
		}
		return frequencies;
	}

	/**
	 * Gets the quadgram frequencies.
	 *
	 * @return the quadgram frequencies
	 * @throws FileNotFoundException the file not found exception
	 */
	public static Map<String, Double> getQuadgramFrequencies() throws FileNotFoundException {
		return getFrequencies("4grams.txt");
	}

}
