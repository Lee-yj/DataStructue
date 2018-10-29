package utils.Queue;

public interface Queue<E> {
	//入队
	void enqueue(E e);
	//出队
	E dequeue();
	//查看队首元素
	E getFront();
	
	int getSize();
	
	boolean isEmpty();
	
}
