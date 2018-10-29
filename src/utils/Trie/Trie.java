package utils.Trie;

import java.util.TreeMap;

//����Java��TreeMap��ʵ��Node,ͬʱ��Trieû��ʹ�÷��ͣ�Ĭ����Character
//ר��ΪString���͵����ݽṹ
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

	//Trie�����һ���µĵ���
	public void add(String word){
		Node cur = root;
		for(int i = 0; i < word.length(); i ++){
			char c = word.charAt(i);
			if(cur.next.get(c) == null)
				cur.next.put(c, new Node());
			cur = cur.next.get(c);
		}
		if(!cur.isWord){ // ȷ���Ƿ�Ϊ�µĵ���
			cur.isWord = true;
			size ++;
		}
	}
	
	//��ѯ�����Ƿ���Trie��
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
	
	//��ѯ�Ƿ���Trie���е�����prefixΪǰ׺
	public boolean isPrefix(String prefix){
		Node cur = root;
		for(int i = 0; i < prefix.length(); i ++){
			char c = prefix.charAt(i);
			if(cur.next.get(c) == null)
				return false;
			cur = cur.next.get(c);
		}
		return true; //��prefix����forѭ��������˵����
	}
	
	//��ȡTrie�д洢�ĵ�������
	public int getSize(){
		return size;
	}
	
	
}
