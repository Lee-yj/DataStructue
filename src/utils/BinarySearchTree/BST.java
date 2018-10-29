package utils.BinarySearchTree;

import utils.Queue.LinkedListQueue;
import utils.Queue.Queue;
import utils.Stack.ArrayStack;

//BST中存储的数据对象必须拥有可比较性
public class BST<E extends Comparable<E>> {
	
	private class Node{
		public E e;
		public Node left, right;
		
		public Node(E e) {
			this.e = e;
			left = null;
			right = null;
		}
	}
	private Node root;
	private int size;
	
	public BST() {
		root = null;
		size = 0;
	}
	//给二分搜索树BST添加元素，非递归
	public void addByUnRecursion(E e){
		Node newNode = new Node(e);
		if(root == null){
			root = newNode;
			return;
		}
		Node cur = root;
		Node parent = null;
		while(cur != null){
			if(e.compareTo(cur.e) < 0){
				parent = cur;
				cur = cur.left;
			}else if(e.compareTo(cur.e) > 0){
				parent = cur;
				cur = cur.right;
			}else{
				return;
			}
		}
		if(e.compareTo(parent.e) < 0){
			parent.left = new Node(e);
		}else{
			parent.right = new Node(e);
		}
	}
	//给二分搜索树BST添加元素，递归
	public void add(E e){
		root = add(root, e);
	}
	//向以node为根的BST中添加的递归函数
	//返回插入新节点后二分搜索树的根
	private Node add(Node node, E e){
		if(node == null){
			size ++;
			return new Node(e);
		}
		if(e.compareTo(node.e) < 0){
			node.left = add(node.left, e);
		}else if(e.compareTo(node.e) > 0){
			node.right = add(node.right, e);
		}
		return node;
	}
	//在BST中查询元素e
	public boolean contains(E e){ 
		return contains(root, e);
	}
	private boolean contains(Node node, E e){
		if(node == null){
			return false;
		}
		if(e.compareTo(node.e) == 0){
			return true;
		}else if(e.compareTo(node.e) < 0){
			return contains(node.left, e);
		}else{
			return contains(node.right, e);
		}
	}
	//BST的前序遍历
	public void preOrder(){
		System.out.println("递归实现前序遍历：");
		preOrder(root);
	}
	private void preOrder(Node node){
		if(node == null)
			return;
		System.out.println(node.e);
		preOrder(node.left);
		preOrder(node.right);
	}
	//BST的前序排列（非递归写法，需要使用Stack来模仿系统栈）
	public void preOrderNonRecursion(){
		System.out.println("非递归实现前序遍历：");
		ArrayStack<Node> stack = new ArrayStack<>();
		stack.push(root);
		while(!stack.isEmpty()){
			Node cur = stack.pop();
			System.out.println(cur.e);
			if(cur.right != null)
				stack.push(cur.right);
			if(cur.left != null)
				stack.push(cur.left);
		}
	}
	//BST的层序遍历   (广度优先遍历，非递归方法，使用队列Queue来辅助)
	public void levelOrder(){
		System.out.println("层序遍历：");
		Queue<Node> queue = new LinkedListQueue<>();
		queue.enqueue(root);
		while(!queue.isEmpty()){
			Node cur = queue.dequeue();
			System.out.println(cur.e);
			if(cur.left != null)
				queue.enqueue(cur.left);
			if(cur.right != null)
				queue.enqueue(cur.right);
		}
	}
	//BST的中序遍历  (是二分搜索树顺序排列的结果)
	public void inOrder(){
		System.out.println("中序遍历：");
		inOrder(root);
	}
	private void inOrder(Node node){
		if(node == null)
			return;
		inOrder(node.left);
		System.out.println(node.e);
		inOrder(node.right);
	}
	//BST的后序遍历
	public void postOrder(){
		System.out.println("后序遍历：");
		postOrder(root);
	}
	private void postOrder(Node node){
		if(node == null)
			return;
		postOrder(node.left);
		postOrder(node.right);
		System.out.println(node.e);
	}
	//寻找BST中最小值
	public E minimum(){
		if(size == 0)
			throw new IllegalArgumentException("BST is empty!");
		return minimum(root).e;
	}
	private Node minimum(Node node){
		if(node.left == null)
			return node;
		return minimum(node.left);
	}
	public E minByNR(){
		Node cur = root;
		if(cur == null)
			return null;
		while(cur.left != null){
			cur = cur.left;
		}
		return cur.e;
	}
	//删除BST中最小值所在的节点，并返回
	public E removeMin(){
		E ret = minimum();
		root = removeMin(root);
		return ret;
	}
	//删除以node为根的BST中最小值节点，返回删除后的新的BST的left根
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
	
