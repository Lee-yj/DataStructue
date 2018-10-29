package utils.AVLTree;

import java.util.ArrayList;

import utils.Set.FileOperation;

public class AVLTree<K extends Comparable<K>, V> {

	
	private class Node{
		public K key;
		public V value;
		public Node left, right;
		public int height; 			//�ýڵ�ĸ߶�ֵ
		
		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			left = null;
			right = null;
			height = 1;
		}
	}
	private Node root;
	private int size;
	
	public AVLTree() {
		root = null;
		size = 0;
	}
	
	// �жϵ�ǰ�Ķ������Ƿ���һ������������
	// BST���������(��-��-��)����������
	public boolean isBST(){
		ArrayList<K> keys = new ArrayList<>();
		inOrder(root, keys);
		for(int i = 1; i < keys.size(); i++){
			if(keys.get(i).compareTo(keys.get(i-1)) < 0)
				return false;
		}
		return true;
	}
	private void inOrder(Node node, ArrayList<K> keys){
		if(node == null)
			return;
		inOrder(node.left, keys);
		keys.add(node.key);
		inOrder(node.right, keys);
	}
	
	// �жϵ�ǰ�������Ƿ���һ��ƽ�������
	public boolean isBalanced() {
		return isBalanced(root);
	}
	// �ж���NodeΪ���Ķ������Ƿ���һ��ƽ����������ݹ��㷨
	private boolean isBalanced(Node node) {
		if(node == null)
			return true;
		if(Math.abs(getBalanceFactor(node)) > 1)
			return false;
		return isBalanced(node.left) && isBalanced(node.right);
	}
	
	// ���һ���ڵ�ĸ߶�ֵ
	private int getHeight(Node node){
		if(node == null)
			return 0;
		return node.height;
	}
	// ���һ���ڵ��ƽ������
	private int getBalanceFactor(Node node){
		if(node == null)
			return 0;
		return getHeight(node.left) - getHeight(node.right);
	}
	
	// ��� 
	public void add(K key, V value) {
		root = add(root, key, value);
	}
	private Node add(Node node, K key, V value) {
		if(node == null){
			size ++;
			return new Node(key, value);
		}
		if(key.compareTo(node.key) < 0)
			node.left = add(node.left, key, value);
		else if(key.compareTo(node.key) > 0)
			node.right = add(node.right, key, value);
		else//key.compareTo(node.key) == 0
			node.value = value;
		
		//����height---�ڵ�����Һ��ӽڵ�����ֵ��һ
		node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
		//����ڵ��ƽ������
		int balanceFactor = getBalanceFactor(node);
		//ƽ��ά��
		// LL
		if(balanceFactor > 1 && getBalanceFactor(node.left) >= 0)
			return rightRotate(node);
		// RR 
		if(balanceFactor < -1 && getBalanceFactor(node.right) <= 0)
			return leftRotate(node);
		// LR
		if(balanceFactor > 1 && getBalanceFactor(node.left) < 0){
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}
		// RL
		if(balanceFactor < -1 && getBalanceFactor(node.right) > 0){
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}
		return node;
	}
	// ƽ��ά��---����ת (LL)
	//        y                              x
    //       / \                           /   \
    //      x   T4     ������ת (y)         z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
	private Node rightRotate(Node y){
		Node x = y.left;
		Node T3 = x.right;
		// ������ת����
		x.right = y;
		y.left = T3;
		//����height
		y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
		x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
		return x;
	}
	// ƽ��ά��---����ת (RR)
    //    y                             x
    //  /  \                          /   \
    // T1   x      ������ת (y)        y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;
        // ������ת����
        x.left = y;
        y.right = T2;
        // ����height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }
    // ƽ��ά��---(RL)
    //   y                            y  
    //  /  \                         / \
    // T1   x    ��x����ת���(RR)   T1   z  
    //     / \   - - - - - - - ->      / \
    //    Z  T4                       T2  x                    
    //   / \							 / \
    //  T2 T3							T3 T4
    
    // ƽ��ά��---(LR)
 	//        y                                 y
	//       / \                          	   / \
	//      x   T4      ��x����ת���(LL)          z   T4
	//     / \      - - - - - - - - ->       / \
	//    T1  z                             x  T3
	//   	 / \						   / \
	//      T2 T3						  T1 T2	
	public V get(K key) {
		Node node = getNode(root, key);
		return node == null ? null : node.value;
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
		
		Node retNode;
		if(key.compareTo(node.key) < 0){
			node.left = remove(node.left, key);
			retNode = node;
		}
		else if(key.compareTo(node.key) > 0){ 
			node.right = remove(node.right, key);
			retNode = node;
		}
		else{ // key.compareTo(node.key) == 0
			if(node.left == null){
				Node rightNode = node.right;
				node.right = null;
				size --;
				retNode = rightNode;
			}
			else if(node.right == null){
				Node leftNode = node.left;
				node.left = null;
				size --;
				retNode = leftNode;
			}
			else{//��ɾ���Ľڵ�������������Ϊ��
				// 1.�ҵ��Ƚڵ�����С�ڵ㣬�������е���С�ڵ�,��������ڵ���Ϣ������̽ڵ㣻2.��ɾ���������е���С�ڵ㣻 3.���غ�̽ڵ�
				Node successor = minimum(node.right);
	//			successor.right = removeMin(node.right);  // removeMin���ܻ����ƽ��,��ʵ�ȼ�=��node.right��ɾ��successor��keyֵ
				successor.right = remove(node.right, successor.key);
				successor.left = node.left;
				node.left = node.right = null;
				retNode = successor;
			}
		}
		// ��Ҫɾ���Ľڵ�ΪҶ�ӽڵ㣬��retNodeΪnull����Ҫ��null�߽紦��
		if(retNode == null)
			return null;
		
		//����height---�ڵ�����Һ��ӽڵ�����ֵ��һ
		retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));
		//����ڵ��ƽ������
		int balanceFactor = getBalanceFactor(retNode);
		//ƽ��ά��
		// LL
		if(balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0)
			return rightRotate(retNode);
		// RR 
		if(balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0)
			return leftRotate(retNode);
		// LR
		if(balanceFactor > 1 && getBalanceFactor(retNode.left) < 0){
			retNode.left = leftRotate(retNode.left);
			return rightRotate(retNode);
		}
		// RL
		if(balanceFactor < -1 && getBalanceFactor(retNode.right) > 0){
			retNode.right = rightRotate(retNode.right);
			return leftRotate(retNode);
		}
		return retNode;
	}

	//������nodeΪ���ڵ��BST�е���Сֵ���ڵĽڵ�
	private Node minimum(Node node){
		if(node.left == null)
			return node;
		return minimum(node.left);
	}
	//ɾ����nodeΪ���ڵ��е���Сֵ�Ľڵ�
	//�ٷ���ɾ���ڵ����µ�BST�ĸ��ڵ�
//	private Node removeMin(Node node){
//		if(node.left == null){
//			Node rightNode = node.right;
//			node.right = null;
//			size --;
//			return rightNode;
//		}
//		node.left = removeMin(node.left);
//		return node;
//	}

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
	
	//���ԣ�ͳ�ƴ�Ƶ
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
			
			System.out.println("is this a BST? ---" + map.isBST());
			System.out.println("is this a Balanced Tree? ---" + map.isBalanced());
			
			for(String word : words){
				map.remove(word);
				if(!map.isBalanced() || !map.isBST())
					throw new RuntimeException("error!");
			}
		}
		
	}
}
