
public class Scorekeeping {
	 
	 private static void finalCleanups(Team first, Team second) {
		 double first_sum = 0;
		 for (Player current : second.getPlayers()) {
			 double amount = current.getForeign_Account();
			 first_sum += amount;
			 current.removeFromForeign(amount);
		 }
		 double second_sum = 0;
		 for (Player current : first.getPlayers()) {
			 double amount = current.getForeign_Account();
			 second_sum += amount;
			 current.removeFromForeign(amount);
		 }
		 
		 first_sum /= first.getPlayers().size();
		 second_sum /= second.getPlayers().size();
		 
		 for (Player current : first.getPlayers()) {
			 current.addToNeutral(first_sum);
		 }
		 for (Player current : second.getPlayers()) {
			 current.addToNeutral(second_sum);
		 }
		 return;
	 }
	 
	 public static int whoWon(Team first, Team second) {
		 finalCleanups(first, second);
		 double first_score = first.calculateTeamScore();
		 double second_score = second.calculateTeamScore();
		 
		 return first_score > second_score ? 1 : first_score < second_score ? -1 : 0;

		 
	 }
	
	 public static void updateScores(Team winner, Team loser, double winner_multiplier, double loser_multiplier, double bank_multiplier) { //BE CAREFUL ABOUT THIS
		 double winner_score = winner.calculateTeamScore() / winner.getPlayers().size();
		 for (Player current : winner.getPlayers()) {
			 current.addPoints(current.getNeutral_Account() * bank_multiplier + winner_score * winner_multiplier);
		 }
		 double loser_score = loser.calculateTeamScore() / loser.getPlayers().size();
		 for (Player current : loser.getPlayers()) {
			current.addPoints(current.getNeutral_Account() * bank_multiplier + loser_score * loser_multiplier);
		 }
		 return;
	 }	 
}
