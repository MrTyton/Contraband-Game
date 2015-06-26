import java.util.ArrayList;
import java.util.Collections;


public class Match {
	
	private Team first_team;
	private Team second_team;
	private int rounds;
	
	
	public Match(ArrayList<Player> players, String first_name, String second_name, int rounds) {
		if (players.size() % 2 != 0 || rounds <= 0) {
			throw new IllegalArgumentException();
		}
		
		this.rounds = rounds;
		
		for (Player current : players) {
			current.initializeAccounts(100, 300);
		}
		
		Collections.shuffle(players);
		
		ArrayList<Player> temp1 = new ArrayList<Player>();
		for (int i = 0; i < players.size() / 2; i++) {
			temp1.add(players.get(i));
		}
		ArrayList<Player> temp2 = new ArrayList<Player>();
		for (int i = players.size() / 2; i < players.size(); i++) {
			temp2.add(players.get(i));
		}
		
		first_team = new Team(first_name, temp1, rounds);
		second_team = new Team(second_name, temp2, rounds);
		
		return;		
		
	}
	
	public void run() {
		
		System.out.println("The teams are:");
		System.out.println(this.first_team.getName());
		for (Player current : this.first_team.getPlayers()) {
			System.out.println("\t" + current.getName());
		}
		System.out.println(this.second_team.getName());
		for (Player current : this.second_team.getPlayers()) {
			System.out.println("\t" + current.getName());
		}
		boolean first_smuggling = true;
		for (int i = 0; i < this.rounds; i++) {
			Smuggler smuggler;
			Inspector inspector;
			if (first_smuggling) {
				smuggler = first_team.getNextSmuggler();
				inspector = second_team.getNextInspector();
			}
			else {
				smuggler = second_team.getNextSmuggler();
				inspector = first_team.getNextInspector();
			}
			Round current_Round = new Round(smuggler, inspector);
			current_Round.run();
			System.out.println();
			first_smuggling = ! first_smuggling;
		}
		int whoWon = Scorekeeping.whoWon(this.first_team, this.second_team);
		if (whoWon == 1) {
			System.out.println("Team " + this.first_team.getName() +" won this round! Congradulations!");
			Scorekeeping.updateScores(this.first_team, this.second_team, 1.5, .5, 1);
		}
		else if (whoWon == -1) {
			System.out.println("Team " + this.second_team.getName() + " won this round! Congradulations!");
			Scorekeeping.updateScores(this.second_team, this.first_team, 1.5, .5, 1);
		}
		else {
			System.out.println("This round was a tie!");
			Scorekeeping.updateScores(this.first_team, this.second_team, 1, 1, 1);
		}
	}
}
