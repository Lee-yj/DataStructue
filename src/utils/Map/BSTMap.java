package utils.Map;

import java.util.ArrayList;

import utils.Map.InterFace.Map;
import utils.Set.FileOperation;

public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {
	
	private class Node{
		public K key;
		public V value;
		public Node left, right;
		
		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			left = null;
			right = null;
		}
	}
	private Node root;
	private int size;
	
	public BSTMap() {
		root = null;
		size = 0;
	}
	
	@Override
	public void add(K key, V value) {
		root = add(root, key, value);
	}
	private Node add(Node node, K key, V value) {
		if(node == null){
			size ++;
			return new Node(key, value);
		}
		if(key.compareTo(node.key) < 0){
			node.left = add(node.left, key, value);
		}
		else if(key.compareTo(node.key) > 0){
			node.right = add(node.right, key, value);
		}
		else{//key.compareTo(node.key) == 0
			node.value = value;
		}
		return node;
	}
	
	//返回以Node为根节点的二分搜索树中，key所在的节点
	private Node getNode(Node node, K key){
		if(node == null){
			return null;
		}
		if(key.compareTo(node.key) == 0)
			return node;
		else if(key.compareTo(node.key) < 0)
			return getNode(node.left, key);
		else// (key.compareTo(node.key) > 0)
			return getNode(node.right, key);
	}
	
	@Override
	public V get(K key) {
		Node node = getNode(root, key);
		return node == null ? null : node.value;
	}

	@Override
	public boolean contains(K key) {
		return getNode(root, key) != null;
	}
	
	@Override
	public V remove(K key) {
		Node node = getNode(root, key);
		if(node != null){
			root = remove(root, key);
			return node.value;
		}
		return null;
	}
	private Node remove(Node node, K key) {
		if(node == null)
			return null;
		if(key.compareTo(node.key) < 0){
			node.left = remove(node.left, key);
			return node;
		}
		else if(key.compareTo(node.key) > 0){ 
			node.right = remove(node.right, key);
			return node;
		}
		else{ // key.compareTo(node.key) == 0
			if(node.left == null){
				Node rightNode = node.right;
				node.right = null;
				size --;
				return rightNode;
			}
			if(node.right == null){
				Node leftNode = node.left;
				node.left = null;
				size --;
				return leftNode;
			}
			//待删除的节点左右子树都不为空
			// 1.找到比节点大的最小节点，右子树中的最小节点,根据这个节点信息建立后继节点；2.在删除右子树中的最小节点； 3.返回后继节点
			Node successor = minimum(node.right);
			successor.right = removeMin(node.right);  // 这里不用再size--，removeMin()方法中已经size--
			successor.left = node.left;
			node.left = node.right = null;
			return successor;
		}
	}

	//返回以node为根节点的BST中的最小值所在的节点
	private Node minimum(Node node){
		if(node.left == null)
			return node;
		return minimum(node.left);
	}
	//删除以node为根节点中的最小值的节点
	//再返回删除节点后的新的BST的根节点
	private Node removeMin(Node node){
		if(node.left == null){
			Node rightNode = node.right;
			node.right = null;
			size --;
			return rightNode;
		}
		node.left = removeMin(node.left);
		return node;
	}

	@Override
	public void set(K key, V newValue) {
		Node node = getNode(root, key);
		if(node == null)
			throw new IllegalArgumentException(key + "doesn't exit!");
		node.value = newValue;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	//统计词频
	public static void main(String[] args) {
		ArrayList<String> words = new ArrayList<>();
		System.out.println("Pride and Prejudice");
		if(FileOperation.readFile("pride-and-prejudice.txt", words)){
			System.out.println("Total words:" + words.size());
			
			BSTMap<String, Integer> map = new BSTMap<>();
			for(String word : words){
				if(map.contains(word)){
					map.set(word, map.get(word) + 1);
				}else{
					map.add(word, 1);
				}
			}
			System.out.println("Total different words:" + map.getSize());
			System.out.println("Frequency of PREIDE:" + map.get("pride"));
			System.out.println("Frequency of PREJUDICE:" + map.get("prejudice"));
		}
		
	}
}
