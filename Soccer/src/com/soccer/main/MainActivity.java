package com.soccer.main;

import java.io.BufferedReader;
import java.io.File;
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
import com.soccer.database.DBOperations;

public class MainActivity extends Activity {

	private Vibrator vibe;
	private View newBtn, contBtn;
	private boolean gameExists = true;
	
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
		
		// try db shit
		InputStream input = null;
		BufferedReader reader = null;
		DBOperations db = new DBOperations(this);
		File dbCheck = new File("/data/data/com.soccer.main/databases/LastNames.db");
		Log.d("DB CHECK!!", Boolean.toString(dbCheck.exists()));
		
		// work out a check to see if the db/table exists
		if (!dbCheck.exists()) {
			try {
				input = getResources().openRawResource(R.raw.lastnames);
				reader = new BufferedReader(new InputStreamReader(input));
				Log.d("Success!!", "Works Fine!");
				String ln;
				while ((ln = reader.readLine()) != null) {
					String[] name = ln.split(",");
					for (int i = 0; i < name.length; i++) {
						// write name[i] to the LastNames table
						db.addLastName(name[i]);
					}
				}
			} catch (Exception e) {
				Log.d("FAILURE!!!", "bad file!");
				e.printStackTrace();
			}
			
			if (input != null) {
				try {
					input.close();
					reader.close();
					db.close();
					Log.d("Success!!", "Closed Fine!");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
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
	
}