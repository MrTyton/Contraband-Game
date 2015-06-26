
public class Inspector implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Player person;
	private double doubt;
	private boolean pass;
	private double penalty;
	
	public Inspector(Player person) {
		this.person = person;
		this.pass = false;
		this.penalty = 0;
		return;
	}
	
	public double getPenalty() {
		return penalty;
	}

	public boolean isPass() {
		return pass;
	}

	public void setPass() {
		this.pass = true;
	}

	public boolean setDoubt(double amount) {
		if (amount > 2 * this.person.getNeutral_Account() || amount <= 0 || amount > 100) {
			return false;
		}
		else {
			this.doubt = amount;
			this.penalty = amount / 2;
			this.person.removeFromNeutral(penalty);
			return true;
		}
	}

	public void success(double amount) {
		this.person.addToNeutral(this.penalty + amount);
		return;
	}
	
	public Player getPerson() {
		return person;
	}

	public double getDoubt() {
		return doubt;
	}

}
