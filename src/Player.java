
public class Player implements java.io.Serializable, Comparable<Player>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	
	private double points;
	
	private double foreign_Account;
	private double neutral_Account;
	
	public String getName() {
		return name;
	}

	public double getPoints() {
		return points;
	}

	public void addPoints(double d) {
		this.points += d;
	}

	public double getForeign_Account() {
		return foreign_Account;
	}

	public double getNeutral_Account() {
		return neutral_Account;
	}

	public Player(String name) {
		this.name = name;
		this.points = 0;
		this.foreign_Account = 0;
		this.neutral_Account = 0;
		return;
	}
	
	public void initializeAccounts(int neutral, int foreign) throws IllegalArgumentException {
		if (neutral < 0 || foreign < 0) {
			throw new IllegalArgumentException("Neutral and Foreign values must be greater than 0.");
		}
		this.foreign_Account = foreign;
		this.neutral_Account = neutral;
		return;
	}
	
	public boolean removeFromNeutral(double amount) {
		if (amount > this.neutral_Account) {
			return false;
		}
		else {
			this.neutral_Account -= amount;
			return true;
		}
	}
	
	public boolean removeFromForeign(double amount) {
		if (amount > this.foreign_Account) {
			return false;
		}
		else {
			this.foreign_Account -= amount;
			return true;
		}
	}
	
	public void addToNeutral(double amount) {
		this.neutral_Account += amount;
		return;
	}
	
	public int compareTo(Player other) {
		return this.points > other.points ? -1 : this.points< other.points ? 1 : 0;
    }
	
	@Override
	public String toString() {
		return this.name + " " + Double.toString(this.points);
	}
		
}
	
	

