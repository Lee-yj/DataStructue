package utils.Stack;

import utils.LinkedList.LinkedList;

public class LinkedListStack<E> implements Stack<E>{
	
	private LinkedList<E> list;
	
	public LinkedListStack() {
		list = new LinkedList<>();
	}
	
	@Override
	public int getSize() {
		return list.getSize();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void push(E e) {
		list.addFirstByDummyHead(e);
	}

	@Override
	public E pop() {
		return list.removeFirst();
	}

	@Override
	public E peek() {
		return list.getFirst();
	}
	
	@Override
	public String toString(){
		StringBuilder res = new StringBuilder();
		res.append("LinkListStack: top ");
		res.append(list);
		return res.toString();
	}
	
}