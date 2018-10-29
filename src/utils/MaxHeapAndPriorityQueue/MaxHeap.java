package utils.MaxHeapAndPriorityQueue;

import java.util.Random;

import utils.DynamicArray.Array;

/*
 * ʹ�ö�̬�������洢����
 */
public class MaxHeap<E extends Comparable<E>> {
	
	private Array<E> data;
	
	public MaxHeap(int capacity) {
		data = new Array<>(capacity);
	}
	public MaxHeap() {
		data = new Array<>();
	}
	public MaxHeap(E[] arr){ //����������������heap����heapify����--���������������ɶ�
		data = new Array<>(arr);
		for(int i = parent(arr.length - 1); i >= 0; i --)
			shiftDown(i);	//heapify�������һ����Ҷ�ӽڵ㿪ʼ��������shift down����
	}
	
	//���ض��е�Ԫ�ظ���
	public int size(){
		return data.getSize();
	}
	
	//�ж϶����Ƿ�Ϊ�գ�����һ������ֵ
	public boolean isEmpty(){
		return data.isEmpty();
	}
	
	//������ȫ�������������ʾ�У�һ����������ʾ��Ԫ�صĸ��ڵ�������������Ǵ�0��ʼ�洢
	private int parent(int index){
		if(index == 0)
			throw new IllegalArgumentException("index-0 is root, doesn't have parent");
		return (index - 1) / 2;
	}
	//������ȫ�������������ʾ�У�һ����������ʾ��Ԫ�ص��������ڵ�������������Ǵ�0��ʼ�洢
	private int leftChild(int index){
		return index * 2 + 1;
	}
	//������ȫ�������������ʾ�У�һ����������ʾ��Ԫ�ص��������ڵ�������������Ǵ�0��ʼ�洢
	private int rightChild(int index){
		return index * 2 + 2;
	}
	
	// ��heap�����Ԫ�أ� 1.����������λ�����Ԫ��
//					   2.����������λ������Ѱ����Ӧ�ĸ��ڵ��Ԫ�أ��Ƚϴ�С
	public void add(E e){
		data.addLast(e);
		shiftUp(data.getSize() - 1);
	}
	private void shiftUp(int k) {
		// ����λ�ò���Ϊ0�� ���丸�ڵ��Ԫ��С�ڸýڵ�Ԫ��  ������£��ϸ����������������ڵ��Ԫ��
		while(k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0){
			data.swap(k, parent(k));
			k = parent(k);
		}
	}
	
	// ȡ��heap������Ԫ�أ�1.ȡ���Ѷ�Ԫ�أ����Ѷ����ĩ��������ɾ����ĩ
//						  2.�Ѷ�Ԫ�����ν����³�����---------------heap��Ԫ����һ
	public E extractMax(){
		E ret = findMax();
		
		data.swap(0, data.getSize() - 1);
		data.removeLast();
		shiftDown(0);
		
		return ret;
	}
	//�����е����Ԫ��
	public E findMax(){
		if(data.getSize() == 0)
			throw new IllegalArgumentException("Can not findMax when heap is empty!");
		return data.get(0);
	}
	private void shiftDown(int k) {
		//ѭ��������1.�ýڵ����Ҷ�ӽڵ㣬�����ӵ�����С���ܳ���
		while(leftChild(k) < data.getSize()){
			//�Ƚ�k�ڵ���k���ҽڵ������Ľڵ�
			int j  = leftChild(k); 								//k���ӽڵ�
			if(j + 1 < data.getSize() &&   						//�ж��Ƿ����Һ���
					data.get(j + 1).compareTo(data.get(j)) > 0){//�����Һ��ӣ����Һ��Ӵ�������
				j = rightChild(k);
			}// data[j]ΪK���Һ��������ֵ
			
			if(data.get(k).compareTo(data.get(j)) >= 0)
				break;
			data.swap(k, j);
			k = j;
		}
	}
	
	// replace--ȡ�����ֵ���ٷ���һ��Ԫ��e��
//				ֱ�ӽ��Ѷ�Ԫ���滻�ɺ���shift down��һ��O(logN)����
	public E replace(E e){
		E ret = findMax();
		data.set(0, e);
		shiftDown(0);
		return ret;
	}
	
	
	public static void main(String[] args) {
		int n = 10000000;
		Random random = new Random();
		
		long start1 = System.nanoTime();
		MaxHeap<Integer> heap1 = new MaxHeap<>();
		for(int i = 0; i < n; i ++)
			heap1.add(random.nextInt(Integer.MAX_VALUE));
		long end1 = System.nanoTime();
		System.out.println("without heapify" + (end1 - start1) / 1000000000.0);
		
		long start2 = System.nanoTime();
		Integer[] data = new Integer[n];
		for(int i = 0; i < n; i ++)
			data[i] = random.nextInt(Integer.MAX_VALUE);
		MaxHeap<Integer> heap = new MaxHeap<>(data);
		long end2 = System.nanoTime();
		System.out.println("with heapify" + (end2 - start2) / 1000000000.0);
		
		int[] arr = new int[n];
		for(int i = 0; i < n; i ++){
			arr[i] = heap.extractMax();
		}
		for(int i = 1; i < n; i ++){
			if(arr[i-1] < arr[i]){
				throw new IllegalArgumentException("Error");
			}
		}
		System.out.println("Test Maxheap is success!");
	}
}
