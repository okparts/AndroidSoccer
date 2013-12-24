package com.soccer.models;

public class Country {

	// class attributes
	private String name, nickname;
	private int population;
	// private NationalFA association;
	
	// constructors
	public Country(String name, String nickname) {
		
		this.name = name;
		this.nickname = nickname;
		
		Double multiplier1 = Math.random() * 10;
		Double multiplier2 = Math.random() * 10 * multiplier1;
		Double multiplier3 = Math.random() * 10 * multiplier2;
		Double multiplier = Math.random() * 10 * multiplier3;
		multiplier = Math.floor(multiplier);
		Double pop = Math.random() * 1000000;
		pop = Math.floor(pop);
		this.population = pop.intValue() * multiplier.intValue();
		
		while (this.population < 1000000) {
			multiplier1 = Math.random() * 10;
			multiplier2 = Math.random() * 10 * multiplier1;
			multiplier = Math.random() * 10 * multiplier2;
			multiplier = Math.floor(multiplier);
			pop = Math.random() * 1000000;
			pop = Math.floor(pop);
			this.population = pop.intValue() * multiplier.intValue();
		}
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

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}
}
