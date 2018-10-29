package utils.Set;

import java.util.ArrayList;

import utils.LinkedList.LinkedList;
import utils.Set.InterFace.Set;

public class LinkedListSet<E> implements Set<E>{
	
	private LinkedList<E> linkedList;
	
	public LinkedListSet() {
		linkedList = new LinkedList<>();
	}

	@Override
	public void add(E e) {
		if(!linkedList.contains(e))
			linkedList.addFirstByDummyHead(e);
	}

	@Override
	public void remove(E e) {
		linkedList.removeElement(e);
	}

	@Override
	public boolean contains(E e) {
		return linkedList.contains(e);
	}

	@Override
	public int getSize() {
		return linkedList.getSize();
	}

	@Override
	public boolean isEmpty() {
		return linkedList.isEmpty();
	}
	
	public static void main(String[] args) {
		ArrayList<String> words = new ArrayList<>();
		System.out.println("Pride and Prejudice");
		if(FileOperation.readFile("pride-and-prejudice.txt", words)){
			System.out.println("Total words:" + words.size());
			
			LinkedListSet<String> sets = new LinkedListSet<>();
			for(String word : words){
				sets.add(word);
			}
			System.out.println("Real Total words:" + sets.getSize());
		}
		ArrayList<String> words1 = new ArrayList<>();
		System.out.println("a-tale-of-two-cities");
		if(FileOperation.readFile("a-tale-of-two-cities.txt", words1)){
			System.out.println("Total words:" + words1.size());
			
			LinkedListSet<String> sets1 = new LinkedListSet<>();
			for(String word : words1){
				sets1.add(word);
			}
			System.out.println("Real Total words:" + sets1.getSize());
		}
	}
}
