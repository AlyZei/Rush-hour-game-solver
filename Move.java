
public class Move {
	int nameOfVehicle;
	Couple newCoordinates;
	int moveLength;
	boolean forward;
	String orientation;
	
	Move (int i, Couple c, int ml, boolean fw, String s){
		this.nameOfVehicle=i;
		this.newCoordinates=c;
		this.moveLength = ml;
		this.forward = fw;
		this.orientation = s;
	}
	
	public String toString() {
		String instruction = "Deplacer la voiture " + this.nameOfVehicle + " de " + this.moveLength + " case";
		if(this.moveLength > 1) {
			instruction += "s";
		}
		instruction += " vers ";
		
		if(orientation == "v" && forward) instruction += "le bas";
		if(orientation == "v" && !forward) instruction += "le haut";
		if(orientation == "h" && forward) instruction += "la droite";
		if(orientation == "h" && !forward) instruction += "la gauche";
		
		return(instruction);
		
	}
}
