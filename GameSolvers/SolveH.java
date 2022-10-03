import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.Comparator;


public class SolveH {
	
	public static void solve(Game game){ 
		
		HashMap <String,Gamove> visited = new HashMap<String,Gamove>();
		// visited.put(game.printdisplay(), null);
		
		Comparator<Game> comparator = new GameComparator();
		
		PriorityQueue<Game> queue = new PriorityQueue<>(comparator);
		queue.add(game);
		
		int nb=0; // number of visited states
			
	
		while(!queue.isEmpty()){
			nb++;
			Game u = queue.poll();
			System.out.println(u.printdisplay());
			System.out.println("-------------------------");
			if(u.isGoal()){
				System.out.println("Nombre d'etapes:"+nb);
				printSolution(visited, game, u);
				break;	
			}
			for(Gamove v : Game.getNext(u)){
				Move m = v.move;
				Game g = v.game;
				if(!visited.containsKey(g.printdisplay())){
					queue.add(g);
					visited.put(g.printdisplay(), new Gamove(u,m));
				}
			}
		}
		
		
	}
	
	public static void printSolution(HashMap<String, Gamove> hm, Game initgame, Game endgame) {
		
		Stack<String> instructions = new Stack<String>();
		Stack<String> gamesDisplays = new Stack<String>();
		gamesDisplays.push(endgame.printdisplay());
		Game g = endgame;
		Move m;
		Gamove temp;
		
		while(!(g.printdisplay().equals(initgame.printdisplay()))) {
			temp = hm.get(g.printdisplay());
			
			g = temp.game;
			gamesDisplays.push(g.printdisplay());
			m = temp.move;
			instructions.push(m.toString());
		
		}
		
		System.out.println("Le nombre d'etapes dans la solution reconstruite est: "+instructions.size());
		
		String s; String display;
		while(!instructions.isEmpty()) {
			display = gamesDisplays.pop();
			s = instructions.pop();
			System.out.println(display);
			System.out.println(s);
		}
		
		System.out.println(endgame.printdisplay());
	}
	 
	public static void main(String[] args) {
	
		Game game=new Game ("C:\\Users\\Alya\\eclipse-workspace\\Rush hour Celine\\RushHour1.txt");
		
		long startTime = System.currentTimeMillis();
		
		solve(game);
		
		long endTime=System.currentTimeMillis();
		
		long time=endTime-startTime;
		
		System.out.println("Temps d'execution: "+time +" ms");
		
	}
}
