package utils.Trie;

import java.util.ArrayList;

import utils.Set.BSTSet;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("a-tale-of-two-cities");
		ArrayList<String> words = new ArrayList<>();

		if(FileOperation.readFile("a-tale-of-two-cities.txt", words)){
			long startTime = System.nanoTime();
			
			BSTSet<String> sets = new BSTSet<>();
			for(String word : words){
				sets.add(word);
			}
			System.out.println("Total words:" + sets.getSize());
			for(String word : words){
				sets.contains(word);
			}
			
			long endTime = System.nanoTime();
			System.out.println("BinarySearchTree-Set:" + (endTime - startTime) / 1000000000.0);
			
			//------
			
			startTime = System.nanoTime();
			
			Trie trie = new Trie();
			for(String word : words){
				trie.add(word);
			}
			System.out.println("Total words:" + trie.getSize());
			for(String word : words){
				trie.contains(word);
			}
			
			endTime = System.nanoTime();
			System.out.println("TrieTree-Set:" + (endTime - startTime) / 1000000000.0);
		}
	}
}
