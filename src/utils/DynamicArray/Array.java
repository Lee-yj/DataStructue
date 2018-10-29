package utils.DynamicArray;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class Array<E> {
	
	private E[] data;
	private int size;

	//构造函数，传入数组的容量capacity构造Array
	public Array(int capacity){
		data = (E[])new Object[capacity];
		size = 0;
	}
	//无参构造函数，默认capacity的容量为10
	public Array(){
		this(10);
	}
	public Array(E[] arr){// 通过传入的数组构建成动态数组Array
		data = (E[]) new Object[arr.length];
		for(int i = 0; i < arr.length; i ++)
			data[i] = arr[i];
		size = arr.length;
	}
	//获取数组中的元素个数
	public int getSize(){
		return size;
	}
	//获取数组的容量capacity
	public int getCapacity(){
		return data.length;
	}
	//判断数组是否为空
	public boolean isEmpty(){
		return size == 0;
	}
	//向所有元素后添加一个元素e
	public void addLast(E e){
		add(size, e);
	}
	//向所有元素头的位置添加一个元素e
	public void addFirst(E e){
		add(0, e);
	}
	//向数组中指定的index的位置添加一个元素e
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
	//获取索引index位置的元素
	public E get(int index){
		if(index < 0 || index >= size){
			throw new IllegalArgumentException("Get failed, Index is illegal.");
		}
		return data[index];
	}
	//获取最后一个索引位置的元素
	public E getLast(){
		return get(size -1);
	}
	//获取第一个索引位置的元素
	public E getFirst(){
		return get(0);
	}
	
	//修改索引index位置的元素
	public void set(int index, E e){
		if(index < 0 || index >= size){
			throw new IllegalArgumentException("Delete failed, Index is illegal.");
		}
		data[index] = e;
	}
	//查找数组是否包含某个元素
	public boolean contains(E e){
		for(int i = 0; i < size; i ++){
			if (data[i].equals(e))
				return true;
		}
		return false;
	}
	//搜索数组中元素相应的索引，若不存在返回-1
	public int findIndex(E e){
		for(int i = 0; i < size; i ++){
			if (data[i].equals(e))
				return i;
		}
		return -1;
	}
	//若存在相同的元素，找出所有的相应索引
	public List<Integer> findAllIndex(E e){
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i < size; i ++){
			if(data[i].equals(e))
				list.add(i);
		}
		return list;
	}
	//从数组中删除任意索引位置index相应的一个元素，并返回删除的元素
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
	//从数组中删除第一个元素，并返回删除的元素
	public E removeFirst(){
		return remove(0);
	}
	//从数组中删除最后一个元素，并返回删除的元素
	public E removeLast(){
		return remove(size - 1);
	}
	//在数组中删除一个指定的元素
	public void removeElement(E e){
		int index = findIndex(e);
		if(index != -1){
			 remove(index);
		}
	}
	//在数组中删除所有的指定的元素
	public void removeAllElements(E e){
		while(findIndex(e) != -1){
			remove(findIndex(e));
		}
	}
	
	// 交换两个索引的元素的位置
	public void swap(int i, int j){
		if(i < 0 || i > size || j < 0 || j > size)  // 判断索引的正确性
			throw new IllegalArgumentException("Index is illegal!");
		E t = data[i];
		data[i] = data[j];
		data[j] = t;
	}
	
	//重构父类中的toString()
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
	//当数组中元素已满，对数组进行扩容
	private void resize(int newCapacity){
		E[] newData = (E[]) new Object[newCapacity];
		for(int i = 0; i < size; i ++){
			newData[i] = data[i];
		}
		data = newData;
	}
}
