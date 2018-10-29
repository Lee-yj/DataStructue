package utils.Queue;

import utils.DynamicArray.Array;
/**
 * 数组-线性队列
 * @author lenovo
 * @param <E>
 */
public class ArrayQueue<E> implements Queue<E> {
	
	private Array<E> array;
	
	public ArrayQueue(int capacity) {
		array = new Array<>(capacity);
	}
	
	public ArrayQueue() {
		array = new Array<>();
	}

	@Override
	public void enqueue(E e) {
		array.addLast(e);
	}

	@Override
	public E dequeue() {
		return array.removeFirst();
	}

	@Override
	public E getFront() {
		return array.getFirst();
	}

	@Override
	public int getSize() {
		return array.getSize();
	}
	
	public int getCapacity(){
		return array.getCapacity();
	}

	@Override
	public boolean isEmpty() {
		return array.isEmpty();
	}
	
	@Override
	public String toString(){
		StringBuilder res = new StringBuilder();
		res.append(String.format("ArrayQueue: size = %d, capacity = %d\n", getSize(), getCapacity()));
		res.append("front-[");
		for(int i = 0; i < array.getSize(); i ++) {
			res.append(array.get(i));
			if(i != array.getSize() - 1) {
				res.append(",");
			}
		}
		res.append("]-tail");
		return res.toString();
	}
	
	public static void main(String[] args) {
		ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
		for(int i = 0; i < 10; i ++){
			arrayQueue.enqueue(i);
			System.out.println(arrayQueue);
			
			if(i % 3 == 2){
				arrayQueue.dequeue();
				System.out.println(arrayQueue);
			}
		}
	}
	
}
