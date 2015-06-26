
public class Smuggler implements java.io.Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Player person;
	private double briefcase;
	
	public Smuggler(Player person) {
		this.person = person;
		return;
	}
	
	public boolean smuggle(int amount) {
		if (amount > person.getForeign_Account() || amount < 0 || amount > 100) {
			return false;
		}
		else {
			this.briefcase = amount;
			person.removeFromForeign(amount);
			return true;
		}
	}
	
	public void success(double amount) {
		person.addToNeutral(amount + this.briefcase);
		return;
	}
	
	public void success() {
		person.addToNeutral(this.briefcase);
		return;
	}

	public Player getPerson() {
		return person;
	}

	public double getBriefcase() {
		return briefcase;
	}

	
}
