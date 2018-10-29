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
	// �жϽڵ�node����ɫ
	private boolean isRed(Node node){
		if(node == null)
			return BLACK;
		return node.color;
	}
	// ������������µ�Ԫ��
	public void add(K key, V value) {
		root = add(root, key, value);
		root.color = BLACK; 		// ���ָ��ڵ�Ϊ��ɫ
	}
	// ����nodeΪ���ĺ�����в���Ԫ�أ�key��value�����ݹ��㷨
	// ���ز����½ڵ��ĺ�����ĸ�
	private Node add(Node node, K key, V value) {
		if(node == null){
			size ++;
			return new Node(key, value);  // Ĭ�ϲ���Ľڵ�Ϊ��ɫ
		}
		if(key.compareTo(node.key) < 0)
			node.left = add(node.left, key, value);
		else if(key.compareTo(node.key) > 0)
			node.right = add(node.right, key, value);
		else//key.compareTo(node.key) == 0
			node.value = value;

		// �жϽڵ�ĺ�ڣ���һ���������ж���
		// ����ת
		if(isRed(node.right) && !isRed(node.left))
            node = leftRotate(node);
		// ����ת
		if(isRed(node.left) && isRed(node.left.left))
            node = rightRotate(node);
		// ��ɫ��ת flipColor
		if(isRed(node.left) && isRed(node.right))
			flipColor(node);
		
		return node;
	}
	// ����ת
	private Node leftRotate(Node node){
		Node x = node.right;
		
		node.right = x.left;
		x.left = node;
		
		x.color = node.color;
		node.color = RED;
		
		return x;
	}
	// ����ת
	private Node rightRotate(Node node){
		Node x = node.left;
		
		node.left = x.right;
		x.right = node;
		
		x.color = node.color;
		node.color = RED;
		
		return x;
	}
	// ��ɫ��ת(���践�أ���Ϊ���ڵ㲻��)
	private void flipColor(Node node){
		node.color = RED;
		node.left.color = BLACK;
		node.right.color = BLACK;
	}
	
	//������NodeΪ���ڵ�Ķ����������У�key���ڵĽڵ�
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
			//��ɾ���Ľڵ�������������Ϊ��
			// 1.�ҵ��Ƚڵ�����С�ڵ㣬�������е���С�ڵ�,��������ڵ���Ϣ������̽ڵ㣻2.��ɾ���������е���С�ڵ㣻 3.���غ�̽ڵ�
			Node successor = minimum(node.right);
			successor.right = removeMin(node.right);  // ���ﲻ����size--��removeMin()�������Ѿ�size--
			successor.left = node.left;
			node.left = node.right = null;
			return successor;
		}
	}

	//������nodeΪ���ڵ��BST�е���Сֵ���ڵĽڵ�
	private Node minimum(Node node){
		if(node.left == null)
			return node;
		return minimum(node.left);
	}
	//ɾ����nodeΪ���ڵ��е���Сֵ�Ľڵ�
	//�ٷ���ɾ���ڵ����µ�BST�ĸ��ڵ�
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
