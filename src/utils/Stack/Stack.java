package utils.Stack;

public interface Stack<E> {
	
	int getSize();
	
	boolean isEmpty();
	//入栈
	void push(E e);
	//出栈
	E pop();
	//查看栈头元素
	E peek();
}
