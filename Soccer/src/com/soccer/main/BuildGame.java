package com.soccer.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.soccer.tasks.GameBuilder;

public class BuildGame extends Activity {

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
		
		GameBuilder build = new GameBuilder(this, mgrName, loader, contBtn);
		build.execute("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.build_game, menu);
		return true;
	}
}