	//寻找BST中最大值
	public E maximum(){
		if(size == 0)
			throw new IllegalArgumentException("BST is empty!");
		return maximum(root).e;
	}
	private Node maximum(Node node){
		if(node.right == null)
			return node;
		return maximum(node.right);
	}
	//删除最大值所在的节点并返回
	public E removeMax(){
		E ret = maximum();
		root = removeMax(root);
		return ret;
	}
	//删除以node为根的BST中最大值的节点，返回删除后新的BST的right根
	private Node removeMax(Node node){
		if(node.right == null){
			Node nodeLeft = node.left;
			node.left = null;
			size --;
			return nodeLeft;
		}
		node.right = removeMax(node.right);
		return node;
	}
	
	//删除BST中元素E e的节点
	public void remove(E e){
		root = remove(root, e);
	}
	//删除以Node为根的BST中的元素e，递归算法， 返回删除节点后的新的BST的根
	private Node remove(Node node, E e){
		if(node == null){ 
			return null;
		}
		if(e.compareTo(node.e) < 0){
			node.left = remove(node.left, e);
			return node;
		}else if(e.compareTo(node.e) > 0){
			node.right = remove(node.right, e);
			return node;
		}else{ // e.compareTo(node.e) == 0
			// 待删除节点无左子树
			if(node.left == null){
				Node nodeRight = node.right;
				node.right = null;
				size --;
				return nodeRight;
			}
			// 待删除的节点无右子树
			if(node.right == null){
				Node nodeLeft = node.left;
				node.left = null;
				size --;
				return nodeLeft;
			}
			// 待删除的节点有左右子树
			// 首先要找到节点node的后继节点successor，即node.right中的最小值，取最小值节点作为node节点的successor节点：
			//							successor的left：node.left，successor的right：node.right的去除最小值后的结果
			Node successor = minimum(node.right);
			successor.right = removeMin(node.right);
			successor.left = node.left;
			node.left = node.right = null;
			return successor;
		}
	}
	
	public int size(){
		return size;
	}
	public boolean isEmpty(){
		return size == 0;
	}
	
	@Override
	public String toString(){
		StringBuilder res = new StringBuilder();
		generateBSTString(root, 0, res);
		return res.toString();
	}
	//生成以node为根节点，深度为depth的描述二叉树的字符串 (这里使用-前序遍历-的方法)
	private void generateBSTString(Node node, int depth, StringBuilder res) {
		if(node == null){
			res.append(depthString(depth) + "null\n");
			return;
		}
		res.append(depthString(depth) + node.e + "\n");
		generateBSTString(node.left, depth + 1, res);
		generateBSTString(node.right, depth + 1, res);
	}
	private String depthString(int depth){
		StringBuilder res = new StringBuilder();
		for(int i = 0; i < depth; i ++){
			res.append("--");
		}
		return res.toString();
	}
	
	public static void main(String[] args) {
		BST<Integer> bst = new BST<>();
		int[] nums = {5,3,6,1,8,4,2,7};
		for(int i = 0; i < nums.length; i ++)
			bst.add(nums[i]);
		bst.preOrder();
		bst.remove(3);
//		System.out.println("remove min：" + bst.removeMin());
		bst.preOrder();
//		System.out.println("remove max:" + bst.removeMax());
//		bst.preOrder();
//		bst.preOrderNonRecursion();
//		bst.levelOrder();
//		System.out.println(bst);
//		bst.inOrder();
//		bst.postOrder();
		
//		int n = 1000;
//		Random random = new Random();
//		for(int i = 0; i < n; i ++){
//			bst.add(random.nextInt(10000));
//		}
//		ArrayList<Integer> nums = new ArrayList<>();
//		while(!bst.isEmpty()){
//			nums.add(bst.removeMax());
//		}
//		System.out.println(nums);
//		
//		for(int i = 1; i < nums.size(); i ++){
//			if(nums.get(i-1) < nums.get(i))
//				throw new IllegalArgumentException("Error");
//		}
//		System.out.println("removeMax test completed");
		
	}
}
