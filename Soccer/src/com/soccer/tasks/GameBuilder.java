package com.soccer.tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.soccer.database.DBHelper;
import com.soccer.models.Player;
import com.soccer.models.Team;
import com.soccer.utils.NameGen;

public class GameBuilder extends AsyncTask<Void, Void, Boolean> {

	// builder utilities
	private static NameGen ng = new NameGen();
	private DBHelper db = null;
	private Player player = null;
	private Team team = null;
	private Context context = null;
	
	private static final int TOTAL_PLAYERS = 3600;
	private static final int TOTAL_TEAMS = 20;
	private static final int PLAYERS_PER_TEAM = 18;
	private int playerCounter = 0;
	private String teamCity = "";
	private String teamName = "";
	private String teamNickname = "";
	private String playerName = "";
	private String mgrName = "";
	private int teamID = 0;
	private static final String[] POSITIONS = {"Goalkeeper", "Goalkeeper", "Striker", "Striker", "Striker", "Striker", "Striker", "Midfield", "Midfield", "Midfield", "Midfield", "Midfield", "Midfield", "Defender", "Defender", "Defender", "Defender", "Defender", "Defender"};
	 
	public GameBuilder(Context context, String mgrName) {
		this.context = context;
		this.mgrName = mgrName;
	}
	
	@Override
	protected Boolean doInBackground(Void... params) {

		/*
		 * TODO List
		 * 
		 * 1. build the teams and players
		 * 2. implement new DBHelper methods to handle creating team, player, manager tables
		 * 2. write the teams, players, manager data to sqlite tables
		 */
		
		// #1 - Build Team and Players
		db = new DBHelper(context);
		
		for (int i = 0; i < TOTAL_PLAYERS; i++) {
			if (playerCounter == PLAYERS_PER_TEAM) {
				playerCounter = 0;
				team = new Team(context, ng);
				teamCity = team.getCity();
				teamName = team.getName();
				teamNickname = team.getNickname();
				teamID = (int) db.newManager(mgrName, teamID);
			}
		}
		
		return true;
	}
}