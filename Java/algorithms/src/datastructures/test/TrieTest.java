package datastructures.test;

import datastructures.Trie;

public class TrieTest {

	public static void main(String[] args) {
		Trie trie = new Trie();

		trie.add("Programming");
		trie.add("Programmingly");
		trie.add("Programming is fun");
		trie.add("Programming is fun provided you keep practicing");
		trie.add("You should work hard learning to program");
		trie.add("God is good");

		System.out.printf("Words: %s", trie.retrieve());
		System.out.printf("%ntrie.isWord(\"Programming\"): %b",
			trie.isWord("Programming"));
		System.out.printf("%ntrie.isWord(\"Programming is fun\"): %b",
			trie.isWord("Programming is fun"));
		System.out.printf(
			"%ntrie.isWord(\"You should work hard learning to program\"): %b",
			trie.isWord("You should work hard learning to program"));
		System.out.printf("%ntrie.isWord(\"Programmatically\"): %b",
			trie.isWord("Programmatically"));

	}

}
