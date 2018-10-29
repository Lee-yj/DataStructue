package utils.HashTable;

// Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
// Note: You may assume the string contain only lowercase letters.
public class LeetCode387 {
	
	public int firstUniqChar(String s) {
		
		int[] freq = new int[26]; // 用来存26个字母对应的频率
		
		for(int i = 0; i < s.length(); i ++)
			freq[s.charAt(i) - 'a'] ++;
		
		for(int i = 0; i < s.length(); i ++)
			if(freq[s.charAt(i) - 'a'] == 1)
				return i;
		
		return -1;
	}
}
