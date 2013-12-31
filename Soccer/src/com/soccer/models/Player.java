package com.soccer.models;

import java.util.Map;
import java.util.Random;

import android.content.Context;

import com.soccer.constants.GameVars;
import com.soccer.database.DBHelper;

public class Player {

	// class attributes
	private String name, position;
	private Map<String, Integer> attrs;
	public int age, contractPeriod, value;
	
	// constructors
	// default - uses getters and setters to assign values to class attributes
	public Player(Context context) {
		this.name = "";
		this.position = "";
		this.attrs = null;
		this.age = 0;
		this.contractPeriod = 0;
		this.value = 0;
	}
	
	// used to generate players to build out teams, leagues, etc.
	public Player(Context context, String position, int tier) {
		
		this.position = position;
		
		// number for contractPeriod (1-5 years)
		Random r = new Random();
		int Low = 1;
		int High = 6; // must be one more than the max
		this.contractPeriod = r.nextInt(High - Low) + Low;
		
		// number for age (18-35)
		Low = 18;
		High = 36; // must be one more than the max
		this.age = r.nextInt(High - Low) + Low;
		
		// number for league tier
		switch (tier) {
			case 1:
				Low = 10000;
				High = 15001;
				break;
			case 2:
				Low = 15000;
				High = 25001;
				break;
			case 3:
				Low = 25000;
				High = 35001;
				break;
			case 4:
				Low = 35000;
				High = 50001;
				break;
			case 5:
				Low = 50000;
				High = 70001;
				break;
			case 6:
				Low = 70000;
				High = 150001;
				break;
			case 7:
				Low = 150000;
				High = 250001;
				break;
			case 8:
				Low = 250000;
				High = 750001;
				break;
			case 9:
				Low = 750000;
				High = 2000001;
				break;
			case 10:
				Low = 2000000;
				High = 30000001;
				break;
			default:
				Low = 0;
				High = 2;
				break;
		}
		this.value = r.nextInt(High - Low) + Low;
		
//		DBHelper db = new DBHelper(context);
//		String fname = db.getRandomName(GameVars.TABLE_FIRST_NAMES, GameVars.COLUMN_FIRST_NAME);
//		String lname = db.getRandomName(GameVars.TABLE_LAST_NAMES, GameVars.COLUMN_LAST_NAME);
//		this.name = fname + " " + lname;
	}

	// Getters and Setters for class attributes
	
	// get player name
	public String getName() {
		return name;
	}

	// set player name - use with an existing name
	public void setName(String name) {
		this.name = name;
	}

	// get player position
	public String getPosition() {
		return position;
	}

	// set player position
	public void setPosition(String position) {
		this.position = position;
	}

	// get Map of player attributes
	public Map<String, Integer> getAttrs() {
		return attrs;
	}

	// set player attribute - use with an existing player attributes Map
	public void setAttrs(Map<String, Integer> attrs) {
		this.attrs = attrs;
	}
	
	// set player attribute - use to build a new player attributes Map
	public Map<String, Integer> setAttrs(Map<String, Integer> attrs, String attr, Integer value) {
		Map<String, Integer> newAttrs = attrs;
		if (!newAttrs.containsKey(attr)) {
			newAttrs.put(attr, value);
		}
		return newAttrs;
	}

	// get player age
	public int getAge() {
		return age;
	}

	// set player age
	public void setAge(int age) {
		this.age = age;
	}

	// get player contract period
	public int getContractPeriod() {
		return contractPeriod;
	}

	// set player contract period
	public void setContractPeriod(int contractPeriod) {
		this.contractPeriod = contractPeriod;
	}

	// get player value
	public int getValue() {
		return value;
	}

	//set player value
	public void setValue(int value) {
		this.value = value;
	}
}