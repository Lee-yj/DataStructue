package utils.Set;

import java.util.TreeSet;

public class LeetCode804 {
	
	public int uniqueMorseRepresentations(String[] words) {
		String[] codes = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
		TreeSet<String> set = new TreeSet<>();
		
		for(String word : words){
			StringBuilder sb = new StringBuilder();
			for(char c : word.toCharArray()){
				sb.append(codes[c - 'a']);
			}
//			for(int i = 0; i < word.length(); i ++){
//				sb.append(codes[word.charAt(i) - 'a']);
//			}
			set.add(sb.toString());
		}
		return set.size();
    }
}
