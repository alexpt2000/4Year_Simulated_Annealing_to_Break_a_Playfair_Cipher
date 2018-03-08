package ie.gmit.sw.ai.playfair;

import ie.gmit.sw.ai.frequency.QuadgramFrequencyRepository;

/**
 * The Class SimulatedAnnealing.
 * 
 * 
 * Ref.
 * http://www.theprojectspot.com/tutorial-post/simulated-annealing-algorithm-for-beginners/6
 * https://github.com/nsadawi/simulated-annealing/blob/master/Tour.java
 * https://github.com/abrown/aima-java/blob/master/aima-core/src/main/java/aima/core/search/local/SimulatedAnnealingSearch.java
 * https://github.com/ugljesas/playfair/blob/master/playfair/src/playfair/breaking/SimulatedAnnealing.java
 * 
 * @author Alexander Souza
 */
public class SimulatedAnnealing {

	private double maxTemp, step;
	private int iterationsOnTemp;

	/**
	 * Instantiates a new simulated annealing.
	 *
	 * @param maxTemp the max temp
	 * @param step the step
	 * @param iterationsOnTemp the iterations on temp
	 */
	public SimulatedAnnealing(double maxTemp, double step, int iterationsOnTemp) {
		this.maxTemp = maxTemp;
		this.step = step;
		this.iterationsOnTemp = iterationsOnTemp;
	}

	/**
	 * Gets the fitness.
	 *
	 * @param cipherText the cipher text
	 * @param playfair the playfair
	 * @return the fitness
	 */
	public double getFitness(String cipherText, Playfair playfair) {
		String candidate = playfair.decrypt(cipherText);

		return QuadgramFrequencyRepository.getInstance().getTextFitness(candidate);
	}

	/**
	 * Find key.
	 *
	 * @param cipherText the cipher text
	 * @return the playfair key
	 */
	public PlayfairKey findKey(String cipherText) {
		double bestFitness = Double.NEGATIVE_INFINITY;

		PlayfairKey parent = PlayfairKey.getRandomKey();
		PlayfairKey tempParent = PlayfairKey.getRandomKey();
		PlayfairKey bestKey = null;
		Playfair playfair = new Playfair(parent);
		double parentFitness = getFitness(cipherText, playfair);

		int progressBar = 0;

		int count = 0;
		
		for (double temp = maxTemp; temp > 0; temp -= step) {

			//parent
			
			//System.out.println(bestKey);
			

			
			progressBar += 2;

			if (progressBar % 3 == 0) {
				System.out.print(progressBar + "% ");

			}

			for (int it = 0; it < iterationsOnTemp; it++) {

				PlayfairKey child = parent.makeChildKey();
				playfair.setKey(child);
				double childFitness = getFitness(cipherText, playfair);
				double df = childFitness - parentFitness;
				boolean takeChild = false;

				if (df > 0)
					takeChild = true;
				else if (Math.random() < Math.exp(df / temp))
					takeChild = true;
				if (takeChild) {

					parentFitness = childFitness;
					parent = child;
				}
				if (parentFitness > bestFitness) {
					bestFitness = parentFitness;
					bestKey = parent;
				}
			}
			
			
			if (count == 0) {
				tempParent = parent;
				// System.out.println("Set new value....");
				count++;
			}
			else if (tempParent.equals(parent)) {
				// System.out.println("Detect iqual....");
				count++;
			}
			else {
				count = 0;
			}
			
			if (count > 5) {
				// System.out.println("Best key....");
				temp = -1000;

			}
			
		}
		return bestKey;
	}

	/**
	 * Gets the max temp.
	 *
	 * @return the max temp
	 */
	public double getMaxTemp() {
		return maxTemp;
	}

	/**
	 * Sets the max temp.
	 *
	 * @param maxTemp the new max temp
	 */
	public void setMaxTemp(double maxTemp) {
		this.maxTemp = maxTemp;
	}

	/**
	 * Gets the step.
	 *
	 * @return the step
	 */
	public double getStep() {
		return step;
	}

	/**
	 * Sets the step.
	 *
	 * @param step the new step
	 */
	public void setStep(double step) {
		this.step = step;
	}

	/**
	 * Gets the iterations on temp.
	 *
	 * @return the iterations on temp
	 */
	public int getIterationsOnTemp() {
		return iterationsOnTemp;
	}

	/**
	 * Sets the iterations on temp.
	 *
	 * @param iterationsOnTemp the new iterations on temp
	 */
	public void setIterationsOnTemp(int iterationsOnTemp) {
		this.iterationsOnTemp = iterationsOnTemp;
	}
}
