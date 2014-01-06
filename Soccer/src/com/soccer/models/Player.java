package com.soccer.models;

import java.util.Random;

import com.soccer.utils.NameGen;

public class Player {

	// class attributes
	private String name, position;
	private int age, contractPeriod, value, strength, control, skill, fitness;
	
	// utility variables
	private NameGen nameGen = null;
	private Random random = new Random();
	private int high = 0;
	private int low = 0;
	private int attrs = 0;
	private int avg = 0;
	private int maxValue = 0;
	private double total = 0.0;
	
	// constructor
	// used to generate players to build out teams
	public Player(NameGen ng, String position, int tier) {
		
		this.nameGen = ng; // name generator
		
		this.position = position; // player position
		
		this.name = nameGen.getPlayerName();
		
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
				maxValue = 15000;
				attrs = 40;
				break;
			case 2:
				maxValue = 25000;
				attrs = 80;
				break;
			case 3:
				maxValue = 35000;
				attrs = 120;
				break;
			case 4:
				maxValue = 50000;
				attrs = 160;
				break;
			case 5:
				maxValue = 70000;
				attrs = 200;
				break;
			case 6:
				maxValue = 150000;
				attrs = 240;
				break;
			case 7:
				maxValue = 250000;
				attrs = 280;
				break;
			case 8:
				maxValue = 750000;
				attrs = 320;
				break;
			case 9:
				maxValue = 2000000;
				attrs = 360;
				break;
			case 10:
				maxValue = 30000000;
				attrs = 400;
				break;
		}
		
		generateAttrs(attrs);
		
		total = (double) this.strength + this.control + this.skill + this.fitness;
		total = total / 400;
		total = total * maxValue;
		this.value = (int) Math.ceil(total);
		
	}
	
	private void generateAttrs(int totalAttrs) {
		avg = totalAttrs / 4;
		low = avg / 2;
		high = avg + 5;
		if (high > 100) {
			high = 101;
		}
		
		this.strength = random.nextInt(high - low) + low;
		if (this.strength > 100) {
			this.strength = 90;
		}
		
		this.control = random.nextInt(high - low) + low;
		if (this.control > 100) {
			this.control = 90;
		}
		
		this.skill = random.nextInt(high - low) + low;
		if (this.skill > 100) {
			this.skill = 90;
		}
		
		this.fitness = random.nextInt(high - low) + low;
		if (this.fitness > 100) {
			this.fitness = 90;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getContractPeriod() {
		return contractPeriod;
	}

	public void setContractPeriod(int contractPeriod) {
		this.contractPeriod = contractPeriod;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getControl() {
		return control;
	}

	public void setControl(int control) {
		this.control = control;
	}

	public int getSkill() {
		return skill;
	}

	public void setSkill(int skill) {
		this.skill = skill;
	}

	public int getFitness() {
		return fitness;
	}

	public void setFitness(int fitness) {
		this.fitness = fitness;
	}
	
}