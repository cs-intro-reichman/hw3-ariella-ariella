/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I an Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "kglhi";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		boolean isAnagram = true;
		String processedStr1 = preProcess(str1);
		String processedStr2 = preProcess(str2);
		if (processedStr1.length() != processedStr2.length()) {
			isAnagram = false;
		} else {
			for (int i = 0; i < processedStr1.length(); i++) {
				int indexOfLetter = processedStr2.indexOf(processedStr1.charAt(i));
				if (indexOfLetter == -1) {
					isAnagram = false;
					break;
				}
				String Str2PartOne = processedStr2.substring(0, indexOfLetter);
				String Str2PartTwo = processedStr2.substring(indexOfLetter + 1);
				processedStr2 = Str2PartOne +  Str2PartTwo;
			}
		}
		return isAnagram;
	}
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		String lowerCaseLetterBank = "abcdefghijklmnopqrstuvwxyz";
		String upperCaseLetterBank = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String stringOfLetters = "";
		for (int index = 0; index < str.length(); index ++) {
			int indexOfUpper = upperCaseLetterBank.indexOf(str.charAt(index));
			int indexOfLower = lowerCaseLetterBank.indexOf(str.charAt(index));
			if ((indexOfUpper == -1) && (indexOfLower == -1)) {
				continue;
			} else if ((indexOfUpper == -1) && (indexOfLower != -1)) {
				stringOfLetters = stringOfLetters + str.charAt(index);
			} else {
				char lowerCaseChar = lowerCaseLetterBank.charAt(indexOfUpper);
				stringOfLetters = stringOfLetters + lowerCaseChar;
			}
		}
		return stringOfLetters;
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		String anagram = "";
		int length = str.length();
		for (int i = 0; i < length; i++) {
			int randomIndex = (int) (Math.random() * str.length());
			anagram += str.charAt(randomIndex);
			str = str.substring(0, randomIndex) + str.substring(randomIndex + 1);
		}
		return anagram;
	}
}
