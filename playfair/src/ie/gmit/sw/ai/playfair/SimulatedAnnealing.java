package ie.gmit.sw.ai.playfair;

import ie.gmit.sw.ai.frequency.QuadgramFrequencyRepository;

public class SimulatedAnnealing {

	private double maxTemp, step;
	private int iterationsOnTemp;

	public SimulatedAnnealing(double maxTemp, double step, int iterationsOnTemp) {
		this.maxTemp = maxTemp;
		this.step = step;
		this.iterationsOnTemp = iterationsOnTemp;
	}

	public double getFitness(String cipherText, Playfair playfair) {
		String candidate = playfair.decrypt(cipherText);

		return QuadgramFrequencyRepository.getInstance().getTextFitness(candidate);
	}

	public PlayfairKey findKey(String cipherText) {
		double bestFitness = Double.NEGATIVE_INFINITY;

		PlayfairKey parent = PlayfairKey.getRandomKey();
		PlayfairKey bestKey = null;
		Playfair playfair = new Playfair(parent);
		double parentFitness = getFitness(cipherText, playfair);

		int progressBar = 0;
		
		for (double temp = maxTemp; temp > 0; temp -= step) {

			progressBar += 2;
			
			if(progressBar % 3 == 0) {
				System.out.print(progressBar + "% ");
				
			}

			
//			System.out.print(temp);
//			System.out.print(parentFitness);
//			System.out.print(parent);
			
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
		}
		return bestKey;
	}

	public double getMaxTemp() {
		return maxTemp;
	}

	public void setMaxTemp(double maxTemp) {
		this.maxTemp = maxTemp;
	}

	public double getStep() {
		return step;
	}

	public void setStep(double step) {
		this.step = step;
	}

	public int getIterationsOnTemp() {
		return iterationsOnTemp;
	}

	public void setIterationsOnTemp(int iterationsOnTemp) {
		this.iterationsOnTemp = iterationsOnTemp;
	}
}
