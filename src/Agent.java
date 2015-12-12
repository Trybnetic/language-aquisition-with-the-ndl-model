import java.util.LinkedList;

/**
 * Model of an simple learning Agent by only giving him a short vocabulary 
 * his Rescorla-Wagner-Values and a changing exploration rate depending on his
 * success in finding the target 
 * 
 * @author Benjamin Mitzkus, Marc Weitz
 * 
 */
public class Agent {
	LinkedList<double[][]> rescVal;
	String[] vocabulary = { "Go", "one", "two", "three", "four", "fields",
			"up", "right", "down", "left" };
	double alpha;
	double beta1;
	double beta2;
	double lambda;
	double explorationRate;
	int counter;


	public Agent(double alpha, double beta1, double beta2, double lambda,
			double explorationRate) {
		this.rescVal = new LinkedList<double[][]>();
		this.rescVal.add(new double[16][10]);
		this.alpha = alpha;
		this.beta1 = beta1;
		this.beta2 = beta2;
		this.lambda = lambda;
		this.explorationRate = explorationRate;
		this.counter = 0;
	}

	public void addNewValues(double[][] newValues) {
		this.rescVal.add(newValues);
	}

	/**
	 * moves the agent
	 */
	public void move() {
		this.counter++;
		Coordinates coordTarget = new Coordinates();
		String cue = Input.generateCue(coordTarget);
		this.update(cue, coordTarget);
		Output.saveTrial(this, counter);
	}

	/**
	 * Selects out of its Rescorla-Wagner-Values the Coordinates it wants to move to
	 */
	public Coordinates selectMove(String[] cue) {
		if (Math.random() < explorationRate) {
			return new Coordinates((int) (Math.random() * 4 + 1),
					(int) (Math.random() * 4 + 1));
		}

		double[][] current = this.rescVal.getFirst();
		double[] maxArr = new double[current.length];

		int[] strInd = getMatchingIndizes(cue);

		for (int i = 0; i < maxArr.length; i++) {
			double sum = 0;
			for (int j = 0; j < strInd.length; j++) {
				sum += current[i][strInd[j]];
			}
			maxArr[i] = sum;
		}

		int action = findMaxIndex(maxArr);
		int actionDir = (action / 4) + 1;
		int actionVal = (action % 4) + 1;

		return new Coordinates(actionDir, actionVal);
	}

	/**
	 * finds the matching indices of the vocabulary array
	 */
	public int[] getMatchingIndizes(String[] arr) {
		int[] strInd = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			int c = linSearch(arr[i], this.vocabulary);
			if (c >= 0)
				strInd[i] = c;
			else {
				// TODO: add Element to vocabulary
			}
		}
		return strInd;
	}

	/**
	 * TODO: insert in getMatchingIndizes Adds String a to the current
	 * vocabulary of the Agent
	 * 
	 * @param a
	 */
	public void addVocab(String a) {
		String[] newArr = new String[this.vocabulary.length + 1];
		for (int i = 0; i < this.vocabulary.length; i++) {
			newArr[i] = this.vocabulary[i];
		}
		newArr[this.vocabulary.length] = a;
		this.vocabulary = newArr;
	}

	/**
	 * finds the index with highest value in a double array
	 */
	public int findMaxIndex(double[] arr) {
		double max = arr[0];
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
				list.clear();
				list.add(i);
			} else if (arr[i] == max) {
				list.add(i);
			}
		}
		return (int) list.get((int) (Math.random() * list.size()));
	}

	/**
	 * returns the first index of a in arr
	 */
	public int linSearch(String a, String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(a))
				return i;
		}
		return -1;
	}

	/**
	 * updates the status of the agent each tick
	 * @param cue
	 * @param targetCoord
	 */
	public void update(String cue, Coordinates targetCoord) {
		String[] words = cue.split(":");
		Coordinates moveCoord = selectMove(words);
		int actionIndex = moveCoord.getVal() - 1 + 4
				* (moveCoord.getDirection() - 1);
		boolean hasFound = targetCoord.isEqual(moveCoord);

		printStatus(hasFound);
		
		this.updateExplorationRate(hasFound);
		double[][] newVals = this.copyArr(rescVal.getFirst());

		double cumWeight = calculateCumweight(actionIndex, words);

		this.RescorlaWagner(newVals, words, actionIndex, cumWeight, hasFound);

		rescVal.addFirst(newVals);

	}

	/**
	 * calculates the new Rescorla-Wagner-Values
	 * @param newVals 
	 * @param words
	 * @param actionIndex
	 * @param cumWeight
	 * @param hasFound
	 */
	public void RescorlaWagner(double[][] newVals, String[] words,
			int actionIndex, double cumWeight, boolean hasFound) {
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < vocabulary.length; j++) {
				if (words[i].equals(vocabulary[j])) {
					double wi = newVals[actionIndex][j];
					if (hasFound) {
						wi += alpha * beta1 * (lambda - cumWeight);
					} else {
						wi += alpha * beta2 * (0 - cumWeight);
					}
					newVals[actionIndex][j] = wi;
					break;
				}
			}
		}
	}

	/**
	 * calculates the cumulated weights of the current Recorla-Wagner-Values
	 * @param actionIndex
	 * @param words
	 * @return
	 */
	public double calculateCumweight(int actionIndex, String[] words) {
		double cumWeight = 0;
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < vocabulary.length; j++) {
				if (words[i].equals(vocabulary[j])) {
					cumWeight += rescVal.getFirst()[actionIndex][j];
					break;
				}
			}
		}
		cumWeight = cumWeight / words.length;
		return cumWeight;
	}

	/**
	 * copy an array
	 * @param arr
	 * @return
	 */
	public double[][] copyArr(double[][] arr) {
		double[][] newVals = new double[rescVal.getFirst().length][rescVal
				.getFirst()[0].length];
		for (int i = 0; i < newVals.length; i++) {
			for (int j = 0; j < newVals[0].length; j++) {
				newVals[i][j] = rescVal.getFirst()[i][j];
			}
		}
		return newVals;
	}

	/**
	 * updates the exploration rate based on whether the goal was found or not
	 * @param hasFound
	 */
	public void updateExplorationRate(boolean hasFound) {
		if (hasFound) {
			this.explorationRate += this.explorationRate * (-this.explorationRate)*0.25;
		} else if (this.explorationRate < 0.5) {
			this.explorationRate += (0.5 - this.explorationRate) * this.explorationRate*0.25;
		}

	}
	
	public static void printStatus(boolean hasFound) {
		System.out.println(hasFound ? "The target was found"
				: "The target was not found");

	}

}
