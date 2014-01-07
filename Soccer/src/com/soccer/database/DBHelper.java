package com.soccer.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.soccer.constants.GameVars;

public class DBHelper extends SQLiteOpenHelper {

	// Table Create Statements
	// MANAGER table create statement
	private static final String CREATE_TABLE_MANAGER = "CREATE TABLE IF NOT EXISTS " + GameVars.TABLE_MANAGER + "(" 
			+ GameVars.COLUMN_ID + " INTEGER PRIMARY KEY,"
			+ GameVars.COLUMN_MANAGER_NAME + " TEXT,"
			+ GameVars.COLUMN_SEASONS + " INTEGER,"
			+ GameVars.COLUMN_TEAM_ID + " TEXT,"
			+ GameVars.COLUMN_WIN + " INTEGER,"
			+ GameVars.COLUMN_LOSS + " INTEGER,"
			+ GameVars.COLUMN_DRAW + " INTEGER"
			+ ")";
	
	// TEAMS table create statement
	private static final String CREATE_TABLE_TEAMS = "CREATE TABLE IF NOT EXISTS " + GameVars.TABLE_TEAMS + "("
			+ GameVars.COLUMN_ID + " INTEGER PRIMARY KEY,"
			+ GameVars.COLUMN_TEAM_CITY + " TEXT,"
			+ GameVars.COLUMN_TEAM_NAME + " TEXT,"
			+ GameVars.COLUMN_TEAM_NICKNAME + " TEXT,"
			+ GameVars.COLUMN_TEAM_LEAGUE_ID + " INTEGER,"
			+ GameVars.COLUMN_TEAM_RANK + " INTEGER,"
			+ GameVars.COLUMN_WIN + " INTEGER,"
			+ GameVars.COLUMN_LOSS + " INTEGER,"
			+ GameVars.COLUMN_DRAW + " INTEGER,"
			+ GameVars.COLUMN_TEAM_GOALS_FOR + " INTEGER,"
			+ GameVars.COLUMN_TEAM_GOALS_AGAINST + " INTEGER,"
			+ GameVars.COLUMN_TEAM_GAMES_PLAYED + " INTEGER"
			+ ")";
	
	// PLAYERS table create statement
	private static final String CREATE_TABLE_PLAYERS = "CREATE TABLE IF NOT EXISTS " + GameVars.TABLE_PLAYERS + "("
			+ GameVars.COLUMN_ID + " INTEGER PRIMARY KEY,"
			+ GameVars.COLUMN_PLAYER_NAME + " TEXT,"
			+ GameVars.COLUMN_PLAYER_POSITION + " TEXT,"
			+ GameVars.COLUMN_TEAM_ID + " INTEGER,"
			+ GameVars.COLUMN_PLAYER_STRENGTH + " INTEGER,"
			+ GameVars.COLUMN_PLAYER_CONTROL + " INTEGER,"
			+ GameVars.COLUMN_PLAYER_SKILL + " INTEGER,"
			+ GameVars.COLUMN_PLAYER_FITNESS + " INTEGER,"
			+ GameVars.COLUMN_PLAYER_AGE + " INTEGER,"
			+ GameVars.COLUMN_PLAYER_CONTRACT_PERIOD + " INTEGER,"
			+ GameVars.COLUMN_PLAYER_VALUE + " INTEGER"
			+ ")";
	
	// constructor
	public DBHelper(Context context) {
		super(context, GameVars.DB_NAME, null, GameVars.DB_VER);
	}

