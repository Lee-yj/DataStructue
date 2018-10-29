package utils.MaxHeapAndPriorityQueue;

import java.util.Random;

import utils.DynamicArray.Array;

/*
 * 使用动态数组来存储最大堆
 */
public class MaxHeap<E extends Comparable<E>> {
	
	private Array<E> data;
	
	public MaxHeap(int capacity) {
		data = new Array<>(capacity);
	}
	public MaxHeap() {
		data = new Array<>();
	}
	public MaxHeap(E[] arr){ //将输入的数组整理成heap，用heapify操作--将任意的数组整理成堆
		data = new Array<>(arr);
		for(int i = parent(arr.length - 1); i >= 0; i --)
			shiftDown(i);	//heapify：从最后一个非叶子节点开始，依次作shift down操作
	}
	
	//返回堆中的元素个数
	public int size(){
		return data.getSize();
	}
	
	//判断堆中是否为空，返回一个布尔值
	public boolean isEmpty(){
		return data.isEmpty();
	}
	
	//返回完全二叉树的数组表示中，一个索引所表示的元素的父节点的索引，组数是从0开始存储
	private int parent(int index){
		if(index == 0)
			throw new IllegalArgumentException("index-0 is root, doesn't have parent");
		return (index - 1) / 2;
	}
	//返回完全二叉树的数组表示中，一个索引所表示的元素的左子树节点的索引，数组是从0开始存储
	private int leftChild(int index){
		return index * 2 + 1;
	}
	//返回完全二叉树的数组表示中，一个索引所表示的元素的右子树节点的索引，数组是从0开始存储
	private int rightChild(int index){
		return index * 2 + 2;
	}
	
	// 向heap中添加元素： 1.在数组的最后位置添加元素
//					   2.从最后的索引位置向上寻找相应的父节点的元素，比较大小
	public void add(E e){
		data.addLast(e);
		shiftUp(data.getSize() - 1);
	}
	private void shiftUp(int k) {
		// 索引位置不能为0， 且其父节点的元素小于该节点元素  的情况下：上浮动作，交换两个节点的元素
		while(k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0){
			data.swap(k, parent(k));
			k = parent(k);
		}
	}
	
	// 取出heap中最大的元素：1.取出堆顶元素，将堆顶与堆末交换，再删除堆末
//						  2.堆顶元素依次进行下沉动作---------------heap中元素少一
	public E extractMax(){
		E ret = findMax();
		
		data.swap(0, data.getSize() - 1);
		data.removeLast();
		shiftDown(0);
		
		return ret;
	}
	//看堆中的最大元素
	public E findMax(){
		if(data.getSize() == 0)
			throw new IllegalArgumentException("Can not findMax when heap is empty!");
		return data.get(0);
	}
	private void shiftDown(int k) {
		//循环条件：1.该节点就是叶子节点，即左孩子的索引小于总长度
		while(leftChild(k) < data.getSize()){
			//比较k节点与k左右节点中最大的节点
			int j  = leftChild(k); 								//k左孩子节点
			if(j + 1 < data.getSize() &&   						//判断是否有右孩子
					data.get(j + 1).compareTo(data.get(j)) > 0){//若有右孩子，若右孩子大于左孩子
				j = rightChild(k);
			}// data[j]为K左右孩子中最大值
			
			if(data.get(k).compareTo(data.get(j)) >= 0)
				break;
			data.swap(k, j);
			k = j;
		}
	}
	
	// replace--取出最大值，再放入一个元素e：
//				直接将堆顶元素替换成后在shift down，一次O(logN)操作
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
