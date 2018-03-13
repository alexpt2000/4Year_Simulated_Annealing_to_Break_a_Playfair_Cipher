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
public class FileManager implements AccessFileManager {

	private static Parser parser = null;
	private static String plainText;
	private static String encryptText;
	private static String path;

	/**
	 * List files screen.
	 *
	 * @return the string
	 */
	@Override
	public String listFilesScreen() {

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
	@Override
	public void ForceDecryptParsingFile(String file) throws IOException, InterruptedException {
		
		// Record the start time
		long startTime = System.currentTimeMillis();
		
		parser = Parser.getParserFile(new File(file));
		encryptText = parser.parse();

		SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing(50, 1, 15000);

		PlayfairKey key = simulatedAnnealing.findKey(encryptText.substring(0, 500));
		Playfair playFair = new Playfair(key);
		String plainText = playFair.decrypt(encryptText);

		// print some sample
		System.out.println("\n------------------------------------------------------------------------------------------");
		System.out.println("Sample output result.: " + plainText.substring(0, 64) + "...");
		System.out.println("------------------------------------------------------------------------------------------");

		// name of the output file name
		String fileName = "_decryptedMSG.txt";
		path = "./" + fileName;

		try {
			new FileOutputStream(fileName, false).close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try (PrintWriter pw = new PrintWriter(path)) {
			// Print into a file (save)
			pw.println(plainText);
			System.out.println("Decrypt saved on file name: " + fileName);
			
			// Stop record the time and print on screen
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out.println("In " + ((elapsedTime)/1000) + " Seconds.");

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
	@Override
	public void DecryptParsingFile(String file, String keyword) throws IOException, InterruptedException {
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

			
			plainText = playfair.encrypt(encryptText);
			
			// save into a file
			pw.println(plainText);
			
			// Print a text sample
			
			System.out.println("\n------------------------------------------------------------------------------------------");
			System.out.println("Sample output result.: " + plainText.substring(0, 64) + "...");
			System.out.println("------------------------------------------------------------------------------------------");
			
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
	@Override
	public void EncryptParsingFile(String file, String keyword) throws IOException, InterruptedException {

		if (file.contains("http://") || file.contains("https://")) {

			parser = Parser.getParserURL(new URL(file));

		} else {
			parser = Parser.getParserFile(new File(file));
		}

		plainText = parser.parse();

		Playfair playfair = new Playfair(keyword);

		// name of the file
		String fileName = "_encryptedMSG.txt";
		path = "./" + fileName;

		try {
			new FileOutputStream(fileName, false).close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try (PrintWriter pw = new PrintWriter(path)) {
			
			encryptText = playfair.encrypt(plainText);
			
			// save into a file
			pw.println(encryptText);
			
			// print a result
			System.out.println("\n------------------------------------------------------------------------------------------");
			System.out.println("Sample output result.: " + encryptText.substring(0, 64) + "...");
			System.out.println("------------------------------------------------------------------------------------------");
			
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
	@Override
	public Boolean validateFile(String file) throws IOException {

		boolean keepRunningEncDec = true;
		// Checks if the file is a URL and if is need contain http:// or https://. If
		// contain will execute the try.
		if (file.contains("http://") || file.contains("https://")) {

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
