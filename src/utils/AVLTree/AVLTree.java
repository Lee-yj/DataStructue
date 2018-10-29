package utils.AVLTree;

import java.util.ArrayList;

import utils.Set.FileOperation;

public class AVLTree<K extends Comparable<K>, V> {

	
	private class Node{
		public K key;
		public V value;
		public Node left, right;
		public int height; 			//该节点的高度值
		
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
	
	// 判断当前的二叉树是否是一个二分搜索树
	// BST的中序遍历(左-中-右)是升序排列
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
	
	// 判断当前二叉树是否是一颗平衡二叉树
	public boolean isBalanced() {
		return isBalanced(root);
	}
	// 判断以Node为根的二叉树是否是一颗平衡二叉树，递归算法
	private boolean isBalanced(Node node) {
		if(node == null)
			return true;
		if(Math.abs(getBalanceFactor(node)) > 1)
			return false;
		return isBalanced(node.left) && isBalanced(node.right);
	}
	
	// 获得一个节点的高度值
	private int getHeight(Node node){
		if(node == null)
			return 0;
		return node.height;
	}
	// 获得一个节点的平衡因子
	private int getBalanceFactor(Node node){
		if(node == null)
			return 0;
		return getHeight(node.left) - getHeight(node.right);
	}
	
	// 添加 
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
		
		//更新height---节点的左右孩子节点的最大值加一
		node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
		//计算节点的平衡因子
		int balanceFactor = getBalanceFactor(node);
		//平衡维护
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
	// 平衡维护---右旋转 (LL)
	//        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)         z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
	private Node rightRotate(Node y){
		Node x = y.left;
		Node T3 = x.right;
		// 向右旋转过程
		x.right = y;
		y.left = T3;
		//更新height
		y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
		x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
		return x;
	}
	// 平衡维护---左旋转 (RR)
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)        y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;
        // 向左旋转过程
        x.left = y;
        y.right = T2;
        // 更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }
    // 平衡维护---(RL)
    //   y                            y  
    //  /  \                         / \
    // T1   x    先x右旋转变成(RR)   T1   z  
    //     / \   - - - - - - - ->      / \
    //    Z  T4                       T2  x                    
    //   / \							 / \
    //  T2 T3							T3 T4
    
    // 平衡维护---(LR)
 	//        y                                 y
	//       / \                          	   / \
	//      x   T4      先x左旋转变成(LL)          z   T4
	//     / \      - - - - - - - - ->       / \
	//    T1  z                             x  T3
	//   	 / \						   / \
	//      T2 T3						  T1 T2	
	public V get(K key) {
		Node node = getNode(root, key);
		return node == null ? null : node.value;
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
			else{//待删除的节点左右子树都不为空
				// 1.找到比节点大的最小节点，右子树中的最小节点,根据这个节点信息建立后继节点；2.在删除右子树中的最小节点； 3.返回后继节点
				Node successor = minimum(node.right);
	//			successor.right = removeMin(node.right);  // removeMin可能会打破平衡,其实等价=在node.right中删除successor的key值
				successor.right = remove(node.right, successor.key);
				successor.left = node.left;
				node.left = node.right = null;
				retNode = successor;
			}
		}
		// 若要删除的节点为叶子节点，则retNode为null，需要对null边界处理
		if(retNode == null)
			return null;
		
		//更新height---节点的左右孩子节点的最大值加一
		retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));
		//计算节点的平衡因子
		int balanceFactor = getBalanceFactor(retNode);
		//平衡维护
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

	//返回以node为根节点的BST中的最小值所在的节点
	private Node minimum(Node node){
		if(node.left == null)
			return node;
		return minimum(node.left);
	}
	//删除以node为根节点中的最小值的节点
	//再返回删除节点后的新的BST的根节点
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
	
	//测试，统计词频
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
