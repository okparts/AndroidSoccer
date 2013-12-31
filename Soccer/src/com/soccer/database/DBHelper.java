package com.soccer.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.soccer.constants.GameVars;

public class DBHelper extends SQLiteOpenHelper {

	// class attributes:
	
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
	
	// LEAGUE table create statement
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
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// drop old tables on upgrade
		db.execSQL("DROP TABLE IF EXISTS " + GameVars.TABLE_MANAGER);
		db.execSQL("DROP TABLE IF EXISTS " + GameVars.TABLE_TEAMS);
		
		// create new tables on upgrade
		onCreate(db);
	}
	
	/*
	 * check if the table is populated
	 * used for FIRST_NAMES, LAST_NAMES, CITY_NAMES, TEAM_NAMES, NICKNAMES
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