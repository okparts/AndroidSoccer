package com.soccer.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import android.util.Log;

public class NameGen {
	
	// class attributes
	private String name = ""; // generated player name
	private String syllable = ""; // syllable
	private String teamCity = ""; // team city
	private String teamName = ""; // team name
	private String teamNickname = ""; // team nickname
	
	// utility variables - general
	private Random random = new Random(); // random number generator
	
	// utility variables - player name generator
	char c = ' '; // letter that ends the current generated name
	private boolean endsInVowel; // boolean for letter that ends the current generated name - vowel-TRUE | consonant-FALSE
	private int numOfSyllables = -1; // number of syllables for the name
	private int whichPrefix = -1; // determines the prefix array to use to begin the name - 0-Any | 1-Vowel | 2-Consonant
	private int index = -1; // the random array index to pull the syllable value
	private int next = -1; // determines what the next syllable must be - 0-Any | 1-Vowel | 2-Consonant
	
	// utility variables - team city, name, nickname generator
	private static final int MAX_ROWS = 18950;
	private int row = 0;
	
	
	// Player Name Generator - constants
		// preceding and proceeding syllable types
		private static final int ANY = 0;
		private static final int VOWEL = 1;
		private static final int CONSONANT = 2;
	
		// letter types
		private static final Set<Character> VOWELS = new HashSet<Character>(Arrays.asList(new Character[] {'a', 'e', 'i', 'o', 'u', 'y'}));
		// private static final Set<Character> CONSONANTS = new HashSet<Character>(Arrays.asList(new Character[] {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z'}));
		
		// prefix syllable types
		private static final String[] FNAME_PREFIX_ANY = {"Av", "Ad", "Ah", "Al", "Am", "An", "Ar", "As", "At", "Ax", "Ay", "Az", "Ba", "Be", "Bo", "Ca", "Ce", "Co", "Da", "De", "Di", "Do", "Dy", "Ed", "El", "Em", "Er", "Ez", "Ib", "Im", "Is", "Jo", "Ju", "Ke", "Ki", "Ky", "Le", "Li", "Lo", "Lu", "Ly", "Mu", "My", "Ne", "No", "Ob", "Ol", "Om", "Or", "Os", "Pa", "Pe", "Ra", "Re", "Ta", "Te", "Ti", "We", "Xa", "Xi", "Za"};
		private static final String[] FNAME_PREFIX_VOWEL = {"Bl", "Br", "Ch", "Cl", "Fl", "Fr", "Gl", "Gr", "Kr", "Ph", "Pr", "Rh", "Sh", "Sp", "St", "Th"};
		private static final String[] FNAME_PREFIX_CONSONANT = {"A", "Aa","Ae", "Ai", "Bi", "Bu", "Ci", "Ea", "Fa", "Fe", "Fi", "Fo", "Ga", "So", "Ge", "Gi", "Gu", "Ha", "He", "Hi", "Ho", "Hu", "Ja", "Ka", "Kl", "Ko", "Ku", "La", "Ma", "Me", "Mi", "Mo", "Na", "Ni", "Ri", "Ro", "Ru", "Ry", "Sa", "Se", "Si", "Su", "To", "Ty", "Va", "Vi", "Wa", "Ya", "Yo", "Yu", "Ze", "Zi", "Zu"};
		
		// middle syllable types
		private static final String[] FNAME_MID_PRE_ANY_POST_ANY = {"r", "rr", "y", "ro", "di", "ri", "san", "es", "ty", "ol", "rin", "mol", "gy", "yan", "el"};
		private static final String[] FNAME_MID_PRE_ANY_POST_VOWEL = {"ir", "kad", "kar", "in", "is", "id", "ph", "gel", "th", "ton", "tom", "tot", "lom", "eth", "in"};
		private static final String[] FNAME_MID_PRE_ANY_POST_CONSONANT = {"ry", "ra", "kha", "mal", "les", "lis", "chi", "tu", "tol", "si"};
		private static final String[] FNAME_MID_PRE_VOWEL_POST_ANY = {"d", "ns", "sh", "n"};
		private static final String[] FNAME_MID_PRE_VOWEL_POST_VOWEL = {"z", "bb", "bd", "sal", "bas", "st", "zed", "ned", "rt", "xt", "bh", "rh", "tch", "rl", "dr", "ll"};
		private static final String[] FNAME_MID_PRE_VOWEL_POST_CONSONANT = {"hr", "ba", "ha", "ki", "on", "hai"};
		private static final String[] FNAME_MID_PRE_CONSONANT_POST_ANY = {"el", "ir", "u", "e", "ay", "an", "af", "vin", "to", "il"};
		private static final String[] FNAME_MID_PRE_CONSONANT_POST_VOWEL = {"ih", "ub", "it", "ad"};
		private static final String[] FNAME_MID_PRE_CONSONANT_POST_CONSONANT = {"ai", "ou", "ek", "ex", "ie", "ia", "oo", "au", "aa", "ee"};
		
