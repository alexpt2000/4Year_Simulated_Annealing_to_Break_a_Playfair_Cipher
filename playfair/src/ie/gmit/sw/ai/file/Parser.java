
package ie.gmit.sw.ai.file;
import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.net.URL;
import java.nio.charset.StandardCharsets;

//This class parse text and uses BufferedReader to read what it is inside the file.
public class Parser {

	private BufferedReader reader;

	private Parser(BufferedReader reader) {
		this.reader = reader;
	}

	// This method tries bring back text from the source and store this text
	// into List<String>
	public List<String> parse() {

		if (reader == null) {// If reader is null will create a message Empty
								// file
			new IllegalArgumentException("Empty file");
		}

		List<String> plainText = new ArrayList<>();
		// Read all lines replace all lower alphabet characters to Upper
		// Characters with no whitespace.
		
		//reader.lines().forEach((line) -> plainText.add(line.replaceAll("[^A-Za-z0-9 ]", "").toUpperCase()));
		
		reader.lines().forEach((line) -> plainText.add(line.toUpperCase().replaceAll("[^A-Za-z0-9 ]", "")));
		
		return new ArrayList<>(plainText);
	}

	// public static parser call back File from source.
	public static Parser getParser(File source) {

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

	// public static parser call back a URLParser.
	public static Parser getParser(URL source) {

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new InputStreamReader(source.openStream(), StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new Parser(reader);
	}

}
