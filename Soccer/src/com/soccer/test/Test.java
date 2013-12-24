package com.soccer.test;

import com.soccer.models.Player;

public class Test {

	
	public static void main(String[] args) {
		
		Player p = new Player("Midfield", 1);
		System.out.println(Integer.toString(p.contractPeriod));
	}

}