		// suffix syllable types
		private static final String[] FNAME_SUFFIX_ANY = {"ron", "ran", "ren", "man", "yan", "sir", "der", "ham", "son", "lin", "lo", "don", "dine", "per", "nan"};
		private static final String[] FNAME_SUFFIX_VOWEL = {"kim", "lik", "co", "mie", "nie", "gan", "se", "ne", "los", "lam", "lan"};
		private static final String[] FNAME_SUFFIX_CONSONANT = {"am", "it", "ola", "en", "ian", "ie", "y", "io", "o", "ea", "uel", "ell", "el", "iah", "ien", "tin", "ick", "ice", "ford", "ter"};
		
	
	// Team Name Generator - constants
		
		// team data arrays
		// 193 team names
		private final String[] TEAM_NAME = {"F.C.", "United", "Industrial", "Patriots", "Sailors", "Old Boys", "Young Boys", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "167", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "Argyle", "Fire", "Eagles", "East", "Dons", "Forest", "Etihad", "Rangers", "Sounders", "Albion", "Americans", "Arabi", "Karelia", "Bohemians", "Celtic", "Chiefs", "International", "Marist", "Monarchy", "Dynasty", "Humble Lions", "Revolution", "Saints", "Santos", "Borough", "Cerro", "City", "Hamlet", "River", "Sandwiches", "Town", "Towne", "Vale", "Sparta", "Corinth", "Betis", "River Plate", "Bluebell", "Blueball", "Cherry", "Ficus", "Laburnum", "Maple", "Shamrock", "Thistle", "Rose", "Bee", "Buffalo", "Cattle", "Cheetahs", "Dingo", "Fairies", "Goats", "Meercats", "Horses", "Hummingbirds", "Lions", "Lynx", "Snakes", "Stallions", "Mustangs", "Squirrels", "Tigers", "Swift", "Zebra", "Wolves", "Apollo", "Aris", "Atlas", "Hercules", "Spartak", "Zenit", "Spartacus", "Teuta", "Vikings", "Zico", "Dynamo", "Lokomotiv", "Torpedo", "Brotherhood", "Fortune", "Freedom", "Libertados", "Libertad", "Libertas", "Glory", "Peace"};
		// 190 team nicknames
		private final String[] TEAM_NICKNAME = {"Addicks", "Ash Trees", "Badgers", "Baggies", "Bantams", "Bees", "Biscuitmen", "Black Cats", "Blades", "Blues", "The Toffees", "Boro", "Brewers", "Bulls", "Bury", "Canaries", "Cardinals", "Cards", "Chairboys", "Cherries", "Citizens", "Clarets", "Cobblers", "Cottagers", "Cumbrians", "Daggers", "Dale", "Diamonds", "Dolly Blues", "Donny", "Dons", "Eagles", "FC", "Filberts", "Fleet", "Fosse", "Foxes", "Gas", "Gills", "Glaziers", "Glovers", "Grecians", "Gulls", "Gunners", "Hammers", "Harriers", "Hatters", "Heed", "Hoops", "Hornets", "Imps", "Iron", "Irons", "The Reds", "The Blues", "Reds", "Yellows", "The Yellows", "Browns", "The Browns", "The Orange", "Oranges", "Greens", "The Greens", "Knitters", "Lads", "Lambs", "Latics", "Lilywhites", "Lions", "Magpies", "Mariners", "Millers", "Minstermen", "Mushrooms", "Nailers", "O's", "Owls", "Peacocks", "Cocks", "Pensioners", "Pilgrims", "Pirates", "Pompey", "Pool", "Pools", "The Pool", "Poppies", "Fancy", "Fancy Pants", "Quakers", "The R's", "The O's", "R's", "Railwaymen", "Rams", "Rebels", "Red Devils", "Red Imps", "Riversiders", "Robins", "Rovers", "Royals", "Saddlers", "Saints", "Salop", "Sandgrounders", "Science", "Seagulls", "Seals", "Seasiders", "Shakers", "Shaymen", "Shots", "Shrimps", "Shrimpers", "Silkmen", "Skunks", "Sky Blues", "Smoggies", "Spirits", "Sprites", "Sporting", "Spurs", "Stags", "Stanleys", "Stripes", "Super", "Terras", "Terriers", "Teyn", "Tics", "Tigers", "Toon", "Tractors", "Tractor Boys", "Towners", "Townees", "Tricks", "Trotters", "Trots", "U's", "The U's", "Vics", "Valiant", "Valiants", "Vikings", "Villains", "Whites", "The Whites", "Wolves", "The Wolves", "Wanderers", "Riversiders", "Red and Blues", "Red and Whites", "Red and Greens", "Red and Yellows", "Blue and Browns", "Blue and Yellows", "Blue and Oranges", "Blue and Mauves", "Glens", "Hatchetmen", "Slicers", "Strikers", "Streakers", "Candy Canes", "Candies", "Buddies", "Cabbages", "Turnips", "Hippees", "Hedgemen", "Tarts", "Loons", "Roughnecks", "Killers", "Serial Killers", "Spiders", "Warriors", "Wasps", "Geezers", "Exiles", "Ironsides", "Bastards", "The Bastards", "Jack Bastards", "Swans", "Wingers"};
		
		
	public String getTeamCity(BufferedReader reader) {
		row = random.nextInt(MAX_ROWS);
		if (row == 0) {
			row = 1;
		}
		for (int i = 0; i <= row - 1; i++) {
			try {
				reader.readLine();
			} catch (IOException e) {
				Log.d("ERROR", e.toString());
			}
		}
		try {
			this.teamCity = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.d("ERROR", "Cannot assign a value for Team City");
		}
		return this.teamCity;
	}
	
