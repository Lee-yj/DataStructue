package utils.RedBlackTree;

import java.util.ArrayList;

import utils.AVLTree.AVLTree;

public class Main3 {
	
	public static void main(String[] args) {
		
		int n = 20500000;
		ArrayList<Integer>	testData = new ArrayList<>();
		for(int i = 0; i < n; i ++)
			testData.add(i);
		
		// Test AVLTree
		long startTime = System.nanoTime();
		AVLTree<Integer, Integer> avl = new AVLTree<>();
		for(Integer x : testData)
			avl.add(x, null);
		long endTime = System.nanoTime();
		System.out.println("AVLT: " + (endTime - startTime) / 1000000000.0 + "s");
		
		// Test RBTree
		startTime = System.nanoTime();
		RBTree<Integer, Integer> rbt = new RBTree<>();
		for(Integer x : testData)
			rbt.add(x, null);
		endTime = System.nanoTime();
		System.out.println("RBT: " + (endTime - startTime) / 1000000000.0 + "s");
	}
}
