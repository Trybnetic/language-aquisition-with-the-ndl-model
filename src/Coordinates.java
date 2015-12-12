/**
 * 
 * This class represents the basic informations to move in the 2D world of the agent.
 * It also contains methods to generate such informations randomly.
 * 
 * @author Benjamin Mitzkus, Marc Weitz
 *
 */
public class Coordinates {
	private int direction;
	private int val;
	
	public Coordinates(){
		this.direction= (int)(Math.random() * 4) +1;
		this.val= (int)(Math.random() * 4) +1;
	}
	public Coordinates(int dir, int val){
		this.direction=dir;
		this.val=val;
	}
	
	public int getDirection() {
		return direction;
	}
	public int getVal() {
		return val;
	}
	
	public boolean isEqual(Coordinates other){
		if(this.direction==other.getDirection() && this.val==other.getVal()) return true;
		else return false;
	}
	public String toString(){
		return "x="+this.direction+", y="+this.val;
	}
	
	
}