	public String getTeamName() {
		row = random.nextInt(TEAM_NAME.length);
		this.teamName = TEAM_NAME[row];
		return this.teamName;
	}
	
	public String getTeamNickname() {
		row = random.nextInt(TEAM_NICKNAME.length);
		this.teamNickname = TEAM_NICKNAME[row];
		return this.teamNickname;
	}
	
	public String getPlayerName() {
		
		numOfSyllables = random.nextInt(3) + 2; // returns a number of syllables between 2-4
		
		whichPrefix = random.nextInt(3);
		switch (whichPrefix) {
			case 0:
				index = random.nextInt(FNAME_PREFIX_ANY.length);
				syllable = FNAME_PREFIX_ANY[index];
				next = ANY;
				break;
			case 1:
				index = random.nextInt(FNAME_PREFIX_VOWEL.length);
				syllable = FNAME_PREFIX_VOWEL[index];
				next = VOWEL;
				break;
			case 2:
				index = random.nextInt(FNAME_PREFIX_CONSONANT.length);
				syllable = FNAME_PREFIX_CONSONANT[index];
				next = CONSONANT;
				break;
			default:
				index = random.nextInt(FNAME_PREFIX_ANY.length);
				syllable = FNAME_PREFIX_ANY[index];
				next = ANY;
		}
		name = syllable;
		endsInVowel = lastIsVowel(name);
		
		if (numOfSyllables > 2) {
			generateMidSyllable(next, endsInVowel);
			endsInVowel = lastIsVowel(name);
		}
		
		if (numOfSyllables > 3) {
			generateMidSyllable(next, endsInVowel);
			endsInVowel = lastIsVowel(name);
		}
		
		generateSuffix(next, endsInVowel);
		
		return name;
	}
	
