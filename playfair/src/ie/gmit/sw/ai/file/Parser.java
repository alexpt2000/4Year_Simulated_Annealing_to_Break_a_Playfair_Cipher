
package ie.gmit.sw.ai.file;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
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
	public String parse() throws IOException, InterruptedException {

		if (reader == null) {// If reader is null will create a message Empty
								// file
			new IllegalArgumentException("Empty file");
		}

		//String plainText = "";
		// Read all lines replace all lower alphabet characters to Upper
		// Characters with no whitespace.
		
		//reader.lines().forEach((line) -> plainText.add(line.replaceAll("[^A-Za-z0-9 ]", "").toUpperCase()));
		
		//reader.lines().forEach((line) -> plainText.add(line.toUpperCase().replaceAll("[^A-Za-z0-9 ]", "")));
		
		
		  //BufferedReader bufferedReader = new BufferedReader(new FileReader("readme.txt"));
		  
		  StringBuffer stringBuffer = new StringBuffer();
		  String line = null;
		 
		  while((line = reader.readLine())!=null){
		 
			  stringBuffer.append(line.toUpperCase().replaceAll("[^A-Za-z0-9 ]", ""));
		  }
		   
//		  System.out.println(stringBuffer);
//		  TimeUnit.SECONDS.sleep(10);
		

		
		return new String(stringBuffer);
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

	public static Map<String, Double> getQuadgramFrequencies() throws FileNotFoundException {
		return getFrequencies("english_quadgrams.txt");
	}

}
