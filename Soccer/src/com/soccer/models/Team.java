package com.soccer.models;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import android.content.Context;

import com.soccer.main.R;
import com.soccer.utils.NameGen;

public class Team {

	// class attributes
	private String city, name, nickname;
	private int tier;
	
	// utility variables
	NameGen ng = null;
	BufferedReader reader = null;
	
	// constructor
	public Team(Context context, NameGen ng) {
		this.ng = ng;
		this.reader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.citynames)));
		this.city = ng.getTeamCity(this.reader);
		this.name = ng.getTeamName();
		this.nickname = ng.getTeamNickname();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getTier() {
		return tier;
	}

	public void setTier(int tier) {
		this.tier = tier;
	}
}