package com.soccer.tasks;

import android.os.AsyncTask;

import com.soccer.models.Player;
import com.soccer.utils.NameGen;

public class GameBuilder extends AsyncTask<Void, Void, Boolean> {

	private static NameGen ng = new NameGen();
	private Player player = null;
	
	@Override
	protected Boolean doInBackground(Void... params) {

		/*
		 * TODO List
		 * 
		 * 1. build the teams and players
		 * 2. implement new DBHelper methods to handle creating team, player, manager tables
		 * 2. write the teams, players, manager data to sqlite tables
		 */
		
		return true;
	}
}