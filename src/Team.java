import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;


public class Team implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArrayList<Player> getPlayers() {
		return players;
	}

	private ArrayList<Player> players;
	
	private ArrayBlockingQueue<Player> inspectors;
	private ArrayBlockingQueue<Player> smugglers;
	
	private String name;
	
	@SuppressWarnings("unchecked")
	public Team(String name, ArrayList<Player> players, int rounds) { //rounds needs to be a multiple of length of players
		this.name = name;
		this.players = players;
		this.inspectors = new ArrayBlockingQueue<Player>(rounds);
		this.smugglers = new ArrayBlockingQueue<Player>(rounds);
		ArrayList<Player> inspectorCopy = (ArrayList<Player>) players.clone();
		ArrayList<Player> smugglerCopy = (ArrayList<Player>) players.clone();
		Collections.shuffle(inspectorCopy);
		Collections.shuffle(smugglerCopy);
		int len = players.size();
		for (int i =0 ; i < rounds; i ++) {
			this.inspectors.add(inspectorCopy.get(rounds % len));
			this.smugglers.add(smugglerCopy.get(rounds % len));
		}
		return;
	}
	
	public String getName() {
		return name;
	}

	public int getScore() {
		int ans = 0;
		for (int i = 0; i < this.players.size(); i++) {
			ans += this.players.get(i).getNeutral_Account();
		}
		return ans;
	}
	
	public Smuggler getNextSmuggler() {
		if (this.smugglers.isEmpty()) {
			return null;
		}
		else {
			try {
				return new Smuggler(this.smugglers.take());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public Inspector getNextInspector() {
		if (this.inspectors.isEmpty()) {
			return null;
		}
		else {
			try {
				return new Inspector(this.inspectors.take());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public double calculateTeamScore() {
		 double ans = 0;
		 for (Player temp:this.players) {
			 ans += temp.getNeutral_Account();
		 }
		 return ans;
	 }
	
}
