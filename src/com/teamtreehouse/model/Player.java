package com.teamtreehouse.model;

import java.io.Serializable;

public class Player implements Comparable<Player>, Serializable {
	private static final long serialVersionUID = 1L;

	private String firstName;
	private String lastName;
	private int heightInInches;
	private boolean previousExperience;

	public Player(String firstName, String lastName, int heightInInches, boolean previousExperience) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.heightInInches = heightInInches;
		this.previousExperience = previousExperience;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getHeightInInches() {
		return heightInInches;
	}

	public boolean isPreviousExperience() {
		return previousExperience;
	}
	
	public String getPlayerStats() {
		String playerStats = null;
		if (previousExperience == false) {
			playerStats = lastName + ", " + firstName + ": Height - " + heightInInches + "in. New Player.\n"; 
		}
		else {
			playerStats = lastName + ", " + firstName + ": Height - " + heightInInches + "in. Experienced Player.\n"; 
		}
		
		return playerStats;
	}
	
	@Override
	public String toString() {
		return lastName + ", " + firstName + ": Height - " + heightInInches + "in. Experienced Player? " + previousExperience + " \n"; 
	}

	@Override
	public int compareTo(Player other) {
		int compareName = this.getLastName().compareTo(other.getLastName());
		return compareName;
		
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Player))
			return false;

		Player player = (Player) o;

		if (heightInInches != player.heightInInches)
			return false;
		if (previousExperience != player.previousExperience)
			return false;
		if (!firstName.equals(player.firstName))
			return false;
		return lastName.equals(player.lastName);

	}

	@Override
	public int hashCode() {
		int result = firstName.hashCode();
		result = 31 * result + lastName.hashCode();
		result = 31 * result + heightInInches;
		result = 31 * result + (previousExperience ? 1 : 0);
		return result;
	}
}
