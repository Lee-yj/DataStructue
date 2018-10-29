package utils.Set;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
	
	public static void main(String[] args) {
		ArrayList<String> words = new ArrayList<>();
		System.out.println("Pride and Prejudice");
		if(FileOperation.readFile("pride-and-prejudice.txt", words)){
			System.out.println("Total words:" + words.size());
			// ‘ˆº”∏¥‘”∂»£¨≈≈–Ú
			Collections.sort(words);
			
			System.out.println("----------------------------------");
			long startTime = System.nanoTime();
			BSTSet<String> sets = new BSTSet<>();
			for(String word : words)
				sets.add(word);
			for(String word : words)
				sets.contains(word);
			System.out.println("Real Total words:" + sets.getSize());
//			for(String word : words)
//				sets.remove(word);
			long endTime = System.nanoTime();
			System.out.println("BSTSet:" + (endTime - startTime) / 1000000000.0 + "s");
			
			System.out.println("----------------------------------");
			startTime = System.nanoTime();
			LinkedListSet<String> linkedListSet = new LinkedListSet<>();
			for(String word : words)
				linkedListSet.add(word);
			for(String word : words)
				linkedListSet.contains(word);
			System.out.println("Real Total words:" + linkedListSet.getSize());
//			for(String word : words)
//				linkedListSet.remove(word);
			endTime = System.nanoTime();
			System.out.println("linkedListSet:" + (endTime - startTime) / 1000000000.0 + "s");
			
			System.out.println("----------------------------------");
			startTime = System.nanoTime();
			AVLSet<String> avlSet = new AVLSet<>();
			for(String word : words)
				avlSet.add(word);
			for(String word : words)
				avlSet.contains(word);
			System.out.println("Real Total words:" + avlSet.getSize());
//			for(String word : words)
//				avlSet.remove(word);
			endTime = System.nanoTime();
			System.out.println("AVLSet:" + (endTime - startTime) / 1000000000.0 + "s");
			
		}
		
//		ArrayList<String> words1 = new ArrayList<>();
//		System.out.println("a-tale-of-two-cities");
//		if(FileOperation.readFile("a-tale-of-two-cities.txt", words1)){
//			System.out.println("Total words:" + words1.size());
//			
//			BSTSet<String> sets1 = new BSTSet<>();
//			for(String word : words1){
//				sets1.add(word);
//			}
//			System.out.println("Real Total words:" + sets1.getSize());
//		}
	}
}
