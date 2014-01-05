package com.soccer.models;

import java.util.Random;

import android.content.Context;

import com.soccer.utils.NameGen;

public class Player {

	// class attributes
	private String name, position;
	private int age, contractPeriod, value, strength, control, skill, fitness;
	private NameGen nameGen = null;
	Random random = new Random();
	
	// utility variables
	private int high, low, attrs, avg;
	private int lowCount = 0;
	private int highCount = 0;
	
	// constructor
	// used to generate players to build out teams
	public Player(Context context, NameGen ng, String position, int tier) {
		
		this.nameGen = ng; // name generator
		
		this.position = position; // player position
		
		this.name = nameGen.getName();
		
		// number for age (18-35)
		low = 18;
		high = 36; // must be one more than the max
		this.age = random.nextInt(high - low) + low;
		
		// number for contractPeriod (1-5 years)
		low = 1;
		if (age <= 31) {
			high = 6;
		} else if (age == 32) {
			high = 5;
		} else if (age == 33) {
			high = 4;
		} else if (age == 34) {
			high = 3;
		} else if (age == 35) {
			high = 2;
		}
		this.contractPeriod = random.nextInt(high - low) + low;
		
		// number for league tier
		switch (tier) {
			case 1:
				low = 10000;
				high = 15001;
				attrs = 40;
				break;
			case 2:
				low = 15000;
				high = 25001;
				attrs = 80;
				break;
			case 3:
				low = 25000;
				high = 35001;
				attrs = 120;
				break;
			case 4:
				low = 35000;
				high = 50001;
				attrs = 160;
				break;
			case 5:
				low = 50000;
				high = 70001;
				attrs = 200;
				break;
			case 6:
				low = 70000;
				high = 150001;
				attrs = 240;
				break;
			case 7:
				low = 150000;
				high = 250001;
				attrs = 280;
				break;
			case 8:
				low = 250000;
				high = 750001;
				attrs = 320;
				break;
			case 9:
				low = 750000;
				high = 2000001;
				attrs = 360;
				break;
			case 10:
				low = 2000000;
				high = 30000001;
				attrs = 400;
				break;
		}
		this.value = random.nextInt(high - low) + low;
		
		generateAttrs(attrs);
	}
	
	private void generateAttrs(int total) {
		avg = total / 4;
		low = avg - 5;
		high = avg + 5;
		if (high > 100) {
			high = 101;
		}
		this.strength = random.nextInt(high - low) + low;
		if (this.strength < 10) {
			lowCount++;
		} else {
			highCount ++;
		}
		
		this.control = random.nextInt(high - low) + low;
		if (this.control < 10) {
			lowCount++;
		} else {
			highCount ++;
		}
		
		if (lowCount == 2) {
			this.skill = random.nextInt(high - avg) + high;
		} else if (highCount == 2) {
			this.skill = random.nextInt(avg - low) + low;
		} else {
			this.skill = random.nextInt(high - low) + low;
		}
		
		this.fitness = total - this.strength - this.control - this.skill;
	}
}