package com.teamtreehouse.model;

import java.util.ArrayList;
import java.util.List;

public class Team implements Comparable<Team>{
	
	private List<Team> mTeamList = new ArrayList<Team>();
	
	private String mCoachName;
	private String mTeamName;
	
	public Team(String coachName, String teamName) {
		mCoachName = coachName;
		mTeamName = teamName;
	}
	
	public String getTeamName() {
		return mTeamName;
	}
	
	public String getCoachName() {
		return mCoachName;
	}
	
	public void setTeamName(String newTeamName) {
		mTeamName = newTeamName;
	}
	
	public void setCoachName(String newCoachName) {
		mCoachName = newCoachName;
	}
	
	public String getTeam() {
		String team = "Team: " + mTeamName + ", coached by: " + mCoachName;
		return team;
	}
	
	@Override 
	public String toString() {
		return getTeamName();
	}

	@Override
	public int compareTo(Team other) {
		int compareName = this.getTeamName().compareTo(other.getTeamName());
		return compareName;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mCoachName == null) ? 0 : mCoachName.hashCode());
		result = prime * result + ((mTeamName == null) ? 0 : mTeamName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team) obj;
		if (mCoachName == null) {
			if (other.mCoachName != null)
				return false;
		} else if (!mCoachName.equals(other.mCoachName))
			return false;
		if (mTeamName == null) {
			if (other.mTeamName != null)
				return false;
		} else if (!mTeamName.equals(other.mTeamName))
			return false;
		return true;
	}

}
