package datastructures;

import java.util.HashMap;

public class TrieNode {
	HashMap<Character, TrieNode> keys;
	boolean end;
	
	TrieNode() {
		keys = new HashMap<>();
	}
	
	void setEnd() {
		end = true;
	}
	
	boolean isEnd() {
		return end;
	}
}
