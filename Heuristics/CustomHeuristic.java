import java.util.HashMap;
import java.util.LinkedList;

public class CustomHeuristic implements Heuristic {
	public int getValue(Game g) {
		Couple c= g.cars[0].coordinates;
		int n=0;
		LinkedList<Vehicle> vehiclesBlocking= new LinkedList<Vehicle>();
		HashMap<Integer, Vehicle> doubleBlock = new HashMap<Integer, Vehicle>();
		for (int i=c.abs; i<Game.size;i++) {
			if (g.display[c.ord-1][i]!=0) {
				n++;
				Vehicle blocking= g.cars[g.display[c.ord-1][i]-1];
				vehiclesBlocking.add(blocking);
			}
		}
		
		for (Vehicle v: vehiclesBlocking) {
			
			Couple d= v.coordinates;
			
			if (v.sizeOfVehicle==2) {
				if (d.ord-2<0) { //bloqu� en haut par la grille
					int i=g.display[d.ord+1][d.abs-1];
					if (i!=0) {// ET bloqu� par un v�hicule en bas
						doubleBlock.put(i, g.cars[i]);
					}
				}
				else { //pas bloqu� en haut par la grille
					if (d.ord+1>=Game.size) { //bloqu� en bas par la grille
						int i=g.display[d.ord-2][d.abs-1];
						if (i!=0) { // ET bloqu� par un v�hicule en haut
							doubleBlock.put(i, g.cars[i]);
						}
					}
					else { //pas bloqu� par la grille
						int i=g.display[d.ord-2][d.abs-1];
						int j=g.display[d.ord+1][d.abs-1];
						if (i!=0&&j!=0) { //bloqu� en haut et en bas par un v�hicule
							doubleBlock.put(i, g.cars[i]);
							doubleBlock.put(j, g.cars[j]);
						}
					}
				}
			}
			
			if (v.sizeOfVehicle==3) {
				
				if (d.ord-2<0) { //bloqu� en haut par la grille
					int i=g.display[d.ord+2][d.abs-1];
					if (i!=0) {// ET bloqu� par un v�hicule en bas
						doubleBlock.put(i, g.cars[i]);
					}
				}
				else { //pas bloqu� en haut par la grille
					if (d.ord+2>=Game.size) { //bloqu� en bas par la grille
						int i=g.display[d.ord-2][d.abs-1];
						if (i!=0) { // ET bloqu� par un v�hicule en haut
							doubleBlock.put(i, g.cars[i]);
						}
					}
					else { //pas bloqu� par la grille
						int i=g.display[d.ord-2][d.abs-1];
						int j=g.display[d.ord+2][d.abs-1];
						if (i!=0&&j!=0) { //bloqu� en haut et en bas par un v�hicule
							doubleBlock.put(i, g.cars[i]);
							doubleBlock.put(j, g.cars[j]);
						}
					}
				}
			}
		}
		
		for (int i:doubleBlock.keySet()) {
			n++;
		}
		
		return n;
	}
}
