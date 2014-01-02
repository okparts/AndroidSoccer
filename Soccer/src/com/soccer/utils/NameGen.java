package com.soccer.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//1) +v means that next syllable must definitely start with a wovel.
//2) +c means that next syllable must definitely start with a consonant.
//3) -v means that this syllable can only added to another syllable, that ends with a wovel.
//4) -c means that this syllable can only added to another syllable, that ends with a consonant.

public class NameGen {

	// letter types
	private static final char[] VOWELS = {'a', 'e', 'i', 'o', 'u', 'ä', 'ö', 'ü', 'y'};
	private static final char[] CONSONANTS = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z'};
	
	// prefix types
	private static final String[] FNAME_PREFIX_ANY = {"any", "av", "ad", "ah", "al", "am", "an", "ar", "as", "at", "ax", "ay", "az", "ba", "be", "bo", "ca", "ce", "co", "da", "de", "di", "do", "dy", "ed", "el", "em", "er", "ez",  "ib", "im", "is", "jo", "ju", "ke", "ki", "ky", "le", "li", "lo", "lu", "ly", "mu", "my", "ne", "no", "ob", "ol", "om", "or", "os", "pa", "pe", "ra", "re", "ta", "te", "ti", "we", "xa", "xi", "za"};
	private static final String[] FNAME_PREFIX_VOWEL = {"+v", "bl", "br", "ch", "cl", "fl", "fr", "gl", "gr+ v", "kr", "ph", "pr", "rh", "sh", "so", "sp", "st", "th"};
	private static final String[] FNAME_PREFIX_CONSONANT = {"+c", "a", "aa","ae", "ai", "bi", "bu", "ci", "ea", "fa", "fe", "fi", "fo", "ga", "ge", "gi", "gu", "ha", "he", "hi", "ho", "hu", "ja", "ka", "kl", "ko", "ku", "la", "ma", "me", "mi", "mo", "na", "ni", "ri", "ro", "ru", "ry", "sa", "se", "si", "su", "to", "ty", "va", "vi", "wa", "ya", "yo", "yu", "ze", "zi", "zu"};
	
	// mid-syllabic types
	private static final String[] FNAME_MID_PRE_ANY_POST_ANY = {"any_any", "r", "rr", "y", "ro", "di", "ri", "san", "es", "ty", "ol", "rin", "mol", "gy", "yan", "el"};
	private static final String[] FNAME_MID_PRE_ANY_POST_VOWEL = {"any_vowel", "ir", "kad", "kar", "in", "is", "id", "ph", "gel", "th", "ton", "tom", "tot", "lom", "eth", "in"};
	private static final String[] FNAME_MID_PRE_ANY_POST_CONSONANT = {"any_consonant", "ry +c", "ro", "ha -v +c", "ki -v +c", "ra +c", "sal -v +v", "bas -v +v", "kad +v", "kar +v", "kha +c", "mal +c", "in +v", "is +v", "el -c", "id +v", "ir -c", "u -c", "ub -c +v", "e -c", "ai -c +c", "di", "it -c +v", "ri", "on -v +c", "ou -c +c", "ta +c", "ad -c +v", "ns -v", "rl -c +c", "ay -c", "an -c", "ek -c +c", "san", "es", "ex -c +c", "ie -c +c", "hai -v +c", "sh -v", "st -v +v", "les +c", "lis +c", "ph +v", "ty", "ol", "rin", "dr -c +c", "gel +v", "mol", "th +v", "ton +v", "af -c", "chi +c", "gy", "tom +v", "tot +v", "tu +c", "yan", "vin -c", "zed -v +v", "ie -c +c", "ned -v +v", "rt -v +v", "tol +c", "lom +v", "to -c", "si +c", "xt -v +v", "n -v", "ia -c +c", "oo -c +c", "uc -c +c", "bh -v +v", "rh -v +v", "tch -v +v", "el", "eth +v", "il -c", "in +v"};
	private static final String[] MIDDLE = {"d -v", "hr -v +c", "r", "rr", "ry +c", "y", "z -v +v", "ba -v +c", "bb -v +v", "bd -v +v", "al -c", "ih -c +v", "ir +v", "ul -c", "ur -c", "ro", "ha -v +c", "ki -v +c", "ra +c", "sal -v +v", "bas -v +v", "kad +v", "kar +v", "kha +c", "mal +c", "in +v", "is +v", "el -c", "id +v", "ir -c", "u -c", "ub -c +v", "e -c", "ai -c +c", "di", "it -c +v", "ri", "on -v +c", "ou -c +c", "ta +c", "ad -c +v", "ns -v", "rl -c +c", "ay -c", "an -c", "ek -c +c", "san", "es", "ex -c +c", "ie -c +c", "hai -v +c", "sh -v", "st -v +v", "les +c", "lis +c", "ph +v", "ty", "ol", "rin", "dr -c +c", "gel +v", "mol", "th +v", "ton +v", "af -c", "chi +c", "gy", "tom +v", "tot +v", "tu +c", "yan", "vin -c", "zed -v +v", "ie -c +c", "ned -v +v", "rt -v +v", "tol +c", "lom +v", "to -c", "si +c", "xt -v +v", "n -v", "ia -c +c", "oo -c +c", "uc -c +c", "bh -v +v", "rh -v +v", "tch -v +v", "el", "eth +v", "il -c", "in +v", ""};
	
	// suffix types
	private static final String[] FNAME_SUFFIX_ANY = {};
	private static final String[] FNAME_SUFFIX_VOWEL = {};
	private static final String[] FNAME_SUFFIX_CONSONANT = {};
	
	public NameGen() {
		 
	}
	
	public String getName() {
		String name = "";
		return name;
	}
}