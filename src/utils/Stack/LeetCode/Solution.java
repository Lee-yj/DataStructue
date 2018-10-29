package utils.Stack.LeetCode;

import utils.Stack.ArrayStack;

public class Solution {
	public boolean isValid(String s) {
		ArrayStack<Character> stack = new ArrayStack<>();
		for(int i = 0; i < s.length(); i ++) {
			char c = s.charAt(i);
			if(c == '(' || c == '[' || c == '{'){
				stack.push(c);
			}else{
				if (stack.isEmpty())
					return false;
				char top = stack.pop();
				if(c == ')' && top != '('){
					return false;
				}
				if(c == ']' && top != '['){
					return false;				
				}
				if(c == '}' && top != '{'){
					return false;	
				}
			}
		}
		return stack.isEmpty();
	}
	
	
	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.isValid(""));
		System.out.println(solution.isValid("{}[]([]"));
	}
}




//Stack<Character> stack = new Stack<>();
//for(int i = 0; i < s.length(); i ++) {
//	char c = s.charAt(i);
//	if(c == '(' || c == '[' || c == '{'){
//		stack.push(c);
//	}
//	if(stack.isEmpty()) {
//		return false;
//	}
//	if(c == ')') {
//		char p = stack.peek();
//		if(p == '('){
//			stack.pop();
//		}else{
//			return false;
//		}
//	}
//	if(c == ']') {
//		char p = stack.peek();
//		if(p == '['){
//			stack.pop();
//		}else{
//			return false;
//		}
//	}
//	if(c == '}') {
//		char p = stack.peek();
//		if(p == '{'){
//			stack.pop();
//		}else{
//			return false;
//		}
//	}
//}
//if(stack.isEmpty()){
//	return true;
//}else{
//	return false;
//}