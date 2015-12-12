import java.util.LinkedList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class contains some functions to log the status of the agent 
 * 
 * @author Benjamin Mitzkus, Marc Weitz
 * 
 */
public class Output {
	
	static PrintWriter tempWriter;
	
	/**
	 * Initializes the output file
	 * @param agent
	 */
	public static void initFile(Agent agent){
		try {
			tempWriter = new PrintWriter(new BufferedWriter(new FileWriter(
					"RescorlaWagnerValues.txt")));
			tempWriter.println(generateHeader(agent));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Saves the current state of an Agent
	 * @param agent
	 * @param number
	 */
	public static void saveTrial(Agent agent, int number) {
		double[][] temp=agent.rescVal.getFirst();
		tempWriter.println(generateTable(temp , number));
		agent.rescVal.clear();
		agent.rescVal.add(temp);
	}
	
	/**
	 * Finishes the output file afte work is done
	 */
	public static void finishFile(){
		if (tempWriter != null) {
			tempWriter.flush();
			tempWriter.close();
		}
	}
	
	/**
	 * Generates a header for the tables
	 * @param agent
	 * @return
	 */
	public static String generateHeader(Agent agent){
		String header="";
		for(int i=0; i<agent.vocabulary.length; i++){
			header+=agent.vocabulary[i]+" ";
		}
		header+="action n";
		return header;
	}
	
	/**
	 * Formats the output String
	 * @param list
	 * @return
	 */
	public static String generateOutput(LinkedList<double[][]> list){
		String file="";
		for(int i=0; i<list.size(); i++){
			file+=generateTable(list.get(i), i);
			file+="\n";
		}
		return file;
	}
	
	/**
	 * Generates the output table
	 * @param arr
	 * @param durchgang
	 * @return
	 */
	public static String generateTable(double[][] arr, int durchgang){
		String ret="";
		for(int i=0; i<arr.length; i++){
			for(int j=0; j<arr[i].length; j++){
				ret+=arr[i][j]+" ";
			}
			ret+= i+" "+durchgang+ "\n";
		}
		return ret;
	}
}
