package utils.RedBlackTree;

import java.util.ArrayList;

import utils.AVLTree.AVLTree;
import utils.Map.BSTMap;
import utils.Set.FileOperation;

public class Main {
	public static void main(String[] args) {
		System.out.println("pride-and-prejudice");
		ArrayList<String> words = new ArrayList<>();
//		if(FileOperation.readFile("a-tale-of-two-cities.txt", words)){
		if(FileOperation.readFile("pride-and-prejudice.txt", words)){
			// !!!--words排序
//			Collections.sort(words);
			
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
			System.out.println();
			
			// RBTree
			startTime = System.nanoTime();
			RBTree<String, Integer> rbTree = new RBTree<>();
			for(String word : words){
				if(rbTree.contains(word)){
					rbTree.set(word, rbTree.get(word) + 1);
				}else{
					rbTree.add(word, 1);
				}
			}
			for(String word : words)
				rbTree.contains(word);
			endTime = System.nanoTime();
			System.out.println("Total different words:" + rbTree.getSize());
			System.out.println("Frequency of PREIDE:" + rbTree.get("city"));
			System.out.println("RBTree-统计词频:" + (endTime - startTime) / 1000000000.0);
			System.out.println();
			
			// AVLTree
			startTime = System.nanoTime();
			AVLTree<String, Integer> avlMap = new AVLTree<>();
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
			System.out.println("AVLTree-统计词频:" + (endTime - startTime) / 1000000000.0);
		}
	}
}
