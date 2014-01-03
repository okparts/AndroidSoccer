package com.soccer.utils;

import java.util.Random;

//1) +v means that next syllable must definitely start with a wovel.
//2) +c means that next syllable must definitely start with a consonant.
//3) -v means that this syllable can only added to another syllable, that ends with a wovel.
//4) -c means that this syllable can only added to another syllable, that ends with a consonant.

public class NameGen {
	
	// general variables
	

	// letter types
	private static final char[] VOWELS = {'a', 'e', 'i', 'o', 'u', 'y'};
	private static final char[] CONSONANTS = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z'};
	
	// prefix types
	private static final String[] FNAME_PREFIX_ANY = {"any", "av", "ad", "ah", "al", "am", "an", "ar", "as", "at", "ax", "ay", "az", "ba", "be", "bo", "ca", "ce", "co", "da", "de", "di", "do", "dy", "ed", "el", "em", "er", "ez",  "ib", "im", "is", "jo", "ju", "ke", "ki", "ky", "le", "li", "lo", "lu", "ly", "mu", "my", "ne", "no", "ob", "ol", "om", "or", "os", "pa", "pe", "ra", "re", "ta", "te", "ti", "we", "xa", "xi", "za"};
	private static final String[] FNAME_PREFIX_VOWEL = {"vowel", "bl", "br", "ch", "cl", "fl", "fr", "gl", "gr+ v", "kr", "ph", "pr", "rh", "sh", "so", "sp", "st", "th"};
	private static final String[] FNAME_PREFIX_CONSONANT = {"consonant", "a", "aa","ae", "ai", "bi", "bu", "ci", "ea", "fa", "fe", "fi", "fo", "ga", "ge", "gi", "gu", "ha", "he", "hi", "ho", "hu", "ja", "ka", "kl", "ko", "ku", "la", "ma", "me", "mi", "mo", "na", "ni", "ri", "ro", "ru", "ry", "sa", "se", "si", "su", "to", "ty", "va", "vi", "wa", "ya", "yo", "yu", "ze", "zi", "zu"};
	
	// mid-syllabic types
	private static final String[] FNAME_MID_PRE_ANY_POST_ANY = {"any_any", "r", "rr", "y", "ro", "di", "ri", "san", "es", "ty", "ol", "rin", "mol", "gy", "yan", "el"};
	private static final String[] FNAME_MID_PRE_ANY_POST_VOWEL = {"any_vowel", "ir", "kad", "kar", "in", "is", "id", "ph", "gel", "th", "ton", "tom", "tot", "lom", "eth", "in"};
	private static final String[] FNAME_MID_PRE_ANY_POST_CONSONANT = {"any_consonant", "ry +c", "ro", "ha -v +c", "ki -v +c", "ra +c", "sal -v +v", "bas -v +v", "kad +v", "kar +v", "kha +c", "mal +c", "in +v", "is +v", "el -c", "id +v", "ir -c", "u -c", "ub -c +v", "e -c", "ai -c +c", "di", "it -c +v", "ri", "on -v +c", "ou -c +c", "ta +c", "ad -c +v", "ns -v", "rl -c +c", "ay -c", "an -c", "ek -c +c", "san", "es", "ex -c +c", "ie -c +c", "hai -v +c", "sh -v", "st -v +v", "les +c", "lis +c", "ph +v", "ty", "ol", "rin", "dr -c +c", "gel +v", "mol", "th +v", "ton +v", "af -c", "chi +c", "gy", "tom +v", "tot +v", "tu +c", "yan", "vin -c", "zed -v +v", "ie -c +c", "ned -v +v", "rt -v +v", "tol +c", "lom +v", "to -c", "si +c", "xt -v +v", "n -v", "ia -c +c", "oo -c +c", "uc -c +c", "bh -v +v", "rh -v +v", "tch -v +v", "el", "eth +v", "il -c", "in +v"};
	private static final String[] FNAME_MID_PRE_VOWEL_POST_ANY = {"vowel_any", "d", "ns", "sh", "n"};
	private static final String[] FNAME_MID_PRE_VOWEL_POST_VOWEL = {"vowel_vowel", "z", "bb", "bd", "sal", "bas", "st", "zed", "ned", "rt", "xt", "bh", "rh", "tch", "rl", "dr", "ll"};
	private static final String[] FNAME_MID_PRE_VOWEL_POST_CONSONANT = {"vowel_consonant", "hr", "ba", "ha", "ki", "on", "hai"};
	private static final String[] FNAME_MID_PRE_CONSONANT_POST_ANY = {"consonant_any", "ry +c", "ra +c", "kha +c", "mal +c", "les +c", "lis +c", "chi +c", "tu +c", "tol +c", "si +c"};
	private static final String[] FNAME_MID_PRE_CONSONANT_POST_VOWEL = {"consonant_vowel", "ih", "ub", "it", "ad"};
	private static final String[] FNAME_MID_PRE_CONSONANT_POST_CONSONANT = {"consonant_consonant", "ai", "ou", "ek", "ex", "ie", "ia", "oo", "au", "aa", "ee"};
	
	// suffix types
	private static final String[] FNAME_SUFFIX_ANY = {"any", "ron", "ran", "ren", "man", "yan", "lam", "lan", "sir", "der", "ham", "son", "lin", "lo", "los", "don", "dine", "per", "nan"};
	private static final String[] FNAME_SUFFIX_VOWEL = {"vowel", "kim", "lik", "co", "mie", "nie", "gan", "se", "ne"};
	private static final String[] FNAME_SUFFIX_CONSONANT = {"consonant", "am", "it", "ola", "en", "ian", "ie", "y", "io", "o", "ea", "uel", "ell", "el", "iah", "ien", "tin", "ick", "ice", "ford", "ter"};
	
	public String getName() {
		String name = "";
		String prefix = "";
		String mid1 = "";
		String mid2 = "";
		String suffix = "";
		Random randFName = null;
		Random randLName = null;
		Random getPrefix = null;
		Random syllables = null;
		int whichPrefix, numOfSyllables, prefixIndex = 0;
		
		randFName = new Random();
		whichPrefix = randFName.nextInt(3);
		syllables = new Random();
		numOfSyllables = syllables.nextInt(3);
		
		switch (whichPrefix) {
			case 0:
				getPrefix = new Random();
				prefixIndex = getPrefix.nextInt(FNAME_PREFIX_ANY.length);
				prefix = FNAME_PREFIX_ANY[prefixIndex];
				break;
			case 1:
				getPrefix = new Random();
				prefixIndex = getPrefix.nextInt(FNAME_PREFIX_VOWEL.length);
				prefix = FNAME_PREFIX_VOWEL[prefixIndex];
				break;
			case 2:
				getPrefix = new Random();
				prefixIndex = getPrefix.nextInt(FNAME_PREFIX_CONSONANT.length);
				prefix = FNAME_PREFIX_CONSONANT[prefixIndex];
				break;
			default:
				getPrefix = new Random();
				prefixIndex = getPrefix.nextInt(FNAME_PREFIX_ANY.length);
				prefix = FNAME_PREFIX_ANY[prefixIndex];
				break;
		}
		
		name = prefix + mid1 + mid2 + suffix;
		return name;
	}
}