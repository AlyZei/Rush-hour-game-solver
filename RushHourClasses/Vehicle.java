
public class Vehicle {
	int name;
	String orientation;
	Couple coordinates;
	int sizeOfVehicle;
	
	Vehicle (String [] s){
		this.name=Integer.parseInt(s[0]);
		this.orientation= s[1];
		this.sizeOfVehicle=Integer.parseInt(s[2]);
		this.coordinates=new Couple(Integer.parseInt(s[3]), Integer.parseInt(s[4]));
	}
	
	Vehicle (int n, String o, Couple coord, int l){
		this.name=n;
		this.orientation=o;
		this.coordinates=coord;
		this.sizeOfVehicle=l;
	}
}
