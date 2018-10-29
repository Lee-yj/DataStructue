package utils.AVLTree;

import java.util.ArrayList;
import java.util.Collections;

import utils.Map.AVLMap;
import utils.Map.BSTMap;
import utils.Set.FileOperation;

public class Main {
	public static void main(String[] args) {
		System.out.println("a-tale-of-two-cities");
		ArrayList<String> words = new ArrayList<>();
		if(FileOperation.readFile("a-tale-of-two-cities.txt", words)){
			// !!!--words排序
			Collections.sort(words);
			
			// BSTMap
			long startTime = System.nanoTime();
			BSTMap<String, Integer> bstMap = new BSTMap<>();
			for(String word : words){
				if(bstMap.contains(word)){
					bstMap.set(word, bstMap.get(word) + 1);
				}else{
					bstMap.add(word, 1);
				}
			}
			for(String word : words) 
				bstMap.contains(word);
			long endTime = System.nanoTime();
			System.out.println("Total different words:" + bstMap.getSize());
			System.out.println("Frequency of PREIDE:" + bstMap.get("city"));
			System.out.println("BSTMap-统计词频:" + (endTime - startTime) / 1000000000.0);
			
			// AVLTree
			startTime = System.nanoTime();
			AVLTree<String, Integer> avlTree = new AVLTree<>();
			for(String word : words){
				if(avlTree.contains(word)){
					avlTree.set(word, avlTree.get(word) + 1);
				}else{
					avlTree.add(word, 1);
				}
			}
			for(String word : words)
				avlTree.contains(word);
			endTime = System.nanoTime();
			System.out.println("Total different words:" + avlTree.getSize());
			System.out.println("Frequency of PREIDE:" + avlTree.get("city"));
			System.out.println("BSTMap-统计词频:" + (endTime - startTime) / 1000000000.0);
			
			// AVLMap
			startTime = System.nanoTime();
			AVLMap<String, Integer> avlMap = new AVLMap<>();
			for(String word : words){
				if(avlMap.contains(word)){
					avlMap.set(word, avlMap.get(word) + 1);
				}else{
					avlMap.add(word, 1);
				}
			}
			for(String word : words)
				avlMap.contains(word);
			endTime = System.nanoTime();
			System.out.println("Total different words:" + avlMap.getSize());
			System.out.println("Frequency of PREIDE:" + avlMap.get("city"));
			System.out.println("BSTMap-统计词频:" + (endTime - startTime) / 1000000000.0);
		}
	}
}
