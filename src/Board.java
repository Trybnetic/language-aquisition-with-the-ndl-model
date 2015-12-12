/**
 * Board to display the current game situation
 * @author Benjamin Mitzkus, Marc Weitz
 *
 */
public class Board {
	int[][] boardArray;
	
	public Board(){
		boardArray= new int[9][9];
	}
	
	/**
	 * directions:
	 * 1 up
	 * 2 right
	 * 3 down
	 * 4 left
	 * 
	 * @param val (values from 1 to 4)
	 * @param direction (directions 1 to 4)
	 */
	public void setTarget(Coordinates targetCoord){
		switch(targetCoord.getDirection()){
		case 1: this.boardArray[4-targetCoord.getVal()][4]=1;
				break;
		case 2: this.boardArray[4][4+targetCoord.getVal()]=1;
				break;
		case 3: this.boardArray[4+targetCoord.getVal()][4]=1;
				break;
		case 4: this.boardArray[4][4-targetCoord.getVal()]=1;
				break;
		default: //TODO: add an exception here
		}
	}
	/**
	 * set agent to middle position
	 */
	public void setAgent(){
		this.boardArray[4][4]=2;
	}
	/**
	 * moves agent and returns whether the target was found or not
	 * @param val
	 * @param direction
	 * @return
	 */
	public boolean moveAgent(Coordinates newCoordAgent){
		this.boardArray[4][4]=' ';
		boolean isFound=false;
		switch(newCoordAgent.getDirection()){
		case 1: if(this.boardArray[4-newCoordAgent.getVal()][4]==1) isFound=true;
				this.boardArray[4-newCoordAgent.getVal()][4]=2;
				return isFound;
		case 2: if(this.boardArray[4][4+newCoordAgent.getVal()]==1) isFound=true;
				this.boardArray[4][4+newCoordAgent.getVal()]=2;
				return isFound;
		case 3: if(this.boardArray[4+newCoordAgent.getVal()][4]==1) isFound=true;
				this.boardArray[4+newCoordAgent.getVal()][4]=2;
				return isFound;
		case 4: if(this.boardArray[4][4-newCoordAgent.getVal()]==1) isFound=true;
				this.boardArray[4][4-newCoordAgent.getVal()]=2;
				return isFound;
		default: return false; //TODO: add an exception here
		}
	}
	/**
	 * Prints the Board on the console
	 */
	public void printBoard(){
		for(int i=0; i<this.boardArray.length; i++){
			for(int j=0; j<this.boardArray[i].length; j++){
				if(this.boardArray[i][j]==1){
					System.out.print("O ");
					continue;
				}
				if(this.boardArray[i][j]==2){
					System.out.print("X ");
					continue;
				}
				System.out.println("  ");
			}
		}
	}
	
}
