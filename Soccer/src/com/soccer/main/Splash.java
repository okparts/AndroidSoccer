package com.soccer.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.soccer.constants.GameVars;
import com.soccer.database.DBHelper;

public class Splash extends Activity {
	
	InputStream input = null;
	BufferedReader reader = null;
	DBHelper db = null;
	ProgressBar pb = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_splash);
		
		// TODO - implement database inits
		db = new DBHelper(this);
		pb = (ProgressBar) findViewById(R.id.splashProgress);
		int progress = 0;
		
		if (db.tableIsEmpty(GameVars.TABLE_FIRST_NAMES)) {
			initDB(R.raw.firstnames, GameVars.TABLE_FIRST_NAMES, GameVars.COLUMN_FIRST_NAME);
			Log.d("DATABASE!!!", "First names has been populated!!");
		} else {
			Log.d("DATABASE!!!", "First names already populated!!");
		}
		// advance the loader progress bar
		progress += 20;
		pb.setProgress(progress);
		
		if (db.tableIsEmpty(GameVars.TABLE_LAST_NAMES)) {
			initDB(R.raw.lastnames, GameVars.TABLE_LAST_NAMES, GameVars.COLUMN_LAST_NAME);
			Log.d("DATABASE!!!", "Last names has been populated!!");
		} else {
			Log.d("DATABASE!!!", "Last names already populated!!");
		}
		// advance the loader progress bar
		progress += 20;
		pb.setProgress(progress);
		
		if (db.tableIsEmpty(GameVars.TABLE_CITY_NAMES)) {
			initDB(R.raw.citynames, GameVars.TABLE_CITY_NAMES, GameVars.COLUMN_CITY_NAME);
			Log.d("DATABASE!!!", "City names has been populated!!");
		} else {
			Log.d("DATABASE!!!", "City names already populated!!");
		}
		// advance the loader progress bar
		progress += 20;
		pb.setProgress(progress);
		
		if (db.tableIsEmpty(GameVars.TABLE_TEAM_NAMES)) {
			initDB(R.raw.teamnames, GameVars.TABLE_TEAM_NAMES, GameVars.COLUMN_TEAM_NAME);
			Log.d("DATABASE!!!", "Team names has been populated!!");
		} else {
			Log.d("DATABASE!!!", "Team names already populated!!");
		}
		// advance the loader progress bar
		progress += 20;
		pb.setProgress(progress);
		
		if (db.tableIsEmpty(GameVars.TABLE_NICKNAMES)) {
			initDB(R.raw.nicknames, GameVars.TABLE_NICKNAMES, GameVars.COLUMN_NICKNAME);
			Log.d("DATABASE!!!", "Team names has been populated!!");
		} else {
			Log.d("DATABASE!!!", "Team names already populated!!");
		}
		// advance the loader progress bar
		progress += 20;
		pb.setProgress(progress);
		
		db.close();
		
		// continue to the main menu screen
		Intent intent = new Intent(this, NewGameOptions.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}
	
	private void initDB(int resource, String table, String column) {
		try {
			input = this.getResources().openRawResource(resource);
			Log.d("TRACING!!", "STEP 1");
			reader = new BufferedReader(new InputStreamReader(input));
			Log.d("TRACING!!", "STEP 2");
			Log.d("File Success!!!", "Reading file for " + table);
			Log.d("TRACING!!", "STEP 3");
			String fn;
			Log.d("TRACING!!", "STEP 4");
			while ((fn = reader.readLine()) != null) {
				String[] names = fn.split(",");
				Log.d("TRACING!!", "STEP 5 - Splitting File");
				for (int i = 0; i < names.length; i++) {
					// write first names to the database
					db.addName(table, column, names[i]);
				}
				Log.d("TRACING!!", "STEP 6 - Finished File");
			}
		} catch(Exception e) {
			Log.d("Failure!!", "Could not read file for " + table);
		} finally {
			if (input != null) {
				try {
					input.close();
					Log.d("TRACING!!", "STEP 7 - INPUT CLOSE");
					Log.d("Success!!", "Input stream connection closed.");
				} catch (IOException e) {
					Log.d("Failure!!", "Problem closing input stream connection.");
				}
			}
			if (reader != null) {
				try {
					reader.close();
					Log.d("TRACING!!", "STEP 8 - READER CLOSE");
					Log.d("Success!!", "Buffered reader closed.");
				} catch (IOException e) {
					Log.d("Failure!!", "Problem closing buffered reader.");
				}
			}
		}
	}

}
