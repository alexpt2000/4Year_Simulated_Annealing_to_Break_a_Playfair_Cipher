package ie.gmit.sw.ai.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import ie.gmit.sw.ai.cipher.PortaCipher;
import ie.gmit.sw.ai.playfair.Playfair;
import ie.gmit.sw.ai.playfair.PlayfairKey;
import ie.gmit.sw.ai.playfair.SimulatedAnnealing;

public class FileManager {

	private static Parser parser = null;
	// Variables Global
	private static String plainText;
	private static String encryptText;

	private static String path;

	// private void list files on the screen
	public static String listFilesScreen() {

		String menu = "";

		File folder = new File("./");// instantiate object
		File[] listOfFiles = folder.listFiles();// instantiate object

		for (File file : listOfFiles) {
			if (file.isFile()) {// verify if new file is a file and will print
								// name of file.
				menu += "\n" + file.getName();
			}
		}

		return menu;
	}

	// private void calls decriptParsingFile.
	public static void ForceDecriptParsingFile(String file) throws IOException, InterruptedException {
		parser = Parser.getParser(new File(file));// give the name of the file
													// in its directory
		encryptText = parser.parse();

		SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing(50, 1, 5000);

		PlayfairKey key = simulatedAnnealing.findKey(encryptText);
		Playfair playFair = new Playfair(key);
		String plainText = playFair.decrypt(encryptText);

		System.out.println(plainText);

		// File Name
		String fileName = "decryptedMSG.txt";

		// Set Path
		path = "./" + fileName;

		try {
			new FileOutputStream(fileName, false).close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try (PrintWriter pw = new PrintWriter(path)) {
			// Print into a file
			pw.println(plainText);
			System.out.println("\nDecrypt saved on file name: " + fileName);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// private void calls decriptParsingFile.
	public static void DecriptParsingFile(String file, String keyword) throws IOException, InterruptedException {
		parser = Parser.getParser(new File(file));// give the name of the file
													// in its directory
		encryptText = parser.parse();

		String fileName = "decryptedMSG.txt";
		path = "./" + fileName;

		try {
			new FileOutputStream(fileName, false).close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try (PrintWriter pw = new PrintWriter(path)) {

			PortaCipher porta = new PortaCipher();// Instantiate PortaCipher.
			// encryptText.forEach((line) -> pw.println(porta.decrypt(keyword, line)));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// private void calls Encrypt parsing file.
	public static void EncriptParsingFile(String file, String keyword) throws IOException, InterruptedException {

		if (file.contains("http://") || file.contains("https://")) {

			URL connection = new URL(file);

			// open the URL stream, wrap it an a few "readers"
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.openStream()));

			parser = Parser.getParser(new URL(file));

		} else {
			parser = Parser.getParser(new File(file));
		}

		plainText = parser.parse();

		// Declare variable
		String fileName = "encryptedMSG.txt";
		path = "./" + fileName;

		try {
			new FileOutputStream(fileName, false).close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try (PrintWriter pw = new PrintWriter(path)) {
			PortaCipher porta = new PortaCipher();// instantiate PortaCipher

			// TODO - Change the loop
			// plainText.forEach((line) -> pw.println(porta.encrypt(keyword, line)));//
			// print encrypted text plain for
			// each.

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

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
