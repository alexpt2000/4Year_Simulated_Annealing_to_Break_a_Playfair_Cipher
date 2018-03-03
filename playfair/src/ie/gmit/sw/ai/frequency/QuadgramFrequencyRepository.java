package ie.gmit.sw.ai.frequency;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import ie.gmit.sw.ai.file.Parser;
import ie.gmit.sw.ai.playfair.TextUtils;

/**
 * The Class QuadgramFrequencyRepository.
 */
public class QuadgramFrequencyRepository {
	private double minValue = Double.POSITIVE_INFINITY;
	private static QuadgramFrequencyRepository instance;

	/**
	 * Gets the single instance of QuadgramFrequencyRepository.
	 *
	 * @return single instance of QuadgramFrequencyRepository
	 */
	public static QuadgramFrequencyRepository getInstance() {
		if (instance == null)
			instance = new QuadgramFrequencyRepository();
		return instance;
	}

	private Map<String, Double> scores;

	private QuadgramFrequencyRepository() {
		try {
			Map<String, Double> frequencies = Parser.getQuadgramFrequencies();
			scores = new HashMap<String, Double>();
			for (Map.Entry<String, Double> entry : frequencies.entrySet()) {
				double val = Math.log(entry.getValue());
				scores.put(entry.getKey(), val);
				if (minValue > val)
					minValue = val;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		getInstance();
	}

	/**
	 * Gets the text fitness.
	 *
	 * @param text the text
	 * @return the text fitness
	 */
	public double getTextFitness(String text) {
		double result = 0;
		String preptext = TextUtils.prepareText(text);
		for (int i = 0; i < preptext.length() - 3; i++) {
			String quadgram = preptext.substring(i, i + 4);
			if (scores.containsKey(quadgram))
				result += scores.get(quadgram);
			else
				result += minValue;
		}
		return result;
	}

}