	private boolean lastIsVowel(String s) {
		c = s.charAt(s.length() - 1);
		if (VOWELS.contains(c)) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean firstIsVowel(String s) {
		c = s.charAt(0);
		if (VOWELS.contains(c)) {
			return true;
		} else {
			return false;
		}
	}
	
	private void generateMidSyllable(int nextType, boolean prevType) {
		if (nextType == ANY) {
			if (prevType) {
				if (random.nextInt(2) == 0) {
					index = random.nextInt(FNAME_MID_PRE_ANY_POST_ANY.length);
					syllable = FNAME_MID_PRE_ANY_POST_ANY[index];
					next = ANY;
				} else {
					index = random.nextInt(FNAME_MID_PRE_ANY_POST_VOWEL.length);
					syllable = FNAME_MID_PRE_ANY_POST_VOWEL[index];
					next = VOWEL;
				}
			} else {
				if (random.nextInt(2) == 0) {
					index = random.nextInt(FNAME_MID_PRE_ANY_POST_ANY.length);
					syllable = FNAME_MID_PRE_ANY_POST_ANY[index];
					next = ANY;
				} else {
					index = random.nextInt(FNAME_MID_PRE_ANY_POST_CONSONANT.length);
					syllable = FNAME_MID_PRE_ANY_POST_CONSONANT[index];
					next = CONSONANT;
				}
			}
		} else if (nextType == VOWEL) {
			if (prevType) {
				index = random.nextInt(FNAME_MID_PRE_CONSONANT_POST_VOWEL.length);
				syllable = FNAME_MID_PRE_CONSONANT_POST_VOWEL[index];
				next = VOWEL;
			} else {
				index = random.nextInt(FNAME_MID_PRE_CONSONANT_POST_CONSONANT.length);
				syllable = FNAME_MID_PRE_CONSONANT_POST_CONSONANT[index];
				next = CONSONANT;
			}
		} else if (nextType == CONSONANT) {
			if (prevType) {
				if (random.nextInt(2) == 0) {
					do {
						index = random.nextInt(FNAME_MID_PRE_CONSONANT_POST_ANY.length);
						syllable = FNAME_MID_PRE_CONSONANT_POST_ANY[index];
					} while (firstIsVowel(syllable));
					next = ANY;
				} else {
					if (random.nextInt(2) == 0) {
						index = random.nextInt(FNAME_MID_PRE_VOWEL_POST_ANY.length);
						syllable = FNAME_MID_PRE_VOWEL_POST_ANY[index];
						next = ANY;
					} else {
						index = random.nextInt(FNAME_MID_PRE_VOWEL_POST_VOWEL.length);
						syllable = FNAME_MID_PRE_VOWEL_POST_VOWEL[index];
						next = VOWEL;
					}
				}
			} else {
				if (random.nextInt(2) == 0) {
					do {
						index = random.nextInt(FNAME_MID_PRE_CONSONANT_POST_ANY.length);
						syllable = FNAME_MID_PRE_CONSONANT_POST_ANY[index];
					} while (firstIsVowel(syllable));
					next = ANY;
				} else {
					if (random.nextInt(2) == 0) {
						index = random.nextInt(FNAME_MID_PRE_VOWEL_POST_ANY.length);
						syllable = FNAME_MID_PRE_VOWEL_POST_ANY[index];
						next = ANY;
					} else {
						syllable = FNAME_MID_PRE_VOWEL_POST_CONSONANT[4];
						next = CONSONANT;
					}
				}
			}
		}
		
		// attach the new syllable to the name
		name = name + syllable;
	}
	
	public void generateSuffix(int nextType, boolean prevType) {
		if (nextType == ANY) {
			if (prevType) {
				if (random.nextInt(2) == 0) {
					index = random.nextInt(FNAME_SUFFIX_ANY.length);
					syllable = FNAME_SUFFIX_ANY[index];
				} else {
					index = random.nextInt(FNAME_SUFFIX_VOWEL.length);
					syllable = FNAME_SUFFIX_VOWEL[index];
				}
			} else {
				if (random.nextInt(2) == 0) {
					index = random.nextInt(FNAME_SUFFIX_ANY.length);
					syllable = FNAME_SUFFIX_ANY[index];
				} else {
					index = random.nextInt(FNAME_SUFFIX_CONSONANT.length);
					syllable = FNAME_SUFFIX_CONSONANT[index];
				}
			}
		} else if (nextType == VOWEL) {
			if (prevType) {
				if (random.nextInt(2) == 0) {
					index = random.nextInt(FNAME_SUFFIX_ANY.length);
					syllable = FNAME_SUFFIX_ANY[index];
				} else {
					index = random.nextInt(FNAME_SUFFIX_VOWEL.length);
					syllable = FNAME_SUFFIX_VOWEL[index];
				}
			} else {
				if (random.nextInt(2) == 0) {
					index = random.nextInt(FNAME_SUFFIX_ANY.length);
					syllable = FNAME_SUFFIX_ANY[index];
				} else {
					do {
						index = random.nextInt(FNAME_SUFFIX_CONSONANT.length);
						syllable = FNAME_SUFFIX_CONSONANT[index];
					} while (!firstIsVowel(syllable));
				}
			}
		} else if (nextType == CONSONANT) {
			if (prevType) {
				if (random.nextInt(2) == 0) {
					index = random.nextInt(FNAME_SUFFIX_ANY.length);
					syllable = FNAME_SUFFIX_ANY[index];
				} else {
					index = random.nextInt(FNAME_SUFFIX_CONSONANT.length);
					syllable = FNAME_SUFFIX_CONSONANT[index];
				}
			} else {
				if (random.nextInt(2) == 0) {
					index = random.nextInt(FNAME_SUFFIX_ANY.length);
					syllable = FNAME_SUFFIX_ANY[index];
				} else {
					do {
						index = random.nextInt(FNAME_SUFFIX_VOWEL.length);
						syllable = FNAME_SUFFIX_VOWEL[index];
					} while (firstIsVowel(syllable));
				}
			}
		}
		
		// complete the name with the final syllable
		name = name + syllable;
	}
}