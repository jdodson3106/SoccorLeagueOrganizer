package com.teamtreehouse.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Organizer {

	private static List<Player> mMasterPlayerList = new ArrayList<Player>();
	private List<Team> mTeamList = new ArrayList<Team>();
	private Set<Team> mTeamSet = new LinkedHashSet<Team>();
	private Map<Team, List<Player>> mTeamRoster = new HashMap<Team, List<Player>>();

	// this is the menu method that will display the menu to the user
	// this is where the program is controlled. (for now)
	public void getMenu() {
		Scanner in = new Scanner(System.in);
		int choice = 0;
		createMasterPlayerList();
		do {

			System.out.print("\nPlease select an option from the menu by typing the number:\n" + "1: Create new Team\n"
					+ "2: View Availiable Player List\n" + "3: Add players to existing team\n"
					+ "4: Remove Players from team roster\n" + "5: View Team Reports\n" + "6: View Team Rosters\n"
					+ "9: Exit\n");

			choice = in.nextInt();
			switch (choice) {

			case 1:
				// This if/else statement controls the amount of teams we can
				// create.
				if (mMasterPlayerList.size() >= 6 && mMasterPlayerList.size() >= mTeamList.size()) {
					mTeamSet.add(buildNewTeam());
				} else {
					System.out.println("Sorry, not enough players to form another Team.");
				}
				break;

			case 2:
				int counter = 1;
				for (Player player : mMasterPlayerList) {
					System.out.println(counter + ": " + player);
					counter++;
				}
				break;

			case 3:
				addPlayerToTeam();
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
					System.out.println("\n" + "Select the report type out would like to view:\n"
							+ "1: Players by height(shortest to tallest)\n" + "2: Players grouped by experience.\n"
							+ "3: Exit Reports");
					selection = in.nextInt();

					/*
					 * ******IN PROGRESS************ A SWITCH STATMENT THAT WILL
					 * PRODUCE THE CERTAIN REPORT THE ORGANIZER WANTS TO RUN
					 */
					switch (selection) {

					case 1:
						getByHeight();
						break;

					case 2:
						isExperienced();
						break;
					case 3:
						break;

					default:
						System.out.println("Selection Error. Try another menu option.");
					}

				} while (selection != 3);
				break;

			case 6:
				getRoster();
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
	public Team buildNewTeam() {
		Scanner in = new Scanner(System.in);
		System.out.println("What is the team name?");

		String teamName = in.nextLine();
		System.out.println("What is the coach's name?");

		String coachName = in.nextLine();

		return new Team(coachName, teamName);
	}

	/*
	 * This loads the Team[] array and puts it in the master team set and
	 * displays the list of available teams to the user.
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
	 * This returns the full sorted master list of all players that are
	 * available that can be added to a team.
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
	public Team teamSelector() {
		Scanner in = new Scanner(System.in);
		System.out.println("Select the team you would like to edit: ");
		this.getTeams();
		int userChoice = in.nextInt();
		Team teamSelection = mTeamList.get(userChoice - 1);
		return teamSelection;
	}

	/*
	 * This method adds players to a selected team
	 */
	public void addPlayerToTeam() {
		Scanner in = new Scanner(System.in);
		List<Player> teamPlayerList = new ArrayList<Player>();
		Team team = teamSelector();
		List<Player> tempList = new ArrayList<Player>(mMasterPlayerList);

		int selector = 0;
		do {
			System.out.println("1: Select player to add to team: ");
			System.out.println("2: Done editing team:");
			selector = in.nextInt();

			switch (selector) {
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
		} while (selector != 2);
		mTeamRoster.put(team, teamPlayerList);
	}

	/*
	 * This method will allow players to be removed from team and placed back in
	 * the master player list.
	 */
	private void removePlayers() {
		Scanner in = new Scanner(System.in);
		Scanner input = new Scanner(System.in);
		Team team = teamSelector();
		List<Player> playerList = mTeamRoster.get(team);

		int choice = 0;
		do {
			System.out.println("1: Select player to remove.");
			System.out.println("2: Done removing player(s)");

			choice = in.nextInt();

			switch (choice) {
			case 1:
				int counter = 1;
				for (Player player : playerList) {
					System.out.println(counter + ": " + player);
					counter++;
				}
				System.out.println("Select the player number you want to remove from team (press 'x' to exit): ");

				int playerChoice = input.nextInt();

				Player selectedPlayer = playerList.get(playerChoice - 1);

				playerList.remove(playerChoice - 1);

				mMasterPlayerList.add(selectedPlayer);

				Collections.sort(mMasterPlayerList);
				break;

			case 2:
				break;
			}

		} while (choice != 2);
	}

	public void getRoster() {
		for (Map.Entry<Team, List<Player>> entry : mTeamRoster.entrySet()) {
			System.out.println("Team : " + entry.getKey() + " \nPlayers : " + entry.getValue());
		}
	}

	// This method sorts the players on a selected team by height
	public void getByHeight() {
		Team team = teamSelector();
		List<Player> playerList = mTeamRoster.get(team);
		
		// selection sort for loop that goes through the list and 
		// swaps the elements with smaller elements (based on height) 
		// until the list is sorted.
		int smallHeight = 0;
		int j = 0;
		int smallIntIndex = 0;

		for (int i = 1; i < playerList.size(); i++) {

			smallHeight = playerList.get(i - 1).getHeightInInches();
			smallIntIndex = i - 1;

			for (j = i; j < playerList.size(); j++) {
				if (playerList.get(j).getHeightInInches() < smallHeight) {
					smallHeight = playerList.get(j).getHeightInInches();
					smallIntIndex = j;
				}
			}
			Player temp = playerList.get(smallIntIndex);
			playerList.set(smallIntIndex, playerList.get(i - 1));
			playerList.set(i - 1, temp);
		}
		int counter = 1;
		for (Player player : playerList) {
			System.out.println(counter + ": " + player);
			counter++;
		}
	}

	// This method gives a count, for each team, the amount of experienced and
	// inexperienced players.
	public void isExperienced() {

		for (Team team : mTeamList) {
			List<Player> playerList = mTeamRoster.get(team);
			int experiencedCount = 0;
			int inexperiencedCount = 0;

			for (Player player : playerList) {
				if (player.isPreviousExperience()) {
					experiencedCount++;
				} else {
					inexperiencedCount++;
				}
			}

			System.out.println("\n" + team + ":\nExperienced players: " + experiencedCount + "\nInexperienced players: "
					+ inexperiencedCount);
		}

	}

}