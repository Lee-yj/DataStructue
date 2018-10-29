package utils.MaxHeapAndPriorityQueue;

import utils.Queue.Queue;

//����MaxHeapʵ�ֵ�PriorityQueue
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
	
	@Override //������ȡ���Ѷ�Ԫ�أ���������Ԫ��
	public E dequeue() {
		return maxHeap.extractMax();
	}
	//ȡ���Ѷ�Ԫ�أ����滻����Ԫ��e
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
