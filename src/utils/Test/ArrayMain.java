package utils.Test;

import utils.DynamicArray.Array;

public class ArrayMain {

	public static void main(String[] args) {
		
		Array<Integer> array = new Array<>(15);
		for(int i = 0; i < array.getCapacity(); i ++){
			array.addLast(i); 
		}
		System.out.println(array);
		array.addLast(10);
		array.addFirst(1);
		System.out.println(array);
		
		array.removeFirst();
		array.removeFirst();
		System.out.println(array);
		array.removeLast();
		System.out.println(array);
//		Array<Student> student = new Array<>();
//		student.addLast(new Student("ALice", 90));
//		student.addLast(new Student("Tom", 99));
//		student.addLast(new Student("Bob", 88));
//		System.out.println(student.toString());
	}

}
