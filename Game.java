import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;


//definition de la classe
public class Game {
	boolean valid;
	static int size;
	int numberOfVehicles;
	Vehicle [] cars;
	int [][] display;
	Couple exit;

	//Constructeur
	public Game(int size, int n, Couple e, Vehicle[] v, int[][] disp) {
		Game.size=size;
		numberOfVehicles=n;
		exit=e;
		cars=v;
		display=disp;
	}
	
	
	//Determine si un game représente un état final
	public boolean isGoal() {
		int i_goal=exit.ord-1;
		int j_goal=exit.abs-1;
		if (display[i_goal][j_goal]==1) {return true;
	}
		else {return false;}
	}
	
	
	//Comparateur de game
	public boolean equals (Game g) {
		return this.display.equals(g.display);
	}
	
//Task 1
	
	//Définir une sortie, en face du premier véhicule
	public void defineExit() {
		if (this.cars[0].orientation.equals("h")){
			this.exit= new Couple(size, this.cars[0].coordinates.ord);
		}
		else {
			this.exit= new Couple(this.cars[0].coordinates.abs, size);
		}	
	}
	
	
//initialiser le jeu (ne va pas jusqu'au bout si le jeu n'est pas valide)
	public Game (String file) {
		try {
		BufferedReader f= new BufferedReader (new FileReader(file)); //lit le fichier
		this.valid=true;
		size=Integer.parseInt(f.readLine());
		this.numberOfVehicles=Integer.parseInt(f.readLine());
		this.cars=new Vehicle[this.numberOfVehicles];
		this.display=new int[size][size];
		
		for (int i=0; i<this.numberOfVehicles; i++) { //implemente le jeu en verifiant qu'il est valide
			String s= f.readLine();
			String [] help=s.split(" ");
			Vehicle car=new Vehicle(help);
			this.cars[i]=car;
		}
		
		//update la matrice display
		this.updateDisplayConstructor();
		
		//define the exit in front of the first car
		this.defineExit();
		
		f.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found, rename the file game.text");
		}
		catch (IOException g) {
		      System.out.println ("Can't read the file");
	    }
	}
	
	
	//Update la matrice display en vérifiant que le set de vehicules est valide
	public void updateDisplayConstructor() {
		this.display= new int[size][size];
		for (int i=0; i<this.numberOfVehicles; i++) { //implï¿½mente le jeu en vï¿½rifiant qu'il est valide
			Vehicle car=this.cars[i];
			int x=car.coordinates.abs;
			int y= car.coordinates.ord;
			int k=0;
			
			if (car.orientation.equals("h")) {
				while (k<car.sizeOfVehicle&&display[y-1][x-1+k]==0) {
					display[y-1][x-1+k]=i+1;
					k++;
				}
				if (k!=car.sizeOfVehicle) {
					this.valid=false;
					break;
				}
			}
			
			if (car.orientation.equals("v")) {
				while (k<car.sizeOfVehicle&&display[y-1+k][x-1]==0) {
					display[y-1+k][x-1]=i+1;
					k++;
				}
				if (k!=car.sizeOfVehicle) {
					this.valid=false;
					break;
				}
			}
		}
	}
	

//Task 2: afficher le jeu
	
	public String printdisplay() { //affiche le jeu s'il est valide, "game is not valid" sinon
		if (!this.valid) {
			return("The game is not valid");
		}
		else {
			String s="";
			for (int i=0; i<Game.size;i++) {
				for (int j=0; j<Game.size;j++) {
					s+=this.display[i][j]+" ";
				}
				s+="\n";
			}
			return s;
		}
		
	}
	
//Task 4
	
