/**
 * Input class which makes a sentence from a coordinate object
 * 
 * @author Benjamin Mitzkus, Marc Weitz
 *
 */
public class Input {
	public static String generateCue(Coordinates coord){
		String cue = "Go:";
		switch(coord.getVal()){
		case 1: cue=cue+"one:";
				break;
		case 2: cue=cue+"two:";
				break;
		case 3: cue=cue+"three:";
				break;
		case 4: cue= cue+"four:";
				break;
		default: //TODO probably implement exception, if external input is requested
		}
		
		cue= cue + "fields:";
		
		switch(coord.getDirection()){
		case 1: cue=cue+"up";
				break;
		case 2: cue=cue+"right";
				break;
		case 3: cue=cue+"down";
				break;
		case 4: cue= cue+"left";
				break;
		default: //TODO probably implement exception, if external input is requested
		}
		return cue;
	}
}
