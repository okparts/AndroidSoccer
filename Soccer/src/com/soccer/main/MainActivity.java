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
	private FirstNamesThread fnThread;
	
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
		
		// build first names database table
		fnThread = new FirstNamesThread(this);
		fnThread.start();
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
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		fnThread.close();
	}

	private static class FirstNamesThread extends Thread {
		private boolean firstNamesIsRunning = false;
		private Context c;
		
		public FirstNamesThread(Context context) {
			this.c = context;
		}
		
		@Override
		public void run() {
			firstNamesIsRunning = true;
			while (firstNamesIsRunning) {
				// try first names db
				// variables
				InputStream input = null;
				BufferedReader reader = null;
				DBHelper db = new DBHelper(c);
				
				// check/populate first names table
				if (db.firstNameIsEmpty()) {
					Log.d("DB CHECK!!", "First Name table is not populated!");
					try {
						input = c.getResources().openRawResource(R.raw.firstnames);
						reader = new BufferedReader(new InputStreamReader(input));
						Log.d("File Success!!!", "firstnames.csv open");
						String fn;
						while ((fn = reader.readLine()) != null) {
							String[] names = fn.split(",");
							for (int i = 0; i < names.length; i++) {
								// write first names to the database
								db.createFirstName(names[i]);
							}
						}
					} catch(Exception e) {
						Log.d("Failure!!", "Could not read firstnames.csv file.");
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
			}
		}
		
		public void close() {
			firstNamesIsRunning = false;
		}
	}
	
}