package utils.Queue;

public interface Queue<E> {
	//���
	void enqueue(E e);
	//����
	E dequeue();
	//�鿴����Ԫ��
	E getFront();
	
	int getSize();
	
	boolean isEmpty();
	
}
