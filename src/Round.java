
public class Round implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Smuggler smuggler;
	private Inspector inspector;
	
	public Round(Smuggler smuggler, Inspector inspector) {
		this.smuggler = smuggler;
		this.inspector = inspector;
	}
	
	private void resolve() {
		
		System.out.println("The smuggler has attempted to smuggle " + this.smuggler.getBriefcase());
		
		if (this.inspector.isPass()) {
			this.smuggler.success();
			System.out.println("Smuggler now has " + this.smuggler.getPerson().getNeutral_Account() + " in this Neutral Account");
		}
		else {
			if (this.smuggler.getBriefcase() == 0 && !(this.inspector.isPass())) {
				this.smuggler.success(this.inspector.getPenalty());
				System.out.println("Smuggler now has " + this.smuggler.getPerson().getNeutral_Account() + " in this Neutral Account");
			}
			else if (this.inspector.getDoubt() < this.smuggler.getBriefcase()) {
				this.smuggler.success(this.inspector.getPenalty());
				System.out.println("Smuggler now has " + this.smuggler.getPerson().getNeutral_Account() + " in this Neutral Account");
			}
			else if (this.inspector.getDoubt() >= this.smuggler.getBriefcase()) {
				this.inspector.success(this.smuggler.getBriefcase());
				System.out.println("Inspector now has " + this.inspector.getPerson().getNeutral_Account() + " in this Neutral Account");
			}
		}
		
		
		return;
	}
	
	public void run() {
		while (true) {
			System.out.println(String.format("Smuggler %s, how much do you want to smuggle? You currently have %.2f in your Foreign account and %.2f in your neutral account.", this.smuggler.getPerson().getName(), this.smuggler.getPerson().getForeign_Account(), this.smuggler.getPerson().getNeutral_Account())); //replace with client call
			int amount = IO.readInt();
			boolean success = this.smuggler.smuggle(amount);
			if (success) {
				break;
			}
			else {
				System.out.println("Please enter a different amount.");
			}
		}
		//Put bribe in here if we want to add it
		while (true) { //replace the input with client call
			System.out.println(String.format("Inspector %s, do you want to pass or doubt? You currently have %.2f in your Neutral Account. (For pass, type pass. For doubt, type 'doubt x', where x is the amount you want to doubt)", this.inspector.getPerson().getName(), this.inspector.getPerson().getNeutral_Account()));
			String ans = IO.readString();
			if (ans.compareToIgnoreCase("pass") == 0) {
				this.inspector.setPass();
				break;
			}
			else {
				String tempString[] = ans.split(" ");
				if (tempString[0].compareToIgnoreCase("doubt") != 0) {
					System.out.println("Sorry, you have to choose an option.");
					continue;
				}
				else {
					int amount = Integer.parseInt(tempString[1].replaceAll(",", ""));
					boolean success = this.inspector.setDoubt(amount);
					if (success) {
						break;
					}
					else {
						System.out.println("Sorry, cannot doubt for that much.");
						continue;
					}
				}
			}
		}
		
		this.resolve();
	}
	
}
