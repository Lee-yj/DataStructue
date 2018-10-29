package utils.Set.InterFace;

public interface Set<E> {
	//添加元素
	void add(E e);
	//删除元素
	void remove(E e);
	//查看是否包含元素
	boolean contains(E e);
	
	int getSize();
	
	boolean isEmpty();
}
