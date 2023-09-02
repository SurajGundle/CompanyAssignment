package com.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortlistingAssignment {
	
	private List<String> list;
    private Map<String, Integer> count;
    
    public ShortlistingAssignment() {
        this.list = new ArrayList<>();
        this.count = new HashMap<>();
    }
    
    
    public void add(String sentence) {
        list.add(sentence);
        sentence = sentence.toLowerCase();
        String[] words = sentence.split("[\\s.,?!]+");
        for (String word : words) {
            word = word.replaceAll("[^a-zA-Z0-9.,?! ]", "");
            //System.out.println(word);
            if (!word.isEmpty()) {
                count.put(word, count.getOrDefault(word, 0) + 1);
            }
        }
    }
    
    public List<String> search(String word, int K) {
        word = word.toLowerCase(); // Convert the search word to lowercase
        List<String> result = new ArrayList<>();
        
        for (String sentence : list) {
            if (sentence.toLowerCase().contains(word)) {
                result.add(sentence);
            }
        }
        result.sort((s1, s2) -> Integer.compare(
                count.getOrDefault(s2.toLowerCase(), 0),
                count.getOrDefault(s1.toLowerCase(), 0)));

        return result.subList(0, Math.min(K, result.size()));
    }


	public static void main(String[] args) {
		
		ShortlistingAssignment obj = new ShortlistingAssignment();
		obj.add("Hello World! How is the world today?");
		obj.add("This is great");
		obj.add("The vast majority of the countries, including all of the great powers, fought as part of two opposing military alliances: the Allies and the Axis");
        System.out.println(obj.search("world", 2)); 

        obj.add("There have been two world wars so far. World needs peace! World doesn't need another war.");
        System.out.println(obj.search("world", 2)); 
	}

}
