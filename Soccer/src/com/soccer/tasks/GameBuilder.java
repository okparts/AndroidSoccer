package com.soccer.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.soccer.database.DBHelper;
import com.soccer.models.Player;
import com.soccer.models.Team;
import com.soccer.utils.NameGen;

public class GameBuilder extends AsyncTask<String, Integer, Boolean> {

	// builder utilities
	private static NameGen ng = new NameGen();
	private DBHelper db = null;
	private Player player = null;
	private Team team = null;
	private Context context = null;
	private ProgressBar loader = null;
	private LinearLayout contBtn = null;
	
	// utility variables
	private static final int LEAGUE_TIERS = 10;
	private static final int PLAYERS_PER_TEAM = 18;
	private static final int TEAMS_PER_LEAGUE = 20;
	private static final String[] POSITIONS = {"Goalkeeper", "Goalkeeper", "Striker", "Striker", "Striker", "Striker", "Midfield", "Midfield", "Midfield", "Midfield", "Midfield", "Midfield", "Defender", "Defender", "Defender", "Defender", "Defender", "Defender"};
	private int leagueTier = 0;
	private int teamID = 0;
	private int positionIndex = 0;
	private int progressCounter = 0;
	private String teamCity = "";
	private String teamName = "";
	private String teamNickname = "";
	private String playerName = "";
	private String playerPosition = "";
	private String mgrName = "";
	private Integer[] teamIntValues = {0, 0, 0, 0, 0, 0, 0, 0};
	private Integer[] playerIntValues = {0, 0, 0, 0, 0, 0, 0, 0};
	
	 
	public GameBuilder(Context context, String mgrName, ProgressBar loader, LinearLayout contBtn) {
		this.context = context;
		this.mgrName = mgrName;
		this.loader = loader;
		this.contBtn = contBtn;
	}
	
	@Override
	protected Boolean doInBackground(String... params) {

		/*
		 * 1. build the teams and players
		 * 2. write the teams, players, manager data to sqlite tables
		 */
		
		// #1 - Build Team and Players
		db = new DBHelper(context);
		
		// outermost loop builds the 10 leagues
		// starting with premier (tier 10) and ending on the lowest level league (tier 1)
		// i = league tier
		for (int i = LEAGUE_TIERS; i > 0; i--) {
			leagueTier = i;
			
			// middle inner loop builds the 20 teams in each league
			// j = team
			for (int j = 0; j < TEAMS_PER_LEAGUE; j++) {
				// build new team object
				team = new Team(context, ng);
				teamCity = team.getCity();
				teamName = team.getName();
				teamNickname = team.getNickname();
				teamIntValues[0] = leagueTier;
				
				// write new team to database
				// capture new team database ID
				teamID = (int) db.newTeam(teamCity, teamName, teamNickname, teamIntValues);
				
				// build manager for the last team on tier 1
				if (leagueTier == 1 && j == (TEAMS_PER_LEAGUE - 1)) {
					// build the user's manager
					db.newManager(mgrName, teamID);
				}
				
				// increment the progress bar value;
				progressCounter += 10;
				
				// final inner loop builds the 18 players for each team
				// k = player position index
				for (int k = 0; k < PLAYERS_PER_TEAM; k++) {
					positionIndex = k;
					
					// build new player object
					player = new Player(ng, POSITIONS[positionIndex], leagueTier);
					playerName = player.getName();
					playerPosition = player.getPosition();
					playerIntValues[0] = teamID;
					playerIntValues[1] = player.getStrength();
					playerIntValues[2] = player.getControl();
					playerIntValues[3] = player.getSkill();
					playerIntValues[4] = player.getFitness();
					playerIntValues[5] = player.getAge();
					playerIntValues[6] = player.getContractPeriod();
					playerIntValues[7] = player.getValue();
					
					// write new player to database
					db.addPlayer(playerName, playerPosition, playerIntValues);
				}
				
				// update progress bar
				publishProgress(progressCounter);
			}
		}
		
		return true;
	}

	@Override
	protected void onProgressUpdate(Integer... progress) {
		super.onProgressUpdate(progress[0]);
		loader.setProgress(progress[0]);
	}

	@Override
	protected void onPostExecute(Boolean result) {
		// process result from async 
		loader.setVisibility(View.INVISIBLE);
		contBtn.setVisibility(View.VISIBLE);
		super.onPostExecute(result);
	}
}