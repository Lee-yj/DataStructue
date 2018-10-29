package utils.Test;

import java.util.Random;

import utils.Stack.ArrayStack;
import utils.Stack.LinkedListStack;
import utils.Stack.Stack;

/**
 * �Ƚ�ArrayQueue��LoopQueue
 */
public class ArrayAndLinkedListStack {
	
	private static double testStack(Stack<Integer> q, int opCount){
		long startTime = System.nanoTime();
		
		Random random = new Random();
		for(int i = 0; i < opCount; i ++)
			q.push(random.nextInt(Integer.MAX_VALUE));
		for(int i = 0; i < opCount; i ++)
			q.pop();
		long endTime = System.nanoTime();
		
		return (endTime - startTime) / 1000000000.0; 
	}
	
	public static void main(String[] args) {
		int opCount = 10000000;
		ArrayStack<Integer> arrayStack = new ArrayStack<>();
		double time1 = testStack(arrayStack, opCount);
		System.out.println(time1);
		
		LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
		double time2 = testStack(linkedListStack, opCount);
		System.out.println(time2);
	}
}
