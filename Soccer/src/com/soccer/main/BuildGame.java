package com.soccer.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.soccer.interfaces.GameBuilderResponse;
import com.soccer.tasks.GameBuilder;

public class BuildGame extends Activity implements GameBuilderResponse {

	private ProgressBar loader = null;
	private LinearLayout contBtn = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_build_game);
		
		Intent passedIntent = getIntent();
		String mgrName = passedIntent.getStringExtra("managerName");
		loader = (ProgressBar) findViewById(R.id.gameBuildProgress);
		contBtn = (LinearLayout) findViewById(R.id.contGameBtn);
		
		GameBuilder build = new GameBuilder(this, mgrName, loader);
		build.execute("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.build_game, menu);
		return true;
	}

	@Override
	public void finishedBuilding(Boolean response) {
		// TODO 
		// 1. stop and hide the spinning loader when the game is finished building
		// 2. show a button to start the game
		loader.setVisibility(View.INVISIBLE);
		contBtn.setVisibility(View.VISIBLE);
	}
}
