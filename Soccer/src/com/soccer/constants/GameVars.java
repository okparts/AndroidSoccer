package com.soccer.constants;

public final class GameVars {
	
	// Vibration Constant Variables:
		public static final long VIBRATE_TIME = 50;
	
	// Database Variables:
		// general DB info
		public static final String DB_NAME = "soccer"; // database name
		public static final int DB_VER = 1; // database version
		public static final String LOG_TAG = "DB QUERY!!!"; // LogCat Tag name
		
		// table names
		public static final String TABLE_MANAGER = "manager";
		public static final String TABLE_TEAMS = "teams";
		
		// common table column names
		public static final String COLUMN_ID = "_id";
		public static final String COLUMN_TEAM_ID = "teamID";
		public static final String COLUMN_WIN = "win";
		public static final String COLUMN_LOSS = "loss";
		public static final String COLUMN_DRAW = "draw";
		public static final String COLUMN_SEASONS = "seasons";
		// private static final String COLUMN_CREATED_ON = "created_on";
		
		// MANAGER table - columns
		public static final String COLUMN_MANAGER_NAME = "managerName";
		
		// TEAMS table - columns
		public static final String COLUMN_TEAM_CITY = "city";
		public static final String COLUMN_TEAM_NAME = "name";
		public static final String COLUMN_TEAM_NICKNAME = "nickname";
		public static final String COLUMN_TEAM_LEAGUE_ID = "leagueID";
		public static final String COLUMN_TEAM_RANK = "rank";
		public static final String COLUMN_TEAM_GOALS_FOR = "goalsFor";
		public static final String COLUMN_TEAM_GOALS_AGAINST = "goalsAgainst";
		public static final String COLUMN_TEAM_GAMES_PLAYED = "gamesPlayed";
		
		// PLAYERS table - columns
		public static final String COLUMN_PLAYER_NAME = "name";
		public static final String COLUMN_PLAYER_POSITION = "position";
		public static final String COLUMN_PLAYER_TEAM_ID = "teamID";
		public static final String COLUMN_PLAYER_STRENGTH = "strength";
		public static final String COLUMN_PLAYER_CONTROL = "control";
		public static final String COLUMN_PLAYER_SKILL = "skill";
		public static final String COLUMN_PLAYER_FITNESS = "fitness";
		public static final String COLUMN_PLAYER_GOALKEEPING = "goalkeeping";
		public static final String COLUMN_PLAYER_AGE = "age";
		public static final String COLUMN_PLAYER_CONTRACT_PERIOD = "contractPeriod";
		public static final String COLUMN_PLAYER_VALUE = "value";
	
	// private constructor
	private GameVars() {
		throw new AssertionError();
	}

}