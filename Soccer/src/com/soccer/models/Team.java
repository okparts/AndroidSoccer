package com.soccer.models;

import android.content.Context;

import com.soccer.constants.GameVars;
import com.soccer.database.DBHelper;

public class Team {

	// class attributes
	private String city, name, nickname;
	private Player[] players = new Player[18];
	
	// constructor
	public Team(Context context) {
		
		// generate team city, team name, and team nickname
		DBHelper db = new DBHelper(context);
		this.city = db.getRandomName(GameVars.TABLE_CITY_NAMES, GameVars.COLUMN_CITY_NAME);
		this.name = db.getRandomName(GameVars.TABLE_TEAM_NAMES, GameVars.COLUMN_TEAM_NAME);
		this.nickname = db.getRandomName(GameVars.TABLE_NICKNAMES, GameVars.COLUMN_NICKNAME);
		db.closeDB();
		
		
	}
}