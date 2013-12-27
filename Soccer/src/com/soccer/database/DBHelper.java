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
	// FIRST_NAME table create statement
	private static final String CREATE_TABLE_FIRST_NAMES = "CREATE TABLE IF NOT EXISTS " + GameVars.TABLE_FIRST_NAMES + "(" + GameVars.COLUMN_ID + " INTEGER PRIMARY KEY,"
			+ GameVars.COLUMN_FIRST_NAME + " TEXT" + ")";
	
	// LAST_NAME table create statement
	private static final String CREATE_TABLE_LAST_NAMES = "CREATE TABLE IF NOT EXISTS " + GameVars.TABLE_LAST_NAMES + "(" + GameVars.COLUMN_ID + " INTEGER PRIMARY KEY,"
			+ GameVars.COLUMN_LAST_NAME + " TEXT" + ")";
	
	// CITY_NAME table create statement
	private static final String CREATE_TABLE_CITY_NAME = "CREATE TABLE IF NOT EXISTS " + GameVars.TABLE_CITY_NAMES + "(" + GameVars.COLUMN_ID + " INTEGER PRIMARY KEY,"
			+ GameVars.COLUMN_CITY_NAME + " TEXT" + ")";
	
	// TEAM_NAME table create statement
	private static final String CREATE_TABLE_TEAM_NAME = "CREATE TABLE IF NOT EXISTS " + GameVars.TABLE_TEAM_NAMES + "(" + GameVars.COLUMN_ID + " INTEGER PRIMARY KEY,"
			+ GameVars.COLUMN_TEAM_NAME + " TEXT" + ")";
	
	// NICKNAME table create statement
	private static final String CREATE_TABLE_NICKNAME = "CREATE TABLE IF NOT EXISTS " + GameVars.TABLE_NICKNAMES + "(" + GameVars.COLUMN_ID + " INTEGER PRIMARY KEY,"
			+ GameVars.COLUMN_NICKNAME + " TEXT" + ")";
	
	
	// constructor
	public DBHelper(Context context) {
		super(context, GameVars.DB_NAME, null, GameVars.DB_VER);
	}

	// overridden methods from SQLiteOpenHelper
	@Override
	public void onCreate(SQLiteDatabase db) {
		// create tables
		db.execSQL(CREATE_TABLE_FIRST_NAMES);
		db.execSQL(CREATE_TABLE_LAST_NAMES);
		db.execSQL(CREATE_TABLE_CITY_NAME);
		db.execSQL(CREATE_TABLE_TEAM_NAME);
		db.execSQL(CREATE_TABLE_NICKNAME);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// drop old tables on upgrade
		db.execSQL("DROP TABLE IF EXISTS " + GameVars.TABLE_FIRST_NAMES);
		db.execSQL("DROP TABLE IF EXISTS " + GameVars.TABLE_LAST_NAMES);
		db.execSQL("DROP TABLE IF EXISTS " + GameVars.TABLE_CITY_NAMES);
		db.execSQL("DROP TABLE IF EXISTS " + GameVars.TABLE_TEAM_NAMES);
		db.execSQL("DROP TABLE IF EXISTS " + GameVars.TABLE_NICKNAMES);
		
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
	 * close database
	 */
	public void closeDB() {
		SQLiteDatabase db = this.getReadableDatabase();
		if (db != null && db.isOpen()) {
			db.close();
		}
	}
}