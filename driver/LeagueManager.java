package driver;

import com.teamtreehouse.model.Organizer;
import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Team;
import com.teamtreehouse.model.Teams;

public class LeagueManager {

	public static void main(String[] args) {
		Player[] players = Players.load();
		Team[] teams = Teams.load();
		System.out.printf("%nThere are currently %d league teams ready to accept new players.%nThey are:%n", teams.length);
		for(Team team : teams) {
			System.out.println("The \"" + team.toString() + "\"");	
		}
		System.out.printf("\nThere are currently %d registered players ready to be drafted to teams.%n", players.length);
		Organizer organizer = new Organizer();
		organizer.getMenu();
	}

}
