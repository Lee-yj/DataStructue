package utils.Trie;

import java.util.TreeMap;

//基于Java的TreeMap来实现Node,同时这Trie没有使用泛型，默认是Character
//专门为String类型的数据结构
public class Trie {
	
	private class Node{
		public boolean isWord;
		public TreeMap<Character, Node> next;
		
		public Node(boolean isWord) {
			this.isWord = isWord;
			next = new TreeMap<>();
		}
		public Node() {
			this(false);
		}
	}
	private Node root;
	private int size;
	
	public Trie() {
		root = new Node();
		size = 0;
	}

	//Trie中添加一个新的单词
	public void add(String word){
		Node cur = root;
		for(int i = 0; i < word.length(); i ++){
			char c = word.charAt(i);
			if(cur.next.get(c) == null)
				cur.next.put(c, new Node());
			cur = cur.next.get(c);
		}
		if(!cur.isWord){ // 确认是否为新的单词
			cur.isWord = true;
			size ++;
		}
	}
	
	//查询单词是否在Trie中
	public boolean contains(String word){
		Node cur = root;
		for(int i = 0; i < word.length(); i ++){
			char c = word.charAt(i);
			if(cur.next.get(c) == null)
				return false;
			cur = cur.next.get(c);
		}
		return cur.isWord;
	}
	
	//查询是否在Trie中有单词以prefix为前缀
	public boolean isPrefix(String prefix){
		Node cur = root;
		for(int i = 0; i < prefix.length(); i ++){
			char c = prefix.charAt(i);
			if(cur.next.get(c) == null)
				return false;
			cur = cur.next.get(c);
		}
		return true; //若prefix可以for循环结束，说明有
	}
	
	//获取Trie中存储的单词数量
	public int getSize(){
		return size;
	}
	
	
}
