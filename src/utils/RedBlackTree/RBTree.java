package utils.RedBlackTree;

import java.util.ArrayList;

import utils.AVLTree.AVLTree;
import utils.Set.FileOperation;

public class RBTree<K extends Comparable<K>, V> {

	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	private class Node{
		public K key;
		public V value;
		public Node left, right;
		public boolean color;
		
		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			left = null;
			right = null;
			color = RED;
		}
	}
	private Node root;
	private int size;
	
	public RBTree() {
		root = null;
		size = 0;
	}
	// 判断节点node的颜色
	private boolean isRed(Node node){
		if(node == null)
			return BLACK;
		return node.color;
	}
	// 向红黑树中添加新的元素
	public void add(K key, V value) {
		root = add(root, key, value);
		root.color = BLACK; 		// 保持根节点为黑色
	}
	// 向以node为根的红黑树中插入元素（key，value），递归算法
	// 返回插入新节点后的红黑树的根
	private Node add(Node node, K key, V value) {
		if(node == null){
			size ++;
			return new Node(key, value);  // 默认插入的节点为红色
		}
		if(key.compareTo(node.key) < 0)
			node.left = add(node.left, key, value);
		else if(key.compareTo(node.key) > 0)
			node.right = add(node.right, key, value);
		else//key.compareTo(node.key) == 0
			node.value = value;

		// 判断节点的红黑，是一个连续的判断链
		// 左旋转
		if(isRed(node.right) && !isRed(node.left))
            node = leftRotate(node);
		// 右旋转
		if(isRed(node.left) && isRed(node.left.left))
            node = rightRotate(node);
		// 颜色翻转 flipColor
		if(isRed(node.left) && isRed(node.right))
			flipColor(node);
		
		return node;
	}
	// 左旋转
	private Node leftRotate(Node node){
		Node x = node.right;
		
		node.right = x.left;
		x.left = node;
		
		x.color = node.color;
		node.color = RED;
		
		return x;
	}
	// 右旋转
	private Node rightRotate(Node node){
		Node x = node.left;
		
		node.left = x.right;
		x.right = node;
		
		x.color = node.color;
		node.color = RED;
		
		return x;
	}
	// 颜色翻转(不需返回，因为根节点不变)
	private void flipColor(Node node){
		node.color = RED;
		node.left.color = BLACK;
		node.right.color = BLACK;
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
	
	public V get(K key) {
		Node node = getNode(root, key);
		return node == null ? null : node.value;
	}

	public boolean contains(K key) {
		return getNode(root, key) != null;
	}
	
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

	public void set(K key, V newValue) {
		Node node = getNode(root, key);
		if(node == null)
			throw new IllegalArgumentException(key + "doesn't exit!");
		node.value = newValue;
	}

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}
	
	
	public static void main(String[] args) {
		ArrayList<String> words = new ArrayList<>();
		System.out.println("Pride and Prejudice");
		if(FileOperation.readFile("pride-and-prejudice.txt", words)){
			System.out.println("Total words:" + words.size());
			
			AVLTree<String, Integer> map = new AVLTree<>();
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
			
			System.out.println("--------------------------------");
			
			RBTree<String, Integer> rb = new RBTree<>();
			for(String word : words){
				if(rb.contains(word)){
					rb.set(word, rb.get(word) + 1);
				}else{
					rb.add(word, 1);
				}
			}
			System.out.println("Total different words:" + rb.getSize());
			System.out.println("Frequency of PREIDE:" + rb.get("pride"));
			System.out.println("Frequency of PREJUDICE:" + rb.get("prejudice"));
		}
		
	}
	
}
