package utils.Set.InterFace;

public interface Set<E> {
	//���Ԫ��
	void add(E e);
	//ɾ��Ԫ��
	void remove(E e);
	//�鿴�Ƿ����Ԫ��
	boolean contains(E e);
	
	int getSize();
	
	boolean isEmpty();
}
