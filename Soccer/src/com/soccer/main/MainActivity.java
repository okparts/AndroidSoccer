package com.soccer.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.soccer.constants.GameVars;

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