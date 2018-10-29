package utils.RedBlackTree;

import java.util.ArrayList;
import java.util.Random;

import utils.AVLTree.AVLTree;
import utils.Map.BSTMap;

public class Main2 {
	
	public static void main(String[] args) {
		
		int n = 20500000;
		Random random = new Random();
		ArrayList<Integer>	testData = new ArrayList<>();
		for(int i = 0; i < n; i ++)
			testData.add(random.nextInt(Integer.MAX_VALUE));
		
		// Test BST
		long startTime = System.nanoTime();
		BSTMap<Integer, Integer> bst = new BSTMap<>();
		for(Integer x : testData)
			bst.add(x, null);
		long endTime = System.nanoTime();
		System.out.println("BST: " + (endTime - startTime) / 1000000000.0 + "s");
		
		// Test AVLTree
		startTime = System.nanoTime();
		AVLTree<Integer, Integer> avl = new AVLTree<>();
		for(Integer x : testData)
			avl.add(x, null);
		endTime = System.nanoTime();
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
