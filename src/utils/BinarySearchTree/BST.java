package utils.BinarySearchTree;

import utils.Queue.LinkedListQueue;
import utils.Queue.Queue;
import utils.Stack.ArrayStack;

//BST�д洢�����ݶ������ӵ�пɱȽ���
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
	//������������BST���Ԫ�أ��ǵݹ�
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
	//������������BST���Ԫ�أ��ݹ�
	public void add(E e){
		root = add(root, e);
	}
	//����nodeΪ����BST����ӵĵݹ麯��
	//���ز����½ڵ������������ĸ�
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
	//��BST�в�ѯԪ��e
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
	//BST��ǰ�����
	public void preOrder(){
		System.out.println("�ݹ�ʵ��ǰ�������");
		preOrder(root);
	}
	private void preOrder(Node node){
		if(node == null)
			return;
		System.out.println(node.e);
		preOrder(node.left);
		preOrder(node.right);
	}
	//BST��ǰ�����У��ǵݹ�д������Ҫʹ��Stack��ģ��ϵͳջ��
	public void preOrderNonRecursion(){
		System.out.println("�ǵݹ�ʵ��ǰ�������");
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
	//BST�Ĳ������   (������ȱ������ǵݹ鷽����ʹ�ö���Queue������)
	public void levelOrder(){
		System.out.println("���������");
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
	//BST���������  (�Ƕ���������˳�����еĽ��)
	public void inOrder(){
		System.out.println("���������");
		inOrder(root);
	}
	private void inOrder(Node node){
		if(node == null)
			return;
		inOrder(node.left);
		System.out.println(node.e);
		inOrder(node.right);
	}
	//BST�ĺ������
	public void postOrder(){
		System.out.println("���������");
		postOrder(root);
	}
	private void postOrder(Node node){
		if(node == null)
			return;
		postOrder(node.left);
		postOrder(node.right);
		System.out.println(node.e);
	}
	//Ѱ��BST����Сֵ
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
	//ɾ��BST����Сֵ���ڵĽڵ㣬������
	public E removeMin(){
		E ret = minimum();
		root = removeMin(root);
		return ret;
	}
	//ɾ����nodeΪ����BST����Сֵ�ڵ㣬����ɾ������µ�BST��left��
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
	
	//Ѱ��BST�����ֵ
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
	//ɾ�����ֵ���ڵĽڵ㲢����
	public E removeMax(){
		E ret = maximum();
		root = removeMax(root);
		return ret;
	}
	//ɾ����nodeΪ����BST�����ֵ�Ľڵ㣬����ɾ�����µ�BST��right��
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
	
	//ɾ��BST��Ԫ��E e�Ľڵ�
	public void remove(E e){
		root = remove(root, e);
	}
	//ɾ����NodeΪ����BST�е�Ԫ��e���ݹ��㷨�� ����ɾ���ڵ����µ�BST�ĸ�
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
			// ��ɾ���ڵ���������
			if(node.left == null){
				Node nodeRight = node.right;
				node.right = null;
				size --;
				return nodeRight;
			}
			// ��ɾ���Ľڵ���������
			if(node.right == null){
				Node nodeLeft = node.left;
				node.left = null;
				size --;
				return nodeLeft;
			}
			// ��ɾ���Ľڵ�����������
			// ����Ҫ�ҵ��ڵ�node�ĺ�̽ڵ�successor����node.right�е���Сֵ��ȡ��Сֵ�ڵ���Ϊnode�ڵ��successor�ڵ㣺
			//							successor��left��node.left��successor��right��node.right��ȥ����Сֵ��Ľ��
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
	//������nodeΪ���ڵ㣬���Ϊdepth���������������ַ��� (����ʹ��-ǰ�����-�ķ���)
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
//		System.out.println("remove min��" + bst.removeMin());
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
