package com.soccer.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBOperations {

	// class attributes
	private DBWrapper wrapper;
	private String[] TABLE_COLUMNS = { DBWrapper.ID_COLUMN, DBWrapper.LAST_NAME_COLUMN };
	private SQLiteDatabase db;
	
	public DBOperations(Context context) {
		
		wrapper = new DBWrapper(context);
	}
	
	public void open() throws SQLException {
		
		db = wrapper.getWritableDatabase();
	}
	
	public void close() {
		
		wrapper.close();
	}
	
	public void addLastName(String last) {
		
		ContentValues values = new ContentValues();
		values.put(DBWrapper.LAST_NAME_COLUMN, last);
	}
	
	public int getCount() {
		
		Cursor cursor = db.query(DBWrapper.TABLE_NAME, TABLE_COLUMNS, null, null, null, null, null);
		int count = cursor.getCount();
		cursor.close();
		return count;
	}
	
}