	public LinkedList<Move> allMoves(){
		LinkedList<Move> moves=new LinkedList<Move>();
		
		for (int i=0; i<this.numberOfVehicles;i++) {
			
			if (cars[i].orientation.equals("h")) {
				
				int k=1; //bouger a gauche
				while (cars[i].coordinates.abs-1-k>=0 && this.display[cars[i].coordinates.ord-1][cars[i].coordinates.abs-1-k]==0) {
					Couple c= new Couple (cars[i].coordinates.abs-k,cars[i].coordinates.ord);
					moves.add(new Move(i+1, c, k, false, "h"));
					k++;
				}
				
				int j=1; //bouger a droite
				while (cars[i].coordinates.abs-1+j+cars[i].sizeOfVehicle<=size && this.display[cars[i].coordinates.ord-1][cars[i].coordinates.abs-2+j+cars[i].sizeOfVehicle]==0) {
					Couple c= new Couple (cars[i].coordinates.abs+j,cars[i].coordinates.ord);
					moves.add(new Move(i+1, c, j, true, "h"));
					j++;
				}
			}
			
			if (cars[i].orientation.equals("v")) {
				
				int k=1;//bouger vers le haut
				while (cars[i].coordinates.ord-1-k>=0 && this.display[cars[i].coordinates.ord-1-k][cars[i].coordinates.abs-1]==0) {
					Couple c= new Couple (cars[i].coordinates.abs,cars[i].coordinates.ord-k);
					moves.add(new Move(i+1, c, k, false, "v"));
					k++;
				}
				
				int j=1; //bouger vers le bas
				while (cars[i].coordinates.ord-1+j+cars[i].sizeOfVehicle<=size && this.display[cars[i].coordinates.ord-2+j+cars[i].sizeOfVehicle][cars[i].coordinates.abs-1]==0) {
					Couple c= new Couple (cars[i].coordinates.abs,cars[i].coordinates.ord+j);
					moves.add(new Move(i+1, c, j, true, "v"));
					j++;
				}
			}
		}
		return moves;
	}
	
	//print all moves
	
	public static void printAllMoves(LinkedList<Move> moves) {
		System.out.println("Vous pouvez: ");
		for (Move m: moves) {
			System.out.println(m.toString());
			System.out.println();
		}
	}
	
//Task 5
	
	//update display with a set Vehicle[] of vehicles

public static int[][] updateDisplay(Vehicle[] v, int size) {
		int [][] display=new int[size][size];
		
		for (int i=0; i<v.length; i++) { 
			int x=v[i].coordinates.abs;
			int y= v[i].coordinates.ord;
			int k=0;
			if (v[i].orientation.equals("h")) {
				while (k<v[i].sizeOfVehicle&&display[y-1][x-1+k]==0) {
					display[y-1][x-1+k]=i+1;
					k++;
				}
			}
			if (v[i].orientation.equals("v")) {
				while (k<v[i].sizeOfVehicle&&display[y-1+k][x-1]==0) {
					display[y-1+k][x-1]=i+1;
					k++;
				}
			}
		}
		return display;
	}	
		
	//create a new state from the initial state
	public static Game updateState(Game game, Move move) {
		Vehicle[] newcars=clonecars(game.cars);
		int[][] newdisplay=clonedisplay(game.display);
		Game updatedGame= new Game (size, game.numberOfVehicles, game.exit, newcars, newdisplay);
		updatedGame.valid=true;
		updatedGame.cars[move.nameOfVehicle-1].coordinates=move.newCoordinates;
		updatedGame.display=updateDisplay(updatedGame.cars, size);
		return updatedGame;
	}
	
	
	//From the current state, get the list of available states with the move that allowed to reach it
	public static LinkedList<Gamove> getNext(Game game){
		
		LinkedList<Gamove> next=new LinkedList<Gamove>();
		LinkedList<Move> moves=game.allMoves();
		
		for (Move m:moves) {
			next.addLast(new Gamove(updateState(game, m),m));
		}
	
		return next;
		
	}
	
	
	//recopier le tableau cars sans fusionner les adresses
	public static Vehicle[] clonecars(Vehicle[] cars) {
		
		Vehicle[] clones=new Vehicle[cars.length];
		int i=0;
		while (i<cars.length) {
			Vehicle clone=new Vehicle(cars[i].name,cars[i].orientation, cars[i].coordinates, cars[i].sizeOfVehicle);
			clones[i]= clone;
			i++;
		}
		
		return clones;
	
	}
	
	
	//Recopier les matrices display sans fusionner les adresses
	public static int[][] clonedisplay(int[][] display){
		
		int[][] clone= new int[size][size];
		int i=0;
		int j=0;
		
		while (i<display.length) {
			while (j<display.length) {
				int n=display[i][j];
				clone[i][j]=n;
				j++;
			}
			i++;
		}
		
		return clone;
	}
	
//Test, methode main
	public static void main (String [] args) { //test= Task2
		Game game = new Game ("C:\\Users\\Alya\\eclipse-workspace\\Rush hour Celine\\RushHour1.txt");
		System.out.println(game.printdisplay());
		System.out.println();
		printAllMoves(game.allMoves());
		Game game2 = updateState(game, game.allMoves().getFirst());
		System.out.println();
		System.out.println(game2.printdisplay());
	}
	
	
	
}
	

 