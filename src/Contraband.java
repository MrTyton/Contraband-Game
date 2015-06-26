import java.util.ArrayList;
import java.util.Collections;


public class Contraband {

	public static void main(String[] args) {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("A"));
		players.add(new Player("B"));
		players.add(new Player("C"));
		players.add(new Player("D"));
		
		Match current_match = new Match(players, "First Team", "Second Team", 8);
		current_match.run();
		
		Collections.sort(players);
		for (Player current : players) {
			System.out.println(current);
		}
		
		return;

	}

}
