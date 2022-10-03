
public class BlockingHeuristic implements Heuristic{
	public int getValue(Game g) {
		Couple c= g.cars[0].coordinates;
		int n=0;
		for (int i=c.abs; i<Game.size;i++) {
			if (g.display[c.ord-1][i]!=0) {
				n++;
			}
		}
		return n;
	}
}
