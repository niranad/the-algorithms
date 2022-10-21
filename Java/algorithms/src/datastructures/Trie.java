package datastructures;

import java.util.ArrayList;

public class Trie {
	private TrieNode root;
	private ArrayList<String> words;
	
	public Trie() {
		root = new TrieNode();
	}
	
	public void add(String word) {
		addWord(word, root);
	}
	
	private void addWord(String word, TrieNode root) {
		if (!word.isBlank()) {
			char letter = word.charAt(0);
			if (root.keys.containsKey(letter)) {
				addWord(word.substring(1), root.keys.get(letter));
			} else {
				TrieNode node = new TrieNode();
				root.keys.put(letter, node);
				
				if (word.length() == 1) {
					node.setEnd();
				}
				
				addWord(word.substring(1), root.keys.get(letter));
			}
		}
	}
	
	public boolean isWord(String word) {
		TrieNode root = this.root;
		
		while (!word.isBlank()) {
			char letter = word.charAt(0);
			if (root.keys.containsKey(letter)) {
				if (word.length() == 1) { 
					if (!root.keys.get(letter).isEnd()) {
						return false;
					}
				}
				word = word.substring(1);
				root = root.keys.get(letter);
			} else {
				return false;
			}
		}
		
		return true;
	}
	
	public ArrayList<String> retrieve() {
		words = new ArrayList<>();
		retrieveUtil(root, "");
		return words;
	}
	
	private void retrieveUtil(TrieNode root, String word) {
		if (root.keys.size() != 0) {
			for (Character key : root.keys.keySet()) {
				retrieveUtil(root.keys.get(key), word.concat(String.valueOf(key)));
			}
			if (root.isEnd()) {
				words.add(word);
			}
		} else {
			if (word.length() > 0) {
				words.add(word);
			}
		}
	}
}
