package utils.Test;

import utils.LinkedList.LinkedList;

public class LinkedListMain {
	
	public static void main(String[] args) {
		
		LinkedList<Integer> linkedList = new LinkedList<>();
		for(int i = 0; i < 5; i ++){
			linkedList.addFirstByDummyHead(i);
			System.out.println(linkedList);
		}
		linkedList.addByDummyHead(2, 10);
		System.out.println(linkedList);
		
		linkedList.remove(2);
		System.out.println(linkedList);

		linkedList.removeFirst();
		System.out.println(linkedList);

		linkedList.removeLast();
		System.out.println(linkedList);
	}
}