	// overridden methods from SQLiteOpenHelper
	@Override
	public void onCreate(SQLiteDatabase db) {
		// create tables
		db.execSQL(CREATE_TABLE_MANAGER);
		db.execSQL(CREATE_TABLE_TEAMS);
		db.execSQL(CREATE_TABLE_PLAYERS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// drop old tables on upgrade
		db.execSQL("DROP TABLE IF EXISTS " + GameVars.TABLE_MANAGER);
		db.execSQL("DROP TABLE IF EXISTS " + GameVars.TABLE_TEAMS);
		db.execSQL("DROP TABLE IF EXISTS " + GameVars.TABLE_PLAYERS);
		
		// create new tables on upgrade
		onCreate(db);
	}
	
	/*
	 * check if the table is populated
	 * used for ANY table
	 */
	public boolean tableIsEmpty(String table) {
		boolean check = false;
		String checkDB = "SELECT COUNT(*) FROM " + table;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(checkDB, null);
		if (c != null) {
			c.moveToFirst();
			if (c.getInt(0) == 0) {
				check = true;
			}
		}
		return check;
	}
	
	/*
	 * add a manager to the table
	 */
	public long newManager(String name, int teamID) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(GameVars.COLUMN_MANAGER_NAME, name);
		cv.put(GameVars.COLUMN_SEASONS, 0);
		cv.put(GameVars.COLUMN_TEAM_ID, teamID);
		cv.put(GameVars.COLUMN_WIN, 0);
		cv.put(GameVars.COLUMN_LOSS, 0);
		cv.put(GameVars.COLUMN_DRAW, 0);
		long id = db.insert(GameVars.TABLE_MANAGER, null, cv);
		return id;
	}
	
	/*
	 * update the manager table
	 */
	public int updateManager(String key, int value) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(key, value);
		int rows = db.update(GameVars.TABLE_MANAGER, cv, null, null);
		return rows;
	}
	
	/*
	 * add a table name
	 * used for FIRST_NAMES, LAST_NAMES, CITY_NAMES, TEAM_NAMES, NICKNAMES
	 */
	public long addTeam(String city, String name, String nickname, Integer... values) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(GameVars.COLUMN_TEAM_CITY, city);
		cv.put(GameVars.COLUMN_TEAM_NAME, name);
		cv.put(GameVars.COLUMN_TEAM_NICKNAME, nickname);
		for (int i = 0; i < values.length; i++) {
			cv.put(GameVars.COLUMNS_INTEGER_TEAMS[i], values[i]);
		}
		long id = db.insert(GameVars.TABLE_TEAMS, null, cv);
		return id;
	}
	
	/*
	 * add a table name
	 * used for FIRST_NAMES, LAST_NAMES, CITY_NAMES, TEAM_NAMES, NICKNAMES
	 */
	public long addPlayer(String name, String position, Integer... values) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(GameVars.COLUMN_PLAYER_NAME, name);
		cv.put(GameVars.COLUMN_PLAYER_POSITION, position);
		for (int i = 0; i < values.length; i++) {
			cv.put(GameVars.COLUMNS_INTEGER_PLAYERS[i], values[i]);
		}
		long id = db.insert(GameVars.TABLE_PLAYERS, null, cv);
		return id;
	}
	
	/*
	 * add a table name
	 * used for FIRST_NAMES, LAST_NAMES, CITY_NAMES, TEAM_NAMES, NICKNAMES
	 */
	public long addName(String table, String column, String fn) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(column, fn);
		long id = db.insert(table, null, values);
		return id;
	}
	
	/*
	 * get a random name
	 * used for FIRST_NAMES, LAST_NAMES, CITY_NAMES, TEAM_NAMES, NICKNAMES
	 */
	public String getRandomName(String table, String column) {
		SQLiteDatabase db = this.getReadableDatabase();
		String selectRandom = "SELECT * FROM " + table + " ORDER BY RANDOM() LIMIT 1";
		Log.d(GameVars.LOG_TAG, selectRandom);
		
		Cursor c = db.rawQuery(selectRandom, null);
		
		if (c != null) {
			c.moveToFirst();
		}
		
		String fn = c.getString(c.getColumnIndex(column));
		return fn;
	}
	
	/*
	 * erase tables
	 */
	public void eraseTables() {
		// erase existing tables when creating a new game
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("DROP TABLE IF EXISTS " + GameVars.TABLE_MANAGER);
		db.execSQL("DROP TABLE IF EXISTS " + GameVars.TABLE_TEAMS);
		db.execSQL("DROP TABLE IF EXISTS " + GameVars.TABLE_PLAYERS);
	}
	
	/*
	 * close database
	 */
	public void closeDB() {
		SQLiteDatabase db = this.getReadableDatabase();
		if (db != null && db.isOpen()) {
			db.close();
		}
	}
}