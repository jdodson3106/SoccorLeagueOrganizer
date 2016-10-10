package com.teamtreehouse.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Organizer {

	private static List<Player> mMasterPlayerList = new ArrayList<Player>();
	private static List<Team> mTeamList = new ArrayList<Team>();
	private static Set<Team> mTeamSet = new LinkedHashSet<Team>();
	private static Map<Team, List<Player>> mTeamRoster = new HashMap<Team, List<Player>>();

	public void getMenu() {
		Scanner in = new Scanner(System.in);
		int choice = 0;
		createMasterPlayerList();
		do {

			System.out.print("\nPlease select an option from the menu by typing the number:\n" + "1: Create new Team\n" + 
					"2: View Availiable Player List\n"+ "3: Add players to existing team\n" + "4: Remove Players from team roster\n" + 
					"5: View Team Reports\n" + "6: View Team Rosters\n"+ "9: Exit\n");

			choice = in.nextInt();
			switch (choice) {
			case 1:
				//This if/else statement controls the amount of teams we can create. 
				//
				if (mMasterPlayerList.size() >= 6 && mMasterPlayerList.size() >= mTeamList.size()) {
					mTeamSet.add(buildNewTeam()); // calls the method to build new team and adds that team to the master team list
				}
				else {
					System.out.println("Sorry, not enough players to form another Team.");
				}
					break;
			case 2:
				int counter = 1;
				for(Player player : mMasterPlayerList) {
					System.out.println(counter + ": " + player);
					counter++;
				}
				break;
			case 3:
				addPlayerToTeam();
//				teamSelector();
				
				break;
			case 4:
				removePlayers();
				break;
			case 5: // TODO: Team Reports.
					// To Include: teams grouped by height, and comparison of
					// experienced vs non experienced players.
				Scanner input = new Scanner(System.in);
				int selection = 0;
				do {
					System.out.println("Select the report type out would like to view:/n" + 
										"1: Players by height(shortest to tallest)/n" +
										"2: Players grouped by experience.\n" + 
										"3: Exit Reports");
					
					/*
					 * ******IN PROGRESS************
					 * A SWITCH STATMENT THAT WILL PRODUCE THE CERTAIN REPORT THE ORGANIZER WANTS TO RUN
					 */
					switch(choice) {
					case 1:
						// sort players by height
						
						break;
						// group players by experience
					case 2:
						break;
					case 3:
						// break to exit switch statement and break the loop
						break;
					}
				}while(selection != 3);
				break;
			case 6: 
				getRoster();
				// TODO: Coach report to view team roster.
				break;
			case 9:
				break;
			default:
				System.out.println("it no worky...give it another go.");
			}

		} while (choice != 9);
	}

	/*
	 * This method offers the user to build a new team object
	 */
	private static Team buildNewTeam() {
		Scanner in = new Scanner(System.in);
		System.out.println("What is the team name?");
		String teamName = in.nextLine();
		System.out.println("What is the coach's name?");
		String coachName = in.nextLine();
		
		return new Team(coachName, teamName);
	}

	/*
	 * This loads the Team[] array and puts it in the master team set and displays 
	 * the list of available teams to the user. 
	 */
	private List<Team> getTeams() {
		Team[] teams = Teams.load();
		List<Team> teamList = new ArrayList<Team>(mTeamSet);
		for (Team team : teams) {
			teamList.add(team);
		}
		Collections.sort(teamList);
		int counter = 0;
		for (Team team : teamList) {
			counter++;
			System.out.println(counter + ": " + team.getTeam());
		}
		mTeamList = teamList;
		return teamList;
	}

	/*
	 * This returns the full sorted master list of all players that are available that can
	 * be added to a team. 
	 */
	private static List<Player> createMasterPlayerList() {
		Player[] newPlayer = Players.load();
		for (Player player : newPlayer) {
			mMasterPlayerList.add(player);
		}
		Collections.sort(mMasterPlayerList);
		return mMasterPlayerList;
	}
	

	
	/*
	 * This is a selection for choosing the team to add players to. this calls 
	 * the getTeams() method to display the list of teams.
	 */
	private Team teamSelector() {
		Scanner in = new Scanner(System.in);
		System.out.println("Select the team you would like to edit: ");
		this.getTeams();
		int userChoice = in.nextInt();
		Team teamSelection = mTeamList.get(userChoice - 1);
		return teamSelection;
	}

	/*
	 * THIS METHOD WILL BE USED TO CHOOSE THE TEAM TO ALTER, DISPLAY THE TEAM LIST AND ADD TEAMS AS WANTED.
	 * THIS METHOD NEEDS TO:
	 * 		1.) PUT THE SELECTED TEAM INTO A LOCAL VARIABLE
	 * 		2.) ADD SELECTED PLAYERS INTO A TEMP LIST (BY LOOPING THROUGH AN OPTION MENU)
	 * 		3.) REMOVE THE SELECTED PLAYERS FROM THE MASTER PLAYER LIST
	 * 		4.) PUT THE TEAM VARIABLE AND TEMP PLAYER LIST INTO THE MASTER ROSTER MAP 
	 */
	private void addPlayerToTeam() {
		Scanner in = new Scanner(System.in);
		List<Player> teamPlayerList = new ArrayList<Player>();	
		Team team = teamSelector(); 
		List<Player> tempList = new ArrayList<Player>(mMasterPlayerList);
		int selector = 0;
		do {
			System.out.println("1: Select player to add to team: ");
			System.out.println("2: Done editing team:");
			selector = in.nextInt();
			switch(selector) {
			case 1: 
				int counter = 0;
				for (Player player : tempList) { 
					counter++;
					System.out.println(counter + ":" + player.getPlayerStats());
				}
				int userChoice = in.nextInt();
				Player selectedPlayer = tempList.get(userChoice - 1);
				teamPlayerList.add(selectedPlayer);
				tempList.remove(userChoice - 1);
				mMasterPlayerList.remove(userChoice - 1);
				break;
			case 2:
				break;
			}
		} while(selector != 2);
		mTeamRoster.put(team, teamPlayerList);
	}
	
	/*
	 * This method will allow players to be removed from team and placed back in
	 * the master player list.
	 * 
	 * THIS METHOD NEEDS TO:
	 * 		1.) PUT THE SELECTED TEAM INTO A LOCAL VARIABLE
	 * 		2.)	DISPLAY THE PLAYERS OF THAT TEAM
	 * 		3.)	WHEN THE PLAYER IS SELECTED, THE PLAYER WILL BE 
	 * 			REMOVED FROM THAT ROSTER AND ADDED BACK TO THE MASTER PLAYER LIST
	 */
	private void removePlayers() {
		Scanner in = new Scanner(System.in);
		Scanner input = new Scanner(System.in);
		Team team = teamSelector();
		List<Player> playerList = mTeamRoster.get(team);
		int counter = 1;
		for(Player player : playerList) {
			System.out.println(counter + ": " + player);
			counter++;
		}
		char choice = 'x';
		do{
			System.out.println("Select the player number you want to remove from team (press 'x' to exit): ");
			int playerChoice = input.nextInt();
			Player selectedPlayer = playerList.get(playerChoice - 1); 
			playerList.remove(playerChoice - 1);
			mMasterPlayerList.add(selectedPlayer);
			Collections.sort(mMasterPlayerList);
			
			
		}while(choice != 'x');
	}
	
	public void getRoster() {
		for(Map.Entry<Team, List<Player>> entry : mTeamRoster.entrySet()) {
			System.out.println("Team : " + entry.getKey() + " \nPlayers : " + entry.getValue());
		}
	}

	/*
	 * TODO: create comparator class to sort the teams alphabetically. Once they
	 * are sorted we can then utilize their / new index location to assign the
	 * chosen team to a map. I can then use the same logic to alphabatize the
	 * players, create an empty list for each team roster, add the players to
	 * the designated roster List that is joined to the team, and add that list
	 * to the Roster map as the player list.
	 */
}