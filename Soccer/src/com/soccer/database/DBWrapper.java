package com.soccer.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBWrapper extends SQLiteOpenHelper {

	// class attributes
	public static final String TABLE_NAME = "LastNames";
	public static final String ID_COLUMN = "id";
	public static final String LAST_NAME_COLUMN = "lname";
	
	public static final String DB_NAME = "LastNames.db";
	private static final int DB_VERSION = 1;
	
	// statement to create the table
	private static final String DB_CREATE = "create table " + TABLE_NAME + "(" + ID_COLUMN + " integer primary key autoincrement, " + LAST_NAME_COLUMN + " text not null);"; 
	
	// constructor
	public DBWrapper(Context context) {
		
		super(context, DB_NAME, null, DB_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL(DB_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}

}