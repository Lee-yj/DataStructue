package utils.Stack;

public interface Stack<E> {
	
	int getSize();
	
	boolean isEmpty();
	//��ջ
	void push(E e);
	//��ջ
	E pop();
	//�鿴ջͷԪ��
	E peek();
}
