package utils.DynamicArray;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class Array<E> {
	
	private E[] data;
	private int size;

	//���캯�����������������capacity����Array
	public Array(int capacity){
		data = (E[])new Object[capacity];
		size = 0;
	}
	//�޲ι��캯����Ĭ��capacity������Ϊ10
	public Array(){
		this(10);
	}
	public Array(E[] arr){// ͨ����������鹹���ɶ�̬����Array
		data = (E[]) new Object[arr.length];
		for(int i = 0; i < arr.length; i ++)
			data[i] = arr[i];
		size = arr.length;
	}
	//��ȡ�����е�Ԫ�ظ���
	public int getSize(){
		return size;
	}
	//��ȡ���������capacity
	public int getCapacity(){
		return data.length;
	}
	//�ж������Ƿ�Ϊ��
	public boolean isEmpty(){
		return size == 0;
	}
	//������Ԫ�غ����һ��Ԫ��e
	public void addLast(E e){
		add(size, e);
	}
	//������Ԫ��ͷ��λ�����һ��Ԫ��e
	public void addFirst(E e){
		add(0, e);
	}
	//��������ָ����index��λ�����һ��Ԫ��e
	public void add(int index, E e){
		if(index < 0 || index > size){
			throw new IllegalArgumentException("Add failed, Require index >= 0 and index <= size.");
		}
		if(size == data.length){
			resize(2 * data.length);
		}
		for(int i = size - 1; i >= index; i --){
			data[i + 1] = data[i];
		}
		data[index] = e;
		size ++;
	}
	//��ȡ����indexλ�õ�Ԫ��
	public E get(int index){
		if(index < 0 || index >= size){
			throw new IllegalArgumentException("Get failed, Index is illegal.");
		}
		return data[index];
	}
	//��ȡ���һ������λ�õ�Ԫ��
	public E getLast(){
		return get(size -1);
	}
	//��ȡ��һ������λ�õ�Ԫ��
	public E getFirst(){
		return get(0);
	}
	
	//�޸�����indexλ�õ�Ԫ��
	public void set(int index, E e){
		if(index < 0 || index >= size){
			throw new IllegalArgumentException("Delete failed, Index is illegal.");
		}
		data[index] = e;
	}
	//���������Ƿ����ĳ��Ԫ��
	public boolean contains(E e){
		for(int i = 0; i < size; i ++){
			if (data[i].equals(e))
				return true;
		}
		return false;
	}
	//����������Ԫ����Ӧ���������������ڷ���-1
	public int findIndex(E e){
		for(int i = 0; i < size; i ++){
			if (data[i].equals(e))
				return i;
		}
		return -1;
	}
	//��������ͬ��Ԫ�أ��ҳ����е���Ӧ����
	public List<Integer> findAllIndex(E e){
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i < size; i ++){
			if(data[i].equals(e))
				list.add(i);
		}
		return list;
	}
	//��������ɾ����������λ��index��Ӧ��һ��Ԫ�أ�������ɾ����Ԫ��
	public E remove(int index){
		if(index < 0 || index >= size){
			throw new IllegalArgumentException("Remove failed, Index is illegal.");
		}
		E ret = data[index];
		for(int i = index + 1; i < size; i ++){
			data[i - 1] = data[i];
		}
		size --;
		data[size] = null;  // loitering objects 
		//Lazy deal
		if(size == data.length / 4 && data.length / 2 != 0){
			resize(data.length / 2);
		}
		return ret;
	}
	//��������ɾ����һ��Ԫ�أ�������ɾ����Ԫ��
	public E removeFirst(){
		return remove(0);
	}
	//��������ɾ�����һ��Ԫ�أ�������ɾ����Ԫ��
	public E removeLast(){
		return remove(size - 1);
	}
	//��������ɾ��һ��ָ����Ԫ��
	public void removeElement(E e){
		int index = findIndex(e);
		if(index != -1){
			 remove(index);
		}
	}
	//��������ɾ�����е�ָ����Ԫ��
	public void removeAllElements(E e){
		while(findIndex(e) != -1){
			remove(findIndex(e));
		}
	}
	
	// ��������������Ԫ�ص�λ��
	public void swap(int i, int j){
		if(i < 0 || i > size || j < 0 || j > size)  // �ж���������ȷ��
			throw new IllegalArgumentException("Index is illegal!");
		E t = data[i];
		data[i] = data[j];
		data[j] = t;
	}
	
	//�ع������е�toString()
	@Override
	public String toString(){
		StringBuffer res = new StringBuffer();
		res.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
		res.append("[");
		for(int i = 0; i < size; i ++){
			res.append(data[i]);
			if(i != size - 1){
				res.append(",");
			}
		}
		res.append("]");
		return res.toString();
	}
	//��������Ԫ���������������������
	private void resize(int newCapacity){
		E[] newData = (E[]) new Object[newCapacity];
		for(int i = 0; i < size; i ++){
			newData[i] = data[i];
		}
		data = newData;
	}
}
