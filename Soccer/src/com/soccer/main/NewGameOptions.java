package com.soccer.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.soccer.constants.GameVars;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

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
		
		EditText mgrNameField = (EditText) findViewById(R.id.optionsManagerName);
		String mgrName = mgrNameField.getText().toString().trim();
		
		if (mgrName.isEmpty()) {
			Toast.makeText(this, R.string.manager_name_error_blank, Toast.LENGTH_LONG).show();
			btn.setBackgroundResource(R.drawable.btn_default);
		} else if(!checkName(mgrName)) {
			Toast.makeText(this, R.string.manager_name_error_letters, Toast.LENGTH_LONG).show();
		} else if(!mgrName.isEmpty() && checkName(mgrName)) {
			Intent intent = new Intent(this, BuildGame.class);
			intent.putExtra("managerName", mgrName);
			startActivity(intent);
		}
	}

	private boolean checkName(String name) {
		try {
			Pattern p = Pattern.compile("^[\\p{L} .'-]+$");
			Matcher m = p.matcher(name);
			return m.matches();
		} catch (RuntimeException e) {
			return false;
		}
	}
}
