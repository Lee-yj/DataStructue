package utils.Test;

//import utils.Stack.ArrayStack;
import utils.Stack.LinkedListStack;

public class StackMain {

	public static void main(String[] args) {
		
//		ArrayStack<Integer> stack = new ArrayStack<>();
//		
//		for(int i = 0; i < 5; i ++) {
//			stack.push(i);
//			System.out.println(stack);
//		}
//		stack.pop();
//		System.out.println(stack);
		LinkedListStack<Integer> stack = new LinkedListStack<>();
		
		for(int i = 0; i < 5; i ++) {
			stack.push(i);
			System.out.println(stack);
		}
		stack.pop();
		System.out.println(stack);
	}

}