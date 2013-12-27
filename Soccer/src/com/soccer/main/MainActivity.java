package com.soccer.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.soccer.constants.GameVars;
import com.soccer.database.DBHelper;

public class MainActivity extends Activity {

	private Vibrator vibe;
	private View newBtn, contBtn;
	private boolean gameExists = true;
	private DBThread fnameThread, lnameThread, cityThread, teamThread, nicknameThread = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    
	    // initialize buttons
	    contBtn = (View) findViewById(R.id.contGameBtn);
	    
	    // initialize vibration
	    vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
	    
		setContentView(R.layout.activity_main);
		
		// hide continue button if there is not an existing game
		if (!gameExists) {
			contBtn.setVisibility(View.INVISIBLE);
	    }
		
		// NEW THREAD - build first names database table
		fnameThread = new DBThread(this, GameVars.RES_FIRSTNAMES, GameVars.TABLE_FIRST_NAMES, GameVars.COLUMN_FIRST_NAME);
		fnameThread.start();
		
		// NEW THREAD - build first names database table
		lnameThread = new DBThread(this, GameVars.RES_LASTNAMES, GameVars.TABLE_LAST_NAMES, GameVars.COLUMN_LAST_NAME);
		lnameThread.start();
		
		// NEW THREAD - build city names database table
		cityThread = new DBThread(this, GameVars.RES_CITYNAMES, GameVars.TABLE_CITY_NAMES, GameVars.COLUMN_CITY_NAME);
		cityThread.start();
		
		// NEW THREAD - build city names database table
		teamThread = new DBThread(this, GameVars.RES_TEAMNAMES, GameVars.TABLE_TEAM_NAMES, GameVars.COLUMN_TEAM_NAME);
		teamThread.start();
		
		// NEW THREAD - build city names database table
		nicknameThread = new DBThread(this, GameVars.RES_NICKNAMES, GameVars.TABLE_NICKNAMES, GameVars.COLUMN_NICKNAME);
		nicknameThread.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void newGame(View view) {
		vibe.vibrate(GameVars.VIBRATE_TIME);
		newBtn = (View) findViewById(R.id.newGameBtn);
		newBtn.setBackgroundResource(R.drawable.btn_pressed);
		Intent intent = new Intent(this, NewGameOptions.class);
		startActivity(intent);
	}
	
	public void contGame(View view) {
		vibe.vibrate(GameVars.VIBRATE_TIME);
		contBtn = (View) findViewById(R.id.contGameBtn);
		contBtn.setBackgroundResource(R.drawable.btn_pressed);
//		Intent intent = new Intent(this, NewGameOptions.class);
//		startActivity(intent);
	}

	@Override
	protected void onPause() {
		
		// reset new game button
		newBtn = (View) findViewById(R.id.newGameBtn);
		newBtn.setBackgroundResource(R.drawable.btn_default);
		
		// reset continue button
		contBtn = (View) findViewById(R.id.contGameBtn);
		contBtn.setBackgroundResource(R.drawable.btn_default);
		
		// pause activity
		super.onPause();
	}

	// new thread to add first names to the first name table
	private static class DBThread extends Thread {
		private boolean isRunning = false;
		private String table, column;
		private int resource;
		private Context c;
		
		public DBThread(Context context, int resource, String table, String column) {
			this.c = context;
			this.resource = resource;
			this.table = table;
			this.column = column;
		}
		
		@Override
		public void run() {
			isRunning = true;
			while (isRunning) {
				// try first names db
				// variables
				InputStream input = null;
				BufferedReader reader = null;
				DBHelper db = new DBHelper(c);
				
				// check/populate first names table
				if (db.tableIsEmpty(table)) {
					Log.d("DB CHECK!!", "First Name table is not populated!");
					try {
						input = c.getResources().openRawResource(resource);
						reader = new BufferedReader(new InputStreamReader(input));
						Log.d("File Success!!!", "Reading file for " + table);
						String fn;
						while ((fn = reader.readLine()) != null) {
							String[] names = fn.split(",");
							for (int i = 0; i < names.length; i++) {
								// write first names to the database
								db.addName(table, column, names[i]);
							}
						}
					} catch(Exception e) {
						Log.d("Failure!!", "Could not read file for " + table);
					} finally {
						if (input != null) {
							try {
								input.close();
								Log.d("Success!!", "Input stream connection closed.");
							} catch (IOException e) {
								Log.d("Failure!!", "Problem closing input stream connection.");
							}
						}
						if (reader != null) {
							try {
								reader.close();
								Log.d("Success!!", "Buffered reader closed.");
							} catch (IOException e) {
								Log.d("Failure!!", "Problem closing buffered reader.");
							}
						}
						db.closeDB();
						Log.d("Success!!", "Database closed.");
					}
				} else {
					db.closeDB();
					Log.d("Success!!", "Database closed.");
					Log.d("DB CHECK!!", "First Name table is already populated!");
				}
				
				// end the first names thread
				this.close();
			}
		}
		
		public void close() {
			isRunning = false;
		}
	}
}