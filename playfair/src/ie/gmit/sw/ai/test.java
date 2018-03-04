package ie.gmit.sw.ai;

import java.io.File;
import java.io.IOException;

import ie.gmit.sw.ai.file.Parser;
import ie.gmit.sw.ai.playfair.Playfair;
import ie.gmit.sw.ai.playfair.PlayfairKey;
import ie.gmit.sw.ai.playfair.SimulatedAnnealing;

public class test {

	public static void main(String[] args) throws IOException, InterruptedException {
		
	
		// String fileName = "test.txt";
		String fileName = "x.txt";
		
		Parser parser = Parser.getParserFile(new File(fileName));
		
		String encryptText = parser.parse();

		
		SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing(50, 1, 5000);

		// Simulate just a limited numbers of char
		PlayfairKey key = simulatedAnnealing.findKey(encryptText.substring(0, 600));
		
		
		
		Playfair playFair = new Playfair(key);
		String plainText = playFair.decrypt(encryptText);

		System.out.println("\n------------------------------------------------------------------------------------------");
		System.out.println("Sample output result.: " + plainText.substring(0, 100) + "...");
		System.out.println("Sample output result.: " + plainText.substring(600, 700) + "...");
		//System.out.println(plainText.replace("X", ""));
		System.out.println("------------------------------------------------------------------------------------------");

	}

}
