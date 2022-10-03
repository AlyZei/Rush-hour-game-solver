import java.util.Comparator;

public class GameComparator implements Comparator<Game>{
	
	public int compare (Game g1, Game g2) {
		
		//define new comparator based on the heuristics
		
		//using zeroheuristic
		//Heuristic h=new ZeroHeuristic();
		
		//using number of vehicles blocking the way
		//Heuristic h = new BlockingHeuristic ();
		
		//using our custom heuristic
		Heuristic h = new CustomHeuristic ();
		
		return h.getValue(g1)-h.getValue(g2);
	}
}
