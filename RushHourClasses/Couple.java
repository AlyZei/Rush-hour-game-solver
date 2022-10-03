public class Couple {
	int abs;
	int ord;
	Couple (int abs, int ord){
		this.abs=abs;
		this.ord=ord;
	}
	
	public static String printCouple(Couple c) {
		return "("+c.abs+"; "+c.ord+")";
	}
}
