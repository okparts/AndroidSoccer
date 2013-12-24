package com.soccer.main;

import com.soccer.constants.GameVars;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class NewGameOptions extends Activity {

	private Vibrator vibe;
	private View btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
	    vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
	    
		setContentView(R.layout.activity_new_game_options);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_game_options, menu);
		return true;
	}

	public void createGame(View v) {
		vibe.vibrate(GameVars.VIBRATE_TIME);
		btn = (View) findViewById(R.id.createGameBtn);
		btn.setBackgroundResource(R.drawable.btn_pressed);
	}

}
