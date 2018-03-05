package ie.gmit.sw.ai.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import ie.gmit.sw.ai.playfair.Playfair;
import ie.gmit.sw.ai.playfair.PlayfairKey;
import ie.gmit.sw.ai.playfair.SimulatedAnnealing;

/**
 * The Class FileManager.
 */
public class FileManager {

	private static Parser parser = null;
	private static String plainText;
	private static String encryptText;
	private static String path;

	/**
	 * List files screen.
	 *
	 * @return the string
	 */
	public static String listFilesScreen() {

		String menu = "";

		File folder = new File("./");
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			if (file.isFile()) {

				menu += "\n" + file.getName();
			}
		}

		return menu;
	}

	/**
	 * Force decrypt parsing file.
	 *
	 * @param file the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	public static void ForceDecryptParsingFile(String file) throws IOException, InterruptedException {
		parser = Parser.getParserFile(new File(file));
		encryptText = parser.parse();

		SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing(50, 1, 5000);

		PlayfairKey key = simulatedAnnealing.findKey(encryptText.substring(0, 600));
		Playfair playFair = new Playfair(key);
		String plainText = playFair.decrypt(encryptText);

		System.out.println(
				"\n------------------------------------------------------------------------------------------");
		System.out.println("Sample output result.: " + plainText.substring(0, 60) + "...");
		System.out
				.println("------------------------------------------------------------------------------------------");

		String fileName = "_decryptedMSG.txt";
		path = "./" + fileName;

		try {
			new FileOutputStream(fileName, false).close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try (PrintWriter pw = new PrintWriter(path)) {
			// Print into a file
			pw.println(plainText);
			System.out.println("Decrypt saved on file name: " + fileName);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Decrypt parsing file.
	 *
	 * @param file the file
	 * @param keyword the keyword
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	public static void DecryptParsingFile(String file, String keyword) throws IOException, InterruptedException {
		parser = Parser.getParserFile(new File(file));
		encryptText = parser.parse();

		Playfair playfair = new Playfair(keyword);

		String fileName = "_decryptedMSG.txt";
		path = "./" + fileName;

		try {
			new FileOutputStream(fileName, false).close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try (PrintWriter pw = new PrintWriter(path)) {

			pw.println(playfair.encrypt(encryptText));
			System.out.println("Decrypt saved on file name: " + fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Encrypt parsing file.
	 *
	 * @param file the file
	 * @param keyword the keyword
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	public static void EncryptParsingFile(String file, String keyword) throws IOException, InterruptedException {

		if (file.contains("http://") || file.contains("https://")) {

			// URL connection = new URL(file);

			// BufferedReader br = new BufferedReader(new
			// InputStreamReader(connection.openStream()));

			parser = Parser.getParserURL(new URL(file));

		} else {
			parser = Parser.getParserFile(new File(file));
		}

		plainText = parser.parse();

		Playfair playfair = new Playfair(keyword);

		// Declare variable
		String fileName = "_encryptedMSG.txt";
		path = "./" + fileName;

		try {
			new FileOutputStream(fileName, false).close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try (PrintWriter pw = new PrintWriter(path)) {
			pw.println(playfair.encrypt(plainText));
			System.out.println("Encrypt saved on file name: " + fileName);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Validate file.
	 *
	 * @param file the file
	 * @return the boolean
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static Boolean validateFile(String file) throws IOException {

		boolean keepRunningEncDec = true;
		// Checks if the file is a URL and if is need contain http:// or https://. If
		// contain will execute the try.
		if (file.contains("http://") || file.contains("https://")) {
			// I use this URL to test if the program it's working
			// http://www.textfiles.com/etext/MODERN/zen10.txt
			// http://www.textfiles.com/etext/MODERN/hckr_hnd.txt

			try {// Instantiate the variable URL.
				URL url = new URL(file);
				HttpURLConnection huc = (HttpURLConnection) url.openConnection();
				int responseCode = huc.getResponseCode();

				if (responseCode != 404) {// Verify if the link exist or not
				} else {
					keepRunningEncDec = false;
				}

			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

		} else {

			// If the path file doesn't exist will print File not found, try
			// again!"
			try {
				File pathToFile = new File(file);
				if (!pathToFile.exists()) {
					keepRunningEncDec = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return keepRunningEncDec;

	}

}
