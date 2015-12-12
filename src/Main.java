/**
 * 
 * @author Benjamin Mitzkus, Marc Weitz
 *
 */
public class Main {

	/**
	 * Default values
	 */
	final static double DEFAULT_ALPHA = 0.1;
	final static double DEFAULT_BETA1 = 0.1;
	final static double DEFAULT_BETA2 = 0.1;
	final static double DEFAULT_LAMBDA = 1;
	final static double DEFAULT_EXPLORATIONRATE = 0.5;
	final static int DEFAULT_N = 1000;
	
	/**
	 * Variables
	 */
	static double alpha = DEFAULT_ALPHA,
			beta1 = DEFAULT_BETA1, 
			beta2 = DEFAULT_BETA2, 
			lambda = DEFAULT_LAMBDA,
			explorationRate = DEFAULT_EXPLORATIONRATE;
	static int n = DEFAULT_N;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		setParams(args);
		Agent agent = new Agent(alpha, beta1, beta2, lambda, explorationRate);
		Output.initFile(agent);
		for (int i = 0; i < n; i++) {
			agent.move();
		}
		Output.finishFile();
	}

	/**
	 * Sets the Parameters to the Values of the input
	 * @param args
	 */
	static void setParams(String[] args) {
		String allArgs = toString(args);
		String[] splitArgs = allArgs.split("-");
		String tmp;
		String[] tmpArr;
		double argument;
		for (int i = 1; i < splitArgs.length; i++) {
			tmp = splitArgs[i];
			tmpArr = tmp.split(" ");
			if (tmpArr.length != 2) {
				System.out.println("Invalid Argument: -" + tmp);
			} else {
				argument = Double.parseDouble(tmpArr[1]);
				switch (tmpArr[0]) {
				case "a":
					alpha = argument;
					break;
				case "b1":
					beta1 = argument;
					break;
				case "b2":
					beta2 = argument;
					break;
				case "l":
					lambda = argument;
					break;
				case "n":
					n = (int) argument;
					break;
				default:
					System.out.println("Invalid Argument: " + tmp);
				}
			}
		}
	}

	static String toString(String[] array) {
		String retString = "";
		for (int i = 0; i < array.length; i++) {
			retString += array[i]+" ";
		}
		return retString;
	}
}
