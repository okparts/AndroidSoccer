package com.soccer.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	// class attributes:
	
	// general DB info
	private static final String DB_NAME = "soccer"; // database name
	private static final int DB_VER = 1; // database version
	private static final String LOG_TAG = "DATABASE!!!"; // LogCat Tag name
	
	// table names
	private static final String TABLE_FIRST_NAMES = "first_names";
	private static final String TABLE_LAST_NAMES = "last_names";
	
	// common table columns
	private static final String COLUMN_ID = "_id";
	private static final String COLUMN_CREATED_ON = "created_on";
	
	// FIRST_NAMES table - columns
	private static final String COLUMN_FIRST_NAME = "fname";
	
	// LAST_NAMES table - columns
	private static final String COLUMN_LAST_NAME = "lname";
	
	
	// Table Create Statements
	// FIRST_NAME table create statement
	private static final String CREATE_TABLE_FIRST_NAMES = "CREATE TABLE IF NOT EXISTS " + TABLE_FIRST_NAMES + "(" + COLUMN_ID + " INTEGER PRIMARY KEY,"
			+ COLUMN_FIRST_NAME + " TEXT" + ")";
	
	private static final String CREATE_TABLE_LAST_NAMES = "CREATE TABLE IF NOT EXISTS " + TABLE_LAST_NAMES + "(" + COLUMN_ID + " INTEGER PRIMARY KEY,"
			+ COLUMN_LAST_NAME + " TEXT" + ")";
	
	
	// constructor
	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VER);
	}

	// overridden methods from SQLiteOpenHelper
	@Override
	public void onCreate(SQLiteDatabase db) {
		// create tables
		db.execSQL(CREATE_TABLE_FIRST_NAMES);
		db.execSQL(CREATE_TABLE_LAST_NAMES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// drop old tables on upgrade
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FIRST_NAMES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LAST_NAMES);
		
		// create new tables on upgrade
		onCreate(db);
	}
	
	/*
	 * add a first name
	 */
	public long createFirstName(String fn) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COLUMN_FIRST_NAME, fn);
		long id = db.insert(TABLE_FIRST_NAMES, null, values);
		return id;
	}
	
	/*
	 * get a random first name
	 */
	public String getRandomFirstName() {
		SQLiteDatabase db = this.getReadableDatabase();
		String selectRandom = "SELECT * FROM " + TABLE_FIRST_NAMES + " ORDER BY RANDOM() LIMIT 1";
		Log.e(LOG_TAG, selectRandom);
		
		Cursor c = db.rawQuery(selectRandom, null);
		
		if (c != null) {
			c.moveToFirst();
		}
		
		String fn = c.getString(c.getColumnIndex(COLUMN_FIRST_NAME));
		return fn;
	}
	
	/*
	 * add a last name
	 */
	public long createLastName(String ln) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COLUMN_LAST_NAME, ln);
		long id = db.insert(TABLE_LAST_NAMES, null, values);
		return id;
	}
	
	/*
	 * get a random last name
	 */
	public String getRandomLastName() {
		SQLiteDatabase db = this.getReadableDatabase();
		String selectRandom = "SELECT * FROM " + TABLE_LAST_NAMES + " ORDER BY RANDOM() LIMIT 1";
		Log.e(LOG_TAG, selectRandom);
		
		Cursor c = db.rawQuery(selectRandom, null);
		
		if (c != null) {
			c.moveToFirst();
		}
		
		String ln = c.getString(c.getColumnIndex(COLUMN_LAST_NAME));
		return ln;
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
