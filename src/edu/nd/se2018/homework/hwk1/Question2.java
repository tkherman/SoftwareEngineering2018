package edu.nd.se2018.homework.hwk1;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Question2 {

	public Question2(){}
	
	public String getMostFrequentWord(String input, String stopwords){
		String[] stopwordsArray = stopwords.split(" ", 0);
		String[] inputWords = input.split(" ", 0);
		HashSet<String> stopwordsSet = new HashSet<String>(Arrays.asList(stopwordsArray));
		HashMap<String, Integer> wordCounter = new HashMap<String, Integer>();
		
		// Count the words in input
		for (String inputWord : inputWords) {
			if (!wordCounter.containsKey(inputWord)) {
				wordCounter.put(inputWord, 1);
			} else {
				wordCounter.put(inputWord, wordCounter.get(inputWord) + 1);
			}
		}
		
		String winner = "";
		int maxCount = 0;
		boolean tie = false;
		
		// Run through the HashMap to determine the most frequently occurring word
		// while using the boolean tie to keep track of ties
		for (HashMap.Entry<String, Integer> item : wordCounter.entrySet()) {
			String word = item.getKey();
			Integer count = item.getValue();
			
			// Skip the word if it's a stopword
			if (stopwordsSet.contains(word)) {
				continue;
			}
			
			// New max found, reset tie and update maxCount and winner word
			if (count > maxCount) {
				maxCount = count;
				winner = word;
				tie = false;
			// Tie found
			} else if (count == maxCount && word != winner) {
				tie = true;
			}
		}
		
		if (tie) {
			return null;
		} else {
			return winner;
		}
	}
}
