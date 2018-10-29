package utils.MaxHeapAndPriorityQueue;

import utils.Queue.Queue;

//基于MaxHeap实现的PriorityQueue
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {
	private MaxHeap<E> maxHeap;
	
	public PriorityQueue() {
		maxHeap = new MaxHeap<>();
	}
	public PriorityQueue(E[] arr) {
		maxHeap = new MaxHeap<>(arr);
	}

	@Override
	public void enqueue(E e) {
		maxHeap.add(e);
	}
	
	@Override //仅仅是取出堆顶元素，不加入新元素
	public E dequeue() {
		return maxHeap.extractMax();
	}
	//取出堆顶元素，并替换入新元素e
	public E replace(E e){ 
		return maxHeap.replace(e);
	}
	@Override
	public E getFront() {
		return maxHeap.findMax();
	}

	@Override
	public int getSize() {
		return maxHeap.size();
	}

	@Override
	public boolean isEmpty() {
		return maxHeap.size() == 0;
	}

}
