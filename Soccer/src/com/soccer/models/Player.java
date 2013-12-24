package com.soccer.models;

import java.util.Map;
import java.util.Random;

public class Player {

	// class attributes
	private String name, position;
	private Map<String, Integer> attrs;
	public int age, contractPeriod, value;
	
	// constructors
	public Player(String position, int tier) {
		
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
		
		
	};
	
}