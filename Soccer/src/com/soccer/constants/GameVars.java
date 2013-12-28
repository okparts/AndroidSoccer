package com.soccer.constants;

import com.soccer.main.R;

public final class GameVars {
	
	// Vibration Constant Variables:
		public static final long VIBRATE_TIME = 50;
	
	// Database Variables:
		// general DB info
		public static final String DB_NAME = "soccer"; // database name
		public static final int DB_VER = 1; // database version
		public static final String LOG_TAG = "DB QUERY!!!"; // LogCat Tag name
		
		// table names
		public static final String TABLE_FIRST_NAMES = "first_names";
		public static final String TABLE_LAST_NAMES = "last_names";
		public static final String TABLE_CITY_NAMES = "city_names";
		public static final String TABLE_TEAM_NAMES = "team_names";
		public static final String TABLE_NICKNAMES = "nicknames";
		
		// common table column names
		public static final String COLUMN_ID = "_id";
		// private static final String COLUMN_CREATED_ON = "created_on";
		
		// FIRST_NAMES table - columns
		public static final String COLUMN_FIRST_NAME = "fname";
		
		// LAST_NAMES table - columns
		public static final String COLUMN_LAST_NAME = "lname";
		
		// CITY_NAMES table - columns
		public static final String COLUMN_CITY_NAME = "city";
		
		// TEAM_NAMES table - columns
		public static final String COLUMN_TEAM_NAME = "team";
		
		// CITY_NAMES table - columns
		public static final String COLUMN_NICKNAME = "nickname";
		
		// Context csv resources
		public static final int RES_FIRSTNAMES = R.raw.firstnames;
		public static final int RES_LASTNAMES = R.raw.lastnames;
		public static final int RES_CITYNAMES = R.raw.citynames;
		public static final int RES_TEAMNAMES = R.raw.teamnames;
		public static final int RES_NICKNAMES = R.raw.nicknames;
	
	// private constructor
	private GameVars() {
		throw new AssertionError();
	}

